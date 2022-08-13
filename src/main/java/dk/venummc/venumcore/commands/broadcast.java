package dk.venummc.venumcore.commands;

import dk.venummc.venumcore.VenumCore;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class broadcast implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player p = (Player) sender;
        if(p.hasPermission("Staff.bc")) {
            if (args.length < 1) {
                p.sendMessage("");
                p.sendMessage(VenumCore.getInstance().getConfig().getString("Broadcast.prefix").replace("&", "§"));
                p.sendMessage("");
                p.sendMessage("§8§l» §f/Broadcast §8(§bBesked§8)§f.");
                p.sendMessage("");
            } else {
                String msg = "";
                for (int i = 0; i < args.length; i++)
                    msg = msg + args[i] + " ";

                if (VenumCore.getInstance().getConfig().getString("Broadcast.layout").equals("1")) {
                    Bukkit.broadcastMessage(VenumCore.getInstance().getConfig().getString("Broadcast.prefix").replace("&", "§") + " " + VenumCore.getInstance().getConfig().getString("Broadcast.message-color").replace("&", "§") + msg.replaceAll("&", ""));
                } else if (VenumCore.getInstance().getConfig().getString("Broadcast.layout").equals("2")) {
                    Bukkit.broadcastMessage(VenumCore.getInstance().getConfig().getString("Broadcast.prefix").replace("&", "§"));
                    Bukkit.broadcastMessage(VenumCore.getInstance().getConfig().getString("Broadcast.prefix").replace("&", "§") + " " + VenumCore.getInstance().getConfig().getString("Broadcast.message-color").replace("&", "§") + msg.replaceAll("&", ""));
                    Bukkit.broadcastMessage(VenumCore.getInstance().getConfig().getString("Broadcast.prefix").replace("&", "§"));
                } else if (VenumCore.getInstance().getConfig().getString("Broadcast.layout").equals("3")) {
                    Bukkit.broadcastMessage("");
                    Bukkit.broadcastMessage(VenumCore.getInstance().getConfig().getString("Broadcast.prefix").replace("&", "§"));
                    Bukkit.broadcastMessage(VenumCore.getInstance().getConfig().getString("Broadcast.message-color").replace("&", "§") + msg.replaceAll("&", ""));
                    Bukkit.broadcastMessage("");
                } else if (VenumCore.getInstance().getConfig().getString("Broadcast.layout").equals("4")) {
                    Bukkit.broadcastMessage("");
                    Bukkit.broadcastMessage(VenumCore.getInstance().getConfig().getString("Broadcast.prefix").replace("&", "§"));
                    Bukkit.broadcastMessage("");
                    Bukkit.broadcastMessage("§8§l» §f" + VenumCore.getInstance().getConfig().getString("Broadcast.message-color").replace("&", "§") + msg.replaceAll("&", ""));
                    Bukkit.broadcastMessage("");
                }
            }
        } else {
            p.sendMessage("§f" + VenumCore.getInstance().getConfig().getString("Core.Ingen-adgang").replace("&", "§") + "§f");
        }
        p.playSound(p.getLocation(), Sound.NOTE_PLING, 1.0f, 1.0f);
        return true;
    }
}
