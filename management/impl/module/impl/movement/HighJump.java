package fr.noctu.haxx.proto.management.impl.module.impl.movement;

import fr.noctu.haxx.proto.management.impl.event.eventbus.EventTarget;
import fr.noctu.haxx.proto.management.impl.event.impl.EventJump;
import fr.noctu.haxx.proto.management.impl.module.Module;
import org.lwjgl.input.Keyboard;

public class HighJump extends Module {
    public HighJump(){
        super("HighJump", Keyboard.KEY_NONE, Category.Movement);
    }

    @EventTarget
    public void onJump(EventJump e){
        if(mc.thePlayer.onGround)
            mc.thePlayer.motionY=1f;
    }
}
