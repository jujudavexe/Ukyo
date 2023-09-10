package fr.ukyo.ukyolobby;

import org.bukkit.Bukkit;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;

public class PlayerGUI implements InventoryHolder {

    private final Inventory inventory;


    public PlayerGUI() {
        inventory = Bukkit.createInventory(this, 27, "Selectionner un serveur");

        Servers[] serverList = Servers.values();

        for (Servers servers : serverList) {
            ItemStack item = ItemStackUtilities.getMetaItem(servers.getItemDisplay(), servers.getDisplayName(), servers.getDescription(), servers.getStatut());
            inventory.setItem(servers.getInventoryIndex(), item);

        }

    }

    public Inventory getInventory() {
        return inventory;
    }
}
