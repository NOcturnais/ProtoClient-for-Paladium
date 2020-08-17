package fr.noctu.haxx.proto.management.impl.module.impl.player;

import fr.noctu.haxx.proto.management.impl.event.eventbus.EventTarget;
import fr.noctu.haxx.proto.management.impl.event.impl.EntityHitEvent;
import fr.noctu.haxx.proto.management.impl.event.impl.EventUpdate;
import fr.noctu.haxx.proto.management.impl.module.Module;
import fr.noctu.haxx.proto.utils.CustomVec3;
import fr.noctu.haxx.proto.utils.MovementUtils;
import net.minecraft.network.play.client.C03PacketPlayer;
import org.lwjgl.input.Keyboard;

public class GodMod extends Module {
    //Skidded from jigsaw :'(

    boolean hasBeenTp;

    public GodMod(){
        super("GodMode", Keyboard.CHAR_NONE, Category.Player);
    }

    @EventTarget
    public void onUpdate(EventUpdate e){
        if(mc.thePlayer.onGround) {
            if(hasBeenTp)
                MovementUtils.blinkToPosFromPos(new CustomVec3(mc.thePlayer.posX, mc.thePlayer.posY + 500, mc.thePlayer.posZ), new CustomVec3(mc.thePlayer.posX, mc.thePlayer.posY, mc.thePlayer.posZ), 9.9);
            MovementUtils.blinkToPosFromPos(new CustomVec3(mc.thePlayer.posX, mc.thePlayer.posY, mc.thePlayer.posZ), new CustomVec3(mc.thePlayer.posX, mc.thePlayer.posY + 500, mc.thePlayer.posZ), 9.9);
            hasBeenTp = true;
            e.setCancelled(true);
        }else {
            mc.getNetHandler().addToSendQueue(new C03PacketPlayer(true));
            if(hasBeenTp)
                MovementUtils.blinkToPosFromPos(new CustomVec3(mc.thePlayer.posX, mc.thePlayer.posY + 500, mc.thePlayer.posZ), new CustomVec3(mc.thePlayer.posX, mc.thePlayer.posY, mc.thePlayer.posZ), 9.9);
            hasBeenTp = false;
        }
    }

    @EventTarget
    public void onEntityHit(EntityHitEvent e){
        MovementUtils.blinkToPosFromPos(new CustomVec3(mc.thePlayer.posX, mc.thePlayer.posY + 500, mc.thePlayer.posZ), new CustomVec3(mc.thePlayer.posX, mc.thePlayer.posY, mc.thePlayer.posZ), 9.9);
        hasBeenTp = false;
    }

    @Override
    public void onDisable(){
        super.onDisable();
        MovementUtils.blinkToPosFromPos(new CustomVec3(mc.thePlayer.posX, mc.thePlayer.posY + 500, mc.thePlayer.posZ), new CustomVec3(mc.thePlayer.posX, mc.thePlayer.posY, mc.thePlayer.posZ), 9.9);
        hasBeenTp = false;
    }
}
