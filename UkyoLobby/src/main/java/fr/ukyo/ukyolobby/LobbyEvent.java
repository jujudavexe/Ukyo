package fr.ukyo.ukyolobby;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryDragEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class LobbyEvent implements Listener {

    private final PlayerGUI gui;

    public LobbyEvent(PlayerGUI gui) {
        this.gui = gui;
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();

        player.getInventory().clear();

        ItemStack item = new ItemStack(Material.COMPASS);
        final ItemMeta im = item.getItemMeta();
        im.setDisplayName(ChatColor.RED + "Rejoindre un serveur");
        item.setItemMeta(im);

        player.getInventory().setItem(4, item);

    }

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        if (event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK) {
            Player player = event.getPlayer();
            ItemStack item = event.getItem();

            if (item != null && gui.getInventory().getTitle().equals("Selectionner un serveur")) {
                player.openInventory(gui.getInventory());
            }
        }
    }

    @EventHandler
    public void onInventoryDrag(InventoryDragEvent event) {
        event.setCancelled(true);
    }

    @EventHandler
    public void onPlayerDropItem(PlayerDropItemEvent event) {
        event.setCancelled(true);
    }


    @EventHandler
    public void onPlayerPickUp(PlayerPickupItemEvent event) {
        event.setCancelled(true);
    }

}