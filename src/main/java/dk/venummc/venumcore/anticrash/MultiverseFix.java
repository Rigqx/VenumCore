package dk.venummc.venumcore.anticrash;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

import java.util.logging.Logger;

public class MultiverseFix implements Listener {
    @EventHandler
    public void onCommand(PlayerCommandPreprocessEvent ev) {
        if (ev.getMessage().startsWith("/mv"))
            ev.setMessage(ev.getMessage().replace("..", ""));
        if(ev.getMessage().contains("/mv"))
            ev.setMessage(ev.getMessage().replace("^(.*.*.*.*.*.*.*.*.*.*.*.*.*.*.*.*.*.*.*.*.*.*.*.*.*.++)$^", ""));

        Logger.getLogger("[VenumMC-Core AntiCrash] The user " + ev.getPlayer() + " tried to crash the server using the MultiVerse Crasher");
    }
}
