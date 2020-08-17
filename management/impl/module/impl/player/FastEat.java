package fr.noctu.haxx.proto.management.impl.module.impl.player;

import fr.noctu.haxx.proto.management.impl.event.eventbus.EventTarget;
import fr.noctu.haxx.proto.management.impl.event.impl.EventPacket;
import fr.noctu.haxx.proto.management.impl.event.impl.EventUpdate;
import fr.noctu.haxx.proto.management.impl.module.Module;
import net.minecraft.item.ItemFood;
import net.minecraft.network.play.client.C01PacketChatMessage;
import net.minecraft.network.play.client.C03PacketPlayer;
import org.lwjgl.input.Keyboard;

public class FastEat extends Module {
    public FastEat(){
        super("FastEat", Keyboard.CHAR_NONE, Category.Player);
    }

    @EventTarget
    public void onUpdate(EventUpdate e){
        if(mc.thePlayer.isUsingItem() && mc.thePlayer.getItemInUse().getItem() instanceof ItemFood){
            for(int i = 0; i<130; i++)
                mc.thePlayer.sendQueue.addToSendQueue(new C03PacketPlayer(false));
        }
    }
}
