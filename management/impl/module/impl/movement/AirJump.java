package fr.noctu.haxx.proto.management.impl.module.impl.movement;

import fr.noctu.haxx.proto.management.impl.event.eventbus.EventTarget;
import fr.noctu.haxx.proto.management.impl.event.impl.EventUpdate;
import fr.noctu.haxx.proto.management.impl.module.Module;
import org.lwjgl.input.Keyboard;

public class AirJump extends Module {
    public AirJump(){
        super("AirJump", Keyboard.KEY_NONE, Category.Movement);
    }

    @EventTarget
    public void onUpdate(EventUpdate e){
        if(!mc.thePlayer.onGround)
            mc.thePlayer.onGround = true;
    }
}
