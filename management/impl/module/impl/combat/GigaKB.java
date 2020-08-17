package fr.noctu.haxx.proto.management.impl.module.impl.combat;

import fr.noctu.haxx.proto.management.impl.event.eventbus.EventTarget;
import fr.noctu.haxx.proto.management.impl.event.impl.EventUpdate;
import fr.noctu.haxx.proto.management.impl.module.Module;
import fr.noctu.haxx.proto.utils.PlayerUtils;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.play.client.C03PacketPlayer;
import org.lwjgl.input.Keyboard;

public class GigaKB extends Module {
    public GigaKB(){
        super("GigaKB", Keyboard.CHAR_NONE, Category.Combat);
    }

    @EventTarget
    public void onUpdate(EventUpdate e){
        EntityPlayer t = PlayerUtils.getClosestEntity(3);
        if(mc.thePlayer.boundingBox.intersectsWith(t.boundingBox)){
            for (int i = 0; i < 250; i++) {
                mc.thePlayer.sendQueue.addToSendQueue(new C03PacketPlayer(mc.thePlayer.onGround));
            }
        }
    }
}
