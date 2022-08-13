package dk.venummc.venumcore.listener;

import dk.venummc.venumcore.VenumCore;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import java.util.ArrayList;

public class joinListener implements Listener {
    ArrayList<String> messages = new ArrayList<>(VenumCore.getInstance().getConfig().getStringList("Join.messages"));

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e){
        if(!e.getPlayer().hasPlayedBefore()) {
            String list = String.valueOf(Bukkit.getOfflinePlayers().length);
            String prefix = VenumCore.getInstance().getConfig().getString("Join.prefix").replace("&", "§");
            for(String m : messages) {
                Bukkit.broadcastMessage(m
                        .replace("<count>", list)
                        .replace("<player>", e.getPlayer().getDisplayName())
                        .replace("<prefix>", prefix)
                        .replace("&", "§")
                );
            }
        }
    }
}
