package fr.noctu.haxx.proto.management.impl.module.impl.player;

import fr.noctu.haxx.proto.management.impl.event.eventbus.EventTarget;
import fr.noctu.haxx.proto.management.impl.event.impl.EventUpdate;
import fr.noctu.haxx.proto.management.impl.module.Module;
import net.minecraft.network.play.client.C03PacketPlayer;
import org.lwjgl.input.Keyboard;

public class AntiFire extends Module {
    public AntiFire(){
        super("AntiFire", Keyboard.CHAR_NONE, Category.Player);
    }

    @EventTarget
    public void onUpdate(EventUpdate e){
        if(mc.thePlayer.onGround && mc.thePlayer.isBurning()){
            for(int i = 0; i<100; i++) {
                mc.thePlayer.sendQueue.addToSendQueue(new C03PacketPlayer(true));
            }
        }
    }
}
