package fr.ukyo.ukyomanagement.commands;

import org.bukkit.BanList;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class UnbanCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (!(sender instanceof Player)) {
            if (args.length < 1) {
                sender.sendMessage(ChatColor.DARK_RED + "[SERVER] " + ChatColor.GREEN + "Utilisation : /unban <joueur>");
                return true;
            }
            String playerName = args[0];


            if (!Bukkit.getBanList(BanList.Type.NAME).isBanned(playerName)) {
                sender.sendMessage(ChatColor.DARK_RED + "[SERVER] " + ChatColor.GOLD + "Le joueur spécifié n'est pas banni.");
                return false;
            }

            Player target = sender.getServer().getPlayer(playerName);

            Bukkit.getBanList(BanList.Type.NAME).pardon(playerName);

            sender.sendMessage(ChatColor.DARK_RED + "[SERVER] " + ChatColor.GOLD + playerName + " a été d\u00E9banni avec succ\u00E8s.");

            return false;
        }

        if (!sender.hasPermission("ukyomanagement.management.unban")) {
            sender.sendMessage(ChatColor.DARK_RED + "[SERVER] " + ChatColor.GOLD + "Tu n'as pas la permission d'utiliser cette commande.");
            return false;
        }

        if (args.length < 1) {
            sender.sendMessage(ChatColor.DARK_RED + "[SERVER] " + ChatColor.GREEN + "Utilisation : /unban <joueur>");
            return false;
        }

        String playerName = args[0];

        if (!Bukkit.getBanList(BanList.Type.NAME).isBanned(playerName)) {
            sender.sendMessage(ChatColor.DARK_RED + "[SERVER] " + ChatColor.GOLD + "Le joueur sp\u00E9cifi\u00E9 n'est pas banni.");
            return false;
        }

        Bukkit.getBanList(BanList.Type.NAME).pardon(playerName);

        sender.sendMessage(ChatColor.DARK_RED + "[SERVER] " + ChatColor.GOLD + playerName + " a été d\u00E9banni avec succ\u00E8s.");

        return false;
    }
}
