package fr.noctu.haxx.proto.management.impl.module.impl.misc;

import fr.noctu.haxx.proto.ProtoClient;
import fr.noctu.haxx.proto.management.impl.event.eventbus.EventTarget;
import fr.noctu.haxx.proto.management.impl.event.impl.EventPacket;
import fr.noctu.haxx.proto.management.impl.module.Module;
import org.lwjgl.input.Keyboard;

import java.awt.*;

public class PacketListener extends Module {
    public PacketListener(){
        super("PacketListener", Keyboard.CHAR_NONE, Category.Misc);
    }

    @EventTarget
    public void onPacket(EventPacket e){
        if(e.side == EventPacket.Side.IN){
            ProtoClient.instance.console.println("Packet reçu:" + e.getPacket().getClass().getSimpleName(), Color.WHITE);
        }
    }
}
