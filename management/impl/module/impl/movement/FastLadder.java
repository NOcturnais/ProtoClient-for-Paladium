package fr.noctu.haxx.proto.management.impl.module.impl.movement;

import fr.noctu.haxx.proto.management.impl.event.eventbus.EventTarget;
import fr.noctu.haxx.proto.management.impl.event.impl.EventUpdate;
import fr.noctu.haxx.proto.management.impl.module.Module;
import fr.noctu.haxx.proto.utils.MovementUtils;
import org.lwjgl.input.Keyboard;

public class FastLadder extends Module {
    public FastLadder(){
        super("FastLadder", Keyboard.CHAR_NONE, Category.Movement);
    }

    @EventTarget
    public void onUpdate(EventUpdate e){
        if(mc.thePlayer.isOnLadder() && MovementUtils.isMoving()) {
            mc.thePlayer.motionY = 0.5f;
        }
    }
}
