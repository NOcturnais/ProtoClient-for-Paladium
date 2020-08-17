package fr.noctu.haxx.proto.management.impl.module.impl.misc;

import fr.noctu.haxx.proto.management.impl.event.eventbus.EventTarget;
import fr.noctu.haxx.proto.management.impl.event.impl.EventPacket;
import fr.noctu.haxx.proto.management.impl.event.impl.EventUpdate;
import fr.noctu.haxx.proto.management.impl.module.Module;
import fr.noctu.haxx.proto.utils.MovementUtils;
import net.minecraft.client.gui.GuiGameOver;
import net.minecraft.network.play.client.C03PacketPlayer;
import org.lwjgl.input.Keyboard;

public class DeathBypass extends Module {
    public DeathBypass(){
        super("DeathBypass", Keyboard.KEY_NUMPAD2, Category.Misc);
    }
    boolean activate = false;
    @EventTarget
    public void onUpdate(EventUpdate e){
        if(mc.currentScreen instanceof GuiGameOver){
            mc.displayGuiScreen(null);
            mc.thePlayer.isDead = false;
            mc.thePlayer.setHealth(20f);
            //mc.thePlayer.setPositionAndUpdate(mc.thePlayer.posX, mc.thePlayer.posY, mc.thePlayer.posZ);
            activate = true;
        }
    }
    @Override
    public void onDisable(){
        super.onDisable();
        if(activate){
            activate = false;
            mc.thePlayer.respawnPlayer();
        }
    }

    @EventTarget
    public void onPacket(EventPacket e){
        if(e.getPacket() instanceof C03PacketPlayer && activate)
            e.cancelled=true;
    }
}
