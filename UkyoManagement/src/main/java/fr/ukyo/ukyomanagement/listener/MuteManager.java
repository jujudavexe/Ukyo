package fr.ukyo.ukyomanagement.listener;

import fr.ukyo.ukyomanagement.Main;
import fr.ukyo.ukyomanagement.utils.MuteData;
import fr.ukyo.ukyomanagement.utils.TimeUtils;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.permissions.PermissionAttachment;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class MuteManager implements Listener {

    private static final Map<UUID, MuteData> mutedPlayers = new HashMap<>();

    public static void mutePlayer(Player player, String reason, Main main, CommandSender sender, long duration) {

        UUID playerId = player.getUniqueId();

        if (isMuted(player)) {
            sender.sendMessage(ChatColor.DARK_RED + "[SERVER] " + ChatColor.GOLD + "Le joueur est d\u00E9j\u00E0 muet.");
            return;
        }

        PermissionAttachment attachment = player.addAttachment(main);

        attachment.setPermission("minecraft.command.say", false);

        long currentTime = System.currentTimeMillis();
        long unmuteTime = currentTime + duration;
        MuteData muteData = new MuteData(playerId, attachment, unmuteTime);

        mutedPlayers.put(playerId, muteData);

        sender.sendMessage(ChatColor.DARK_RED + "[SERVER] " + ChatColor.GOLD + player.getName() + " est maintenant muet," + ChatColor.AQUA + " pendant " + TimeUtils.formatDuration(duration));
        player.sendMessage(ChatColor.DARK_RED + "[SERVER] " + ChatColor.GOLD + "Tu as \u00E9t\u00E9 rendu muet pour : " + ChatColor.AQUA + reason + ChatColor.GOLD + " pendant " + TimeUtils.formatDuration(duration));
    }

    public static void unmutePlayer(Player player, CommandSender sender) {
        UUID playerId = player.getUniqueId();

        if (!isMuted(player)) {
            sender.sendMessage(ChatColor.DARK_RED + "[SERVER] " + ChatColor.GOLD + "Ce joueur n'est pas muet.");
            return;
        }

        MuteData muteData = mutedPlayers.get(playerId);

        player.removeAttachment(muteData.getAttachment());
        mutedPlayers.remove(playerId);

        sender.sendMessage(ChatColor.DARK_RED + "[SERVER] " + ChatColor.GOLD + player.getName() + " n'est plus muet.");
        player.sendMessage(ChatColor.DARK_RED + "[SERVER] " + ChatColor.GOLD + "Tu n'es plus muet !");
    }

    public static boolean isMuted(Player player) {
        UUID playerId = player.getUniqueId();
        return mutedPlayers.containsKey(playerId);
    }

    @EventHandler
    public void onPlayerChat(AsyncPlayerChatEvent event) {
        Player player = event.getPlayer();

        if (isMuted(player)) {
            event.setCancelled(true);
            player.sendMessage(ChatColor.DARK_RED + "[SERVER] Tu es actuellement muet et ne peux pas envoyer de messages.");
        }
    }
}