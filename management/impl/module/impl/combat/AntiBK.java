package fr.noctu.haxx.proto.management.impl.module.impl.combat;

import fr.noctu.haxx.proto.management.impl.event.eventbus.EventTarget;
import fr.noctu.haxx.proto.management.impl.event.impl.EventPacket;
import fr.noctu.haxx.proto.management.impl.module.Module;
import net.minecraft.network.play.server.S12PacketEntityVelocity;
import org.lwjgl.input.Keyboard;

public class AntiBK extends Module{
    public AntiBK(){
        super("AntiKnockBack", Keyboard.CHAR_NONE, Module.Category.Combat);
    }

    @EventTarget
    public void onPacket(EventPacket e){
        if(e.getPacket() instanceof S12PacketEntityVelocity)
            e.cancelled = true;
    }
}
