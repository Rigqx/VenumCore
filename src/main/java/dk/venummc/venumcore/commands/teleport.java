package dk.venummc.venumcore.commands;

import dk.venummc.venumcore.VenumCore;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class teleport implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player p = (Player) sender;
        if(sender instanceof Player){
            if(p.hasPermission(VenumCore.getInstance().getConfig().getString("Teleport.permission"))){
                if(args.length > 0) {
                    if(args[0].equalsIgnoreCase("all")){
                        for(Player user : Bukkit.getOnlinePlayers())
                            user.teleport(p);
                        return true;
                    } else {
                        if(args.length == 1) {
                            Player arg = (Player) Bukkit.getPlayer(args[0]);
                            if(arg.isOnline())
                                p.teleport(arg);
                        } else if (args.length == 2){
                            Player player1 = (Player) Bukkit.getPlayer(args[0]);
                            Player player2 = (Player) Bukkit.getPlayer(args[1]);

                            if(player1.isOnline() && player2.isOnline())
                                player1.teleport(player2);
                        }
                    }
                }
            } else {
                p.sendMessage("§f" + VenumCore.getInstance().getConfig().getString("Core.Ingen-adgang").replace("&", "§") + "§f");
            }
        }

        return true;
    }
}
