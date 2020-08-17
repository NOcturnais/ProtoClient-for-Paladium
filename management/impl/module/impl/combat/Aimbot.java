package fr.noctu.haxx.proto.management.impl.module.impl.combat;

import fr.noctu.haxx.proto.management.impl.event.eventbus.EventTarget;
import fr.noctu.haxx.proto.management.impl.event.impl.EventUpdate;
import fr.noctu.haxx.proto.management.impl.module.Module;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;
import org.lwjgl.input.Keyboard;

public class Aimbot extends Module {
    public Aimbot(){
        super("Aimbot", Keyboard.KEY_NONE, Category.Combat);
    }

    @EventTarget
    public void onUpdate(EventUpdate e){
        for(Object o : mc.theWorld.loadedEntityList){
            if(o instanceof Entity && mc.thePlayer.getDistanceToEntity((Entity)o) <= 5 && o != mc.thePlayer){
                aim((Entity)o);
            }
        }
    }

    public static void aim(Entity e){
        double x = e.posX - mc.thePlayer.posX;
        double z = e.posZ - mc.thePlayer.posZ;
        double y = e.posY + (e.getEyeHeight()/1.4D) - mc.thePlayer.posY + (mc.thePlayer.getEyeHeight()/1.4D);
        double helper = MathHelper.sqrt_double(x*x+z*z); //racine carré return en double

        float newYaw = (float)((Math.toDegrees(-Math.atan(x / z))));
        float newPitch = (float)-Math.toDegrees(Math.atan(y / helper));

        if(z < 0 && x < 0) { newYaw = (float)(90D + Math.toDegrees(Math.atan(z / x))); }
        else if(z < 0 && x > 0) { newYaw = (float)(-90D + Math.toDegrees(Math.atan(z / x))); }

        mc.thePlayer.rotationYaw = newYaw;
        mc.thePlayer.rotationPitch = newPitch;
        mc.thePlayer.rotationYawHead = newPitch;
    }
}
