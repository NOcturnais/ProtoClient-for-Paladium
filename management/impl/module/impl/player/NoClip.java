package fr.noctu.haxx.proto.management.impl.module.impl.player;

import fr.noctu.haxx.proto.management.impl.event.eventbus.EventTarget;
import fr.noctu.haxx.proto.management.impl.event.impl.EventUpdate;
import fr.noctu.haxx.proto.management.impl.module.Module;
import org.lwjgl.input.Keyboard;

public class NoClip extends Module {
    public NoClip(){
        super("NoClip", Keyboard.KEY_NUMPAD3, Category.Player);
    }
    @EventTarget
    public void onUpdate(EventUpdate e){
        if(mc.thePlayer.onGround){
            mc.thePlayer.noClip = true;
            mc.thePlayer.fallDistance = 0f;
            mc.thePlayer.onGround = false;

            if(mc.gameSettings.keyBindJump.getIsKeyPressed())
                mc.thePlayer.motionY = 0.4f;
            else if(mc.gameSettings.keyBindSneak.getIsKeyPressed())
                mc.thePlayer.motionY -= 0.3f;
            else
                mc.thePlayer.motionY = 0;
        }
    }
    @Override
    public void onDisable(){
        super.onDisable();
        mc.thePlayer.noClip = false;
    }
}
