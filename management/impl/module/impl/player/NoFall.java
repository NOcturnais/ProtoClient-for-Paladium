package fr.noctu.haxx.proto.management.impl.module.impl.player;

import fr.noctu.haxx.proto.management.impl.event.eventbus.EventTarget;
import fr.noctu.haxx.proto.management.impl.event.impl.EventPacket;
import fr.noctu.haxx.proto.management.impl.event.impl.EventUpdate;
import fr.noctu.haxx.proto.management.impl.module.Module;
import net.minecraft.client.Minecraft;
import net.minecraft.network.play.client.C03PacketPlayer;
import org.lwjgl.input.Keyboard;

import java.lang.reflect.Field;

public class NoFall extends Module {
    public NoFall(){
        super("NoFall", Keyboard.KEY_NONE, Category.Player);
    }

    @EventTarget
    public void onUpdate(EventUpdate e){
        if(mc.thePlayer.fallDistance>2)
            mc.thePlayer.sendQueue.addToSendQueue(new C03PacketPlayer(true));
    }
}
