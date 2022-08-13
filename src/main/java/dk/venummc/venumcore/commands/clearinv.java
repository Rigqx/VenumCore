package dk.venummc.venumcore.commands;

import dk.venummc.venumcore.VenumCore;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class clearinv implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		Player p = (Player) sender;

		String Prefix = VenumCore.getInstance().getConfig().getString("Clearinv.prefix");
		String mainColor = VenumCore.getInstance().getConfig().getString("Clearinv.mainColor");
		String secondColor = VenumCore.getInstance().getConfig().getString("Clearinv.secondaryColor");
		if(p.hasPermission("Staff.ci")){
			if(args.length < 1){
				p.getInventory().clear();
				p.sendMessage(Prefix + " " + mainColor + "Du har nu fjernet dit " + secondColor + "inventory" + mainColor + "!");
				p.playSound(p.getLocation(), Sound.ANVIL_USE, 1.0f, 1.0f);
			}else{
				OfflinePlayer of = Bukkit.getOfflinePlayer(args[0]);
				if(of.isOnline()){
					Player ofp = Bukkit.getPlayer(of.getUniqueId());
					ofp.getInventory().clear();
					p.sendMessage(Prefix + " " + mainColor + "Du har nu fjernet " + secondColor + ofp.getDisplayName() + mainColor + "'s Inventory");
					ofp.sendMessage(Prefix + " " + mainColor + "Dit " + secondColor + "inventory " + mainColor + "er blevet fjernet, af brugeren " + secondColor + p.getDisplayName());

					p.playSound(p.getLocation(), Sound.ANVIL_USE, 1.0f, 1.0f);
					ofp.playSound(p.getLocation(), Sound.ANVIL_USE, 1.0f, 1.0f);
				} else{
					p.sendMessage(Prefix + " " + mainColor + "Brugeren er ikke online.");
					p.playSound(p.getLocation(), Sound.ANVIL_BREAK, 1.0f, 1.0f);
				}
			}
		} else {
			p.sendMessage("§f" + VenumCore.getInstance().getConfig().getString("Core.Ingen-adgang").replace("&", "§") + "§f");
		}
		return true;
	}
}
