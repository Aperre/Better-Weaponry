package dev.revizion.ape.betterWeaponry;

import dev.revizion.ape.betterWeaponry.weapons.MaceTweaks;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class BetterWeaponry extends JavaPlugin {

    @Override
    public void onEnable() {
        Bukkit.getPluginManager().registerEvents(new MaceTweaks(), this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
