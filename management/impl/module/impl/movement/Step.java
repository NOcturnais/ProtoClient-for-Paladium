package fr.noctu.haxx.proto.management.impl.module.impl.movement;

import fr.noctu.haxx.proto.management.impl.event.eventbus.EventTarget;
import fr.noctu.haxx.proto.management.impl.event.impl.EventUpdate;
import fr.noctu.haxx.proto.management.impl.module.Module;
import org.lwjgl.input.Keyboard;

public class Step extends Module {
    public Step(){
        super("Step", Keyboard.KEY_3, Category.Movement);
    }
    @EventTarget
    public void onUpdate(EventUpdate e){
        mc.thePlayer.stepHeight = 3f;
    }
    @Override
    public void onDisable(){
        super.onDisable();
        mc.thePlayer.stepHeight = 0.4f;
    }
}
