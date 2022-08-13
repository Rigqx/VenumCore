package dk.venummc.venumcore.anticrash;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

import java.util.logging.Logger;

public class WorldEditFix implements Listener {
    @EventHandler
    public void onCommand(PlayerCommandPreprocessEvent ev) {
        if (ev.getMessage().startsWith("//to"))
            ev.setMessage(ev.getMessage().replace("for(i=0;i<256;i++){for(j=0;j<256;j++){for(k=0;k<256;k++){for(l=0;l<256;l++){ln(pi)}}}}", ""));
        if (ev.getMessage().startsWith("//calc"))
            ev.setMessage(ev.getMessage().replace("for(i=0;i<256;i++){for(j=0;j<256;j++){for(k=0;k<256;k++){for(l=0;l<256;l++){for(m=0;m<256;m++){ln(pi)}}}}}", ""));
        if (ev.getMessage().startsWith("/worldedit:/calc"))
            ev.setMessage(ev.getMessage().replace("for{7i=o,i<256;i++}{for[b=0;b<256;b++]{for[h=0;h<256;h++]{for[h=0;h<256;h++]{for[n=0;n<256;n++]{}}]}", ""));

        Logger.getLogger("[VenumMC-Core AntiCrash] The user " + ev.getPlayer() + " tried to crash the server using the WorldEdit Crasher");
    }
}
