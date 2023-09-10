package fr.ukyo.ukyolobby;

import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {
    private static Main instance;

    public static Main getInstance() {
        return instance;
    }

    @Override
    public void onEnable() {
        super.onEnable();

        instance = this;

        getServer().getPluginManager().registerEvents(new LobbyEvent(new PlayerGUI()), this);
        getServer().getPluginManager().registerEvents(new BungeeConnect(this), this);

        getServer().getMessenger().registerOutgoingPluginChannel(this, "BungeeCord");
    }

    @Override
    public void onDisable() {
        super.onDisable();
    }

}
