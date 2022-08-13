package dk.venummc.venumcore.commands;

import dk.venummc.venumcore.VenumCore;
import org.bukkit.Bukkit;
import org.bukkit.Statistic;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ontime implements CommandExecutor {
    public String getPlaytime(Player player){
        // Sætter values
        int onlineTicks = player.getStatistic(Statistic.PLAY_ONE_TICK);
        int playTime = onlineTicks/20;
        int days = (int) Math.floor(playTime / 86400);
        int hours = (int) Math.floor((playTime - (days * 86400)) / 3600);
        int minutes = (int) Math.floor((playTime - ((days * 86400) + (hours * 3600))) / 60);
        int seconds = (int) Math.floor(playTime - ((days * 86400) + (hours * 3600) + (minutes * 60)));
        // Return statement
        return days + " Dage, " + hours + " Timer, " + minutes + " Minutter, " + seconds + " Sekunder!";
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player p = (Player) sender;
        if(sender instanceof Player) {
            if (args.length == 0) {
                p.sendMessage("");
                p.sendMessage(VenumCore.getInstance().getConfig().getString("Ontime.prefix"));
                p.sendMessage("");
                p.sendMessage("§8§l» §bDin §fOntime§8: §b" + getPlaytime(p));
                p.sendMessage("");
                return true;
            } else if (args.length >= 1) {
                Player arg = (Player) Bukkit.getPlayer(args[0]);
                if (arg.hasPlayedBefore()) {
                    p.sendMessage("");
                    p.sendMessage(VenumCore.getInstance().getConfig().getString("Ontime.prefix"));
                    p.sendMessage("");
                    p.sendMessage("§8§l» §b" + arg.getDisplayName() + "'s §f Ontime§8: §b" + getPlaytime(arg));
                    p.sendMessage("");
                } else {
                    p.sendMessage("");
                    p.sendMessage(VenumCore.getInstance().getConfig().getString("Ontime.prefix"));
                    p.sendMessage("");
                    p.sendMessage("§8§l» §fAngiv venligst en gyldig bruger!");
                    p.sendMessage("");
                }
            }
        }
        return true;
    }
}
