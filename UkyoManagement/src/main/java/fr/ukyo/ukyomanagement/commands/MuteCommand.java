package fr.ukyo.ukyomanagement.commands;

import fr.ukyo.ukyomanagement.Main;
import fr.ukyo.ukyomanagement.listener.MuteManager;
import fr.ukyo.ukyomanagement.utils.TimeUtils;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class MuteCommand implements CommandExecutor {

    private final Main main;

    public MuteCommand(Main main) {
        this.main = main;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (sender.hasPermission("ukyomanagement.management.mute")) {
            if (args.length >= 3) {
                StringBuilder raison = new StringBuilder();
                for (int i = 1; i < args.length - 1; i++) {
                    raison.append(args[i]).append(" ");
                }

                Player target = sender.getServer().getPlayer(args[0]);
                if (target == null) {
                    sender.sendMessage(ChatColor.GOLD + "Le joueur " + args[0] + " n'est pas en ligne.");
                    return true;
                }

                String durationString = args[args.length - 1];
                long duration;
                try {
                    duration = TimeUtils.parseDuration(durationString);
                } catch (NumberFormatException e) {
                    sender.sendMessage(ChatColor.GOLD + "Dur\u00E9e invalide sp\u00E9cifi\u00E9e.");
                    return true;
                }

                MuteManager.mutePlayer(target, raison.toString(), main, sender, duration);
                return true;
            }

            sender.sendMessage(ChatColor.DARK_RED + "[SERVER] " + ChatColor.GREEN + "Utilisation : /mute <joueur> <raison> <temps>.");
            return false;
        }

        sender.sendMessage(ChatColor.DARK_RED + "[SERVER] " + ChatColor.GOLD + "Vous n'avez pas la permission d'ex\u00E9cuter cette commande.");
        return false;
    }
}
