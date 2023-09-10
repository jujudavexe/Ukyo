package fr.ukyo.ukyomanagement.commands;

import fr.ukyo.ukyomanagement.listener.MuteManager;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class UnmuteCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        Player player = (Player) sender;

        if (sender.hasPermission("ukyomanagement.management.unmute")) {

            if (args.length < 1){
                player.sendMessage(ChatColor.DARK_RED + "[SERVER] " + ChatColor.GREEN + "Utilisation : /unMute <player>");
                return true;
            }

            Player target = player.getServer().getPlayer(args[0]);

            MuteManager.unmutePlayer(target, sender);
            return true;
        }
        player.sendMessage(ChatColor.DARK_RED + "[SERVER] " + ChatColor.GOLD + "Vous n'avez pas la permission d'ex\u00E9cuter cette commande.");
        return true;
    }
}

