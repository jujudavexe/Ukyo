package fr.ukyo.ukyomanagement.listener;


import fr.ukyo.ukyomanagement.utils.TimeUtils;
import org.bukkit.BanEntry;
import org.bukkit.BanList;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerLoginEvent;


public class PlayerEvent implements Listener {

    @EventHandler
    public void onPlayerLogin(PlayerLoginEvent event) {
        Player player = event.getPlayer();
        if (event.getResult() == PlayerLoginEvent.Result.KICK_BANNED) {
            BanEntry banEntry = player.getServer().getBanList(BanList.Type.NAME).getBanEntry(player.getName());
            if (banEntry != null) {
                long expirationTime = banEntry.getExpiration().getTime();
                long remainingTime = expirationTime - System.currentTimeMillis();
                String reason = banEntry.getReason();
                if (remainingTime > 0) {
                    event.setKickMessage(ChatColor.DARK_RED + "Vous avez \u00E9t\u00E9 banni du serveur.\n" +
                                         ChatColor.GOLD + "Raison : " + reason + "\n" +
                            ChatColor.DARK_GREEN + "Temps restant : " + TimeUtils.formatDuration(remainingTime));
                } else {
                    player.getServer().getBanList(BanList.Type.NAME).pardon(player.getName());
                }
            }

        }
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        if (event.getInventory().getName().equals("Permission category")) {
            event.setCancelled(true);
        }
    }

}
