package dk.venummc.venumcore.commands;

import dk.venummc.venumcore.VenumCore;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

import java.util.ArrayList;

public class vanish implements CommandExecutor, Listener {

	public static ArrayList<Player> invis_list = new ArrayList<>();

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if(sender instanceof Player){
			Player p = (Player) sender;
			if(p.hasPermission("Staff.vanish")){
				if(invis_list.contains(p)) {
					for(Player people : Bukkit.getOnlinePlayers()){
						people.showPlayer(p);
					}
					p.sendMessage(VenumCore.getInstance().getConfig().getString("Vanish.prefix").replace("&", "§") + "§f" + " §f" + VenumCore.getInstance().getConfig().getString("Vanish.toggled-off").replace("&", "§"));
					invis_list.remove(p);
				} else if(!invis_list.contains(p)) {
					for(Player people : Bukkit.getOnlinePlayers()){
						people.hidePlayer(p);
					}
					p.sendMessage(VenumCore.getInstance().getConfig().getString("Vanish.prefix").replace("&", "§") + "§f" + " §f" + VenumCore.getInstance().getConfig().getString("Vanish.toggled-on").replace("&", "§"));
					invis_list.add(p);
				}
			} else {
				p.sendMessage("§f" + VenumCore.getInstance().getConfig().getString("Core.Ingen-adgang").replace("&", "§") + "§f");
			}
		}

		return true;
	}
}
