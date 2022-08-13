package dk.venummc.venumcore.commands;

import dk.venummc.venumcore.VenumCore;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class nattesyn implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		Player p = (Player) sender;

		if(p.hasPotionEffect(PotionEffectType.NIGHT_VISION)){
			p.removePotionEffect(PotionEffectType.NIGHT_VISION);
			p.sendMessage(VenumCore.getInstance().getConfig().getString("Nightvision.prefix").replace("&", "§") + " §f" + VenumCore.getInstance().getConfig().getString("Nightvision.toggled-off").replace("&", "§"));
		}else{
			p.sendMessage(VenumCore.getInstance().getConfig().getString("Nightvision.prefix").replace("&", "§") + " §f" + VenumCore.getInstance().getConfig().getString("Nightvision.toggled-on").replace("&", "§"));
			p.addPotionEffect(new PotionEffect(PotionEffectType.NIGHT_VISION, 999999, 1));
		}
		return true;
	}
}
