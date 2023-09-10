package fr.ukyo.ukyolobby;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public enum Servers {
    SERVER1("Skyblock", true, new ItemStack(Material.SAPLING), "Le skyblock est un mode de jeu ...", 11),
    SERVER2("Survie", false, new ItemStack(Material.APPLE), "Joue avec tes amis en survie ...", 13),
    SERVER3("Practice", false, new ItemStack(Material.DIAMOND_SWORD), "Viens te battre contre d'autres joueurs ...", 15),
    SERVER4("Bedwars", true, new ItemStack(Material.BED), "Casse les lits de tes adversaires.", 4);

    final private String displayName;

    final private boolean statut;
    final private ItemStack itemDisplay;
    final private String description;
    final private int inventoryIndex;

    Servers(String displayName, boolean statut, ItemStack itemDisplay, String description, int inventoryIndex) {
        this.displayName = displayName;
        this.statut = statut;
        this.itemDisplay = itemDisplay;
        this.description = description;
        this.inventoryIndex = inventoryIndex;
    }


    public String getDisplayName() {
        return displayName;
    }

    public boolean getStatut() {
        return statut;
    }

    public ItemStack getItemDisplay() {
        return itemDisplay;
    }

    public String getDescription() {
        return description;
    }

    public int getInventoryIndex() {
        return inventoryIndex;
    }
}
