package dk.venummc.venumcore.commands;

import dk.venummc.venumcore.VenumCore;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import java.io.File;

public class core implements CommandExecutor {

	public void loadConfig(Plugin plugin) {
		File file = new File(plugin.getDataFolder().getAbsolutePath() + "/config.yml");
		FileConfiguration cfg = YamlConfiguration.loadConfiguration(file);
	}

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		Player p = (Player) sender;
		if(p.hasPermission("Staff.*")){
			if (args.length < 1) {
				p.sendMessage("");
				p.sendMessage(VenumCore.getInstance().getConfig().getString("Core.prefix").replace("&", "§"));
				p.sendMessage("");
				p.sendMessage("§8§l» §f/VenumCore Reload §8- §bGenindlæser pluginnet.");
				p.sendMessage("");
				return true;
			} else if(args[0].equalsIgnoreCase("reload")){
				p.sendMessage("");
				p.sendMessage(VenumCore.getInstance().getConfig().getString("Core.prefix").replace("&", "§"));
				p.sendMessage("");
				p.sendMessage("§8§l» §fGenindlæser coren.");

				VenumCore.getInstance().getPluginLoader().disablePlugin(VenumCore.getInstance());
				VenumCore.getInstance().getPluginLoader().enablePlugin(VenumCore.getInstance());
				VenumCore.getInstance().reloadConfig();

				p.sendMessage("§8§l» §fCoren er nu genindlæst.");
				p.sendMessage("");
				return true;
			}
		} else {
			p.sendMessage("§f" + VenumCore.getInstance().getConfig().getString("Core.Ingen-adgang").replace("&", "§") + "§f");
		}
		return true;
	}
}
