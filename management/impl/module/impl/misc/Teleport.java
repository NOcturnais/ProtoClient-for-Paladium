package fr.noctu.haxx.proto.management.impl.module.impl.misc;

import fr.noctu.haxx.proto.management.impl.module.Module;
import net.minecraft.network.play.client.C03PacketPlayer;
import org.lwjgl.input.Keyboard;

public class Teleport extends Module{
    public Teleport(){
        super("Teleport", Keyboard.KEY_NUMPAD1, Module.Category.Misc);
    }
    @Override
    public void onEnable(){
        super.onEnable();
        mc.getNetHandler().addToSendQueue(new C03PacketPlayer.C04PacketPlayerPosition(100, 100, 100, 100, true));
        toggle();
    }
}
