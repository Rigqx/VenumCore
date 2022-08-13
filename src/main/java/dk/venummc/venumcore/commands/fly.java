package dk.venummc.venumcore.commands;

import dk.venummc.venumcore.VenumCore;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class fly implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		Player p = (Player) sender;
		String prefix = VenumCore.getInstance().getConfig().getString("Fly.prefix").replace("&", "§");
		if(p.hasPermission("Staff.fly")){
			if(p.isFlying()){
				p.setAllowFlight(false);
				p.sendMessage(prefix + " §f" + VenumCore.getInstance().getConfig().getString("Fly.toggled-off").replace("&", "§"));
			}else {
				p.setAllowFlight(true);
				p.sendMessage(prefix + " §f" + VenumCore.getInstance().getConfig().getString("Fly.toggled-off").replace("&", "§"));
			}
		} else {
			p.sendMessage("§f" + VenumCore.getInstance().getConfig().getString("Core.Ingen-adgang").replace("&", "§") + "§f");
		}
		return true;
	}
}
