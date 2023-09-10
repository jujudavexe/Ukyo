package fr.ukyo.ukyomanagement.commands;

import fr.ukyo.ukyomanagement.utils.TimeUtils;
import org.bukkit.BanList;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Arrays;
import java.util.Date;

import static org.bukkit.Bukkit.getOfflinePlayer;

public class BanCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!sender.hasPermission("ukyomanagement.management.ban")) {
            sender.sendMessage(ChatColor.DARK_RED + "[SERVER] " + ChatColor.GOLD + "Vous n'avez pas la permission d'exécuter cette commande.");
            return true;
        }

        if (args.length < 2) {
            sender.sendMessage(ChatColor.DARK_RED + "[SERVER] " + ChatColor.GREEN + "Utilisation : /ban <joueur> <raison>");
            return true;
        }

        executeBanCommand(sender, args);
        return true;
    }

    private void executeBanCommand(CommandSender sender, String[] args) {
        Player target = sender.getServer().getPlayer(args[0]);

        if (target == null) {
            banOfflinePlayer(sender, args[0], args);
        } else {
            banOnlinePlayer(sender, target, args);
        }
    }

    private void banOfflinePlayer(CommandSender sender, String targetName, String[] args) {
        OfflinePlayer offlinePlayer = getOfflinePlayer(targetName);

        String durationString = args[1];
        String reason;
        long duration;
        Date expirationDate;
        try {
            duration = TimeUtils.parseDuration(durationString);
            expirationDate = new Date(System.currentTimeMillis() + duration);
            reason = String.join(" ", Arrays.copyOfRange(args, 2, args.length));
        } catch (NumberFormatException e) {
            expirationDate = new Date(Long.MAX_VALUE);
            reason = String.join(" ", Arrays.copyOfRange(args, 1, args.length));
        }
        sender.getServer().getBanList(BanList.Type.NAME).addBan(offlinePlayer.getName(), reason, expirationDate, sender.getName());

        sender.sendMessage(ChatColor.DARK_RED + "[SERVER] " + ChatColor.GOLD + "Le joueur " + targetName + " a \u00E9t\u00E9 banni.");
    }

    private void banOnlinePlayer(CommandSender sender, Player target, String[] args) {
        String durationString = args[1];
        String reason;
        long duration;
        Date expirationDate;
        try {
            duration = TimeUtils.parseDuration(durationString);
            expirationDate = new Date(System.currentTimeMillis() + duration);
            reason = String.join(" ", Arrays.copyOfRange(args, 2, args.length));
        } catch (NumberFormatException e) {
            expirationDate = new Date(Long.MAX_VALUE);
            reason = String.join(" ", Arrays.copyOfRange(args, 1, args.length));
        }

        sender.getServer().getBanList(BanList.Type.NAME).addBan(target.getName(), reason, expirationDate, sender.getName());

        String message = createBanMessage(reason, expirationDate);
        target.kickPlayer(message);
        sender.sendMessage(ChatColor.DARK_RED + "[SERVER] " + ChatColor.GOLD + "Le joueur " + target.getName() + " a \u00E9t\u00E9 banni.");
    }

    private String createBanMessage(String reason, Date expirationDate) {
        long remainingTime = expirationDate.getTime() - System.currentTimeMillis();
        String durationMessage = TimeUtils.formatDuration(remainingTime);

        return ChatColor.DARK_RED + "Vous avez \u00E9t\u00E9 banni du serveur.\n" +
                ChatColor.GOLD + "Raison : " + reason + "\n" +
                ChatColor.AQUA + "Temps restant : " + durationMessage;
    }
}