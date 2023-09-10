package fr.ukyo.ukyolobby;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.plugin.Plugin;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class BungeeConnect implements Listener {
    private final Main main;

    public BungeeConnect(Main main) {
        this.main = main;
    }

    @EventHandler
    public void onClick(InventoryClickEvent event) {
        if (event.getCurrentItem() != null) {
            Player player = (Player) event.getWhoClicked();
            if (!event.getCurrentItem().getItemMeta().getDisplayName().contains("CLOSED")) {
                switch (event.getCurrentItem().getItemMeta().getDisplayName()) {
                    case "§dSkyblock":
                        event.setCancelled(true);
                        player.closeInventory();
                        connectToBungee(player, "mmo", this.main);
                        break;
                    default:
                        event.setCancelled(true);
                        break;
                }
            } else {
                event.setCancelled(true);
                player.closeInventory();
                player.sendMessage(ChatColor.RED + "Le serveur est fermé !");
            }
        }
    }

    public static void connectToBungee(Player player, String server, Plugin main){
        ByteArrayOutputStream b = new ByteArrayOutputStream();
        DataOutputStream out = new DataOutputStream(b);
        try {
            out.writeUTF("Connect");
            out.writeUTF(server);
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        player.sendPluginMessage(main, "BungeeCord", b.toByteArray());
    }
}
