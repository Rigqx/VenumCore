package dk.venummc.venumcore;

import dk.venummc.venumcore.anticrash.*;
import dk.venummc.venumcore.commands.*;
import dk.venummc.venumcore.listener.*;
import dk.venummc.venumcore.utils.*;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.Bukkit;

import java.util.logging.Logger;

public final class VenumCore extends JavaPlugin {

    private static VenumCore instance;
    public static VenumCore getInstance() {
        return instance;
    }


    @Override
    public void onEnable() {
        instance = this;

        Logger.getLogger("Initializing..");

        Logger.getLogger("Checking license..");
        if(!new Licens(getConfig().getString("Licens"), "https://mcudvikling.dk/licens/verify.php", this).setConsoleLog(Licens.LogType.NORMAL).register())
            return;

        Logger.getLogger("Checking Dependencies..");
        // Check Dependencies Method
        Logger.getLogger("Dependencies is valid..");

        Logger.getLogger("Hooking..");
        // Register commands

        getCommand("VenumCore").setExecutor(new core());

        getCommand("broadcast").setExecutor(new broadcast());

        getCommand("ontime").setExecutor(new ontime());

        getCommand("nv").setExecutor(new nattesyn());

        getCommand("ci").setExecutor(new clearinv());

        getCommand("fly").setExecutor(new fly());

        getCommand("vanish").setExecutor(new vanish());
        Bukkit.getServer().getPluginManager().registerEvents(new vanishListener(), this);

        getCommand("teleport").setExecutor(new teleport());

        Bukkit.getServer().getPluginManager().registerEvents(new joinListener(), this);

        getServer().getPluginManager().registerEvents(new scoreboardListener(), this);

        Bukkit.getPluginManager().registerEvents(new MultiverseFix(), this);
        Bukkit.getPluginManager().registerEvents(new WorldEditFix(), this);

        Logger.getLogger("Hooked..");



        Logger.getLogger("Loading Metrics..");
        // Metrics
        // Coming soon
        Logger.getLogger("Metrics loaded..");

        Logger.getLogger("Loading Config..");
        // Config
        getConfig().options().copyDefaults();
        saveDefaultConfig();
        Logger.getLogger("Config loaded..");

        Logger.getLogger("VenumCore has been loaded..");
    }

    @Override
    public void onDisable() {
        Logger.getLogger("VenumCore is saving..");
        // Plugin shutdown logic
        getConfig().options().copyDefaults();
        saveDefaultConfig();
        Logger.getLogger("VenumCore has been saved and is now shutdown..");
    }
}
