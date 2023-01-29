package pridecraft.networkPlugin;

import com.tchristofferson.configupdater.ConfigUpdater;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;

public final class PridePlugin extends JavaPlugin implements Listener {

    final FileConfiguration config = this.getConfig();

    @Override
    public void onEnable() {
        saveDefaultConfig();
        //The config needs to exist before using the updater
        File configFile = new File(getDataFolder(), "config.yml");

        try {
            ConfigUpdater.update(this, "config.yml", configFile);
        } catch (IOException e) {
            e.printStackTrace();
        }

        reloadConfig();
        getServer().getPluginManager().registerEvents(new Events(), this);
        if (config.getBoolean("startup-message")) {
            System.out.println("Pride Network Started!");
        }
    }
}
