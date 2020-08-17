package fr.noctu.haxx.proto.management.impl.module.impl.combat;

import fr.noctu.haxx.proto.management.impl.event.eventbus.EventTarget;
import fr.noctu.haxx.proto.management.impl.event.impl.EventUpdate;
import fr.noctu.haxx.proto.management.impl.module.Module;
import net.minecraft.entity.EntityLivingBase;
import org.lwjgl.input.Keyboard;

public class Criticals extends Module {
    public Criticals(){
        super("Criticals", Keyboard.KEY_NONE, Category.Combat);
    }

    @EventTarget
    public void onUpdate(EventUpdate e){
       if(mc.objectMouseOver.entityHit instanceof EntityLivingBase && mc.thePlayer.isSwingInProgress && mc.thePlayer.onGround){
           mc.thePlayer.motionY = 0.5f;
           mc.thePlayer.fallDistance = 0.1f;
           mc.thePlayer.onGround = false;
       }
    }
}
