package dev.revizion.ape.betterWeaponry;

import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.Damageable;
import org.bukkit.inventory.meta.ItemMeta;

public class utils {
    public static void damageItem(ItemStack item, int amount) {
        ItemMeta itemMeta = item.getItemMeta();
        Damageable dmg = (Damageable) itemMeta;
        int finalDamage;
        int currentDamage = 0;
        if (dmg.hasDamage()) currentDamage = dmg.getDamage();
        if (!itemMeta.hasEnchant(Enchantment.UNBREAKING)) {
            dmg.setDamage(currentDamage + amount);
            item.setItemMeta(dmg);
            return;
        }
        finalDamage = (int) Math.round((double) amount / (itemMeta.getEnchantLevel(Enchantment.UNBREAKING) + 1));
        dmg.setDamage(currentDamage + finalDamage);
        item.setItemMeta(dmg);
    }
}
