package dev.revizion.ape.betterWeaponry.weapons;

import com.destroystokyo.paper.ParticleBuilder;
import io.papermc.paper.datacomponent.item.blocksattacks.ItemDamageFunction;
import net.kyori.adventure.text.Component;
import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.Damageable;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.util.Vector;
import dev.revizion.ape.betterWeaponry.utils;

public class MaceTweaks implements Listener {
    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        World world = player.getWorld();
        ItemStack item = event.getItem();
        double fallingSpeed = player.getVelocity().getY();


        if (item == null) return;
        if (item.getType() != Material.getMaterial("MACE") || event.getBlockFace().getModY() != 1 || !event.getAction().isLeftClick() || fallingSpeed > -0.7 || !player.isSneaking())
            return;

        Location location = event.getClickedBlock().getLocation().add(.5, 1.3, .5);

        double impactForce = Math.clamp(fallingSpeed*-1,0, 2)/2;
        new ParticleBuilder(Particle.LARGE_SMOKE)
                .source(player)
                .location(location)
                .count((int) Math.round(Math.pow(impactForce,2)*400))
                .spawn();

        if (impactForce>0.5)
            new ParticleBuilder(Particle.EXPLOSION)
                    .source(player)
                    .location(location)
                    .count((int) Math.round(impactForce*5))
                    .spawn();

        world.createExplosion(location,(int)Math.round((Math.pow(impactForce,2)*10)),false, false,player);
        player.setVelocity(player.getVelocity().multiply(new Vector(0,-.8,0)));

        utils.damageItem(item, (int)Math.round(impactForce*10));
    }
}
