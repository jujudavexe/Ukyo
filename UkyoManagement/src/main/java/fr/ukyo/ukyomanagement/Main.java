package fr.ukyo.ukyomanagement;

import fr.ukyo.ukyomanagement.commands.*;
import fr.ukyo.ukyomanagement.listener.MuteManager;
import fr.ukyo.ukyomanagement.listener.PlayerEvent;
import org.bukkit.plugin.java.JavaPlugin;

public final class Main extends JavaPlugin {

    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(new PlayerEvent(), this);
        getServer().getPluginManager().registerEvents(new MuteManager(), this);
        this.getCommand("kick").setExecutor(new KickCommand());
        this.getCommand("ban").setExecutor(new BanCommand());
        this.getCommand("unban").setExecutor(new UnbanCommand());
        this.getCommand("mute").setExecutor(new MuteCommand(this));
        this.getCommand("unmute").setExecutor(new UnmuteCommand());
    }

}
