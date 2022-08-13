package dk.venummc.venumcore.listener;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import static dk.venummc.venumcore.commands.vanish.invis_list;

public class vanishListener implements Listener {
	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent e){
		Player p = e.getPlayer();
		for (Player player : invis_list) {
			p.hidePlayer(player);
		}
	}

}
