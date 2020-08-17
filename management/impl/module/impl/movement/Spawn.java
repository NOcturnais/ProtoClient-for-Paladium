package fr.noctu.haxx.proto.management.impl.module.impl.movement;

import fr.noctu.haxx.proto.management.impl.module.Module;
import org.lwjgl.input.Keyboard;

public class Spawn extends Module {
    public Spawn(){
        super("Spawn", Keyboard.KEY_NONE, Category.Movement);
    }

    @Override
    public void onEnable(){
        super.onEnable();
        mc.thePlayer.setPosition(10000, 100000, 1000000);
        toggle();
    }
}
