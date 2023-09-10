package fr.ukyo.ukyolobby;

import org.bukkit.ChatColor;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Collections;

public class ItemStackUtilities {

    public static ItemStack getMetaItem(ItemStack item, String displayName, String description, boolean statut) {
        final ItemMeta im = item.getItemMeta();
        if (statut) {
            im.setDisplayName("§d" + displayName);
            im.setLore(Collections.singletonList(description));
        } else {
            im.setDisplayName(ChatColor.DARK_RED + "CLOSED  |  " + displayName);
            im.setLore(Collections.singletonList(description));
        }


        item.setItemMeta(im);
        return item;
    }

}
