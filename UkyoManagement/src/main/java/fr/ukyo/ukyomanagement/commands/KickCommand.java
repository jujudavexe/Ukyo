package fr.ukyo.ukyomanagement.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class KickCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (sender.hasPermission("ukyomanagement.management.kick")) {
            if (args.length >= 2){
                Player target = sender.getServer().getPlayer(args[0]);

                if (target != null) {
                    StringBuilder raison = new StringBuilder();

                    for (int i = 1; i < args.length; i++)
                        raison.append(args[i]).append(" ");

                    target.kickPlayer(ChatColor.GOLD +  "Tu as \u00E9t\u00E9 expuls\u00E9 pour : " + ChatColor.AQUA + args[1]);
                } else {
                    sender.sendMessage(ChatColor.DARK_RED + "[SERVER] " + ChatColor.GOLD + "Le joueur n'est pas connect\u00E9.");
                }
                return true;
            }
            sender.sendMessage(ChatColor.DARK_RED + "[SERVER] " + ChatColor.GREEN + "Utilisation : /kick <player> <raison>");
            return  true;
        }

        sender.sendMessage(ChatColor.DARK_RED + "[SERVER] " + ChatColor.GOLD + "Vous n'avez pas la permission d'ex\u00E9cuter cette commande.");
        return false;
    }
}
