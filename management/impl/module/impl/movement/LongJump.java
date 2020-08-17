package fr.noctu.haxx.proto.management.impl.module.impl.movement;

import fr.noctu.haxx.proto.management.impl.event.eventbus.EventTarget;
import fr.noctu.haxx.proto.management.impl.event.impl.EventJump;
import fr.noctu.haxx.proto.management.impl.event.impl.EventUpdate;
import fr.noctu.haxx.proto.management.impl.module.Module;
import fr.noctu.haxx.proto.utils.MovementUtils;
import net.minecraft.network.play.server.S12PacketEntityVelocity;
import org.lwjgl.input.Keyboard;

public class LongJump extends Module {
    private boolean isJumping = false;

    public LongJump(){
        super("LongJump", Keyboard.KEY_1, Category.Movement);
    }
    @EventTarget
    public void onUpdate(EventUpdate e){
        if(!mc.thePlayer.onGround && MovementUtils.isMoving() && isJumping) {
            MovementUtils.setSpeed(2);
            isJumping = false;
        }
    }
    @EventTarget
    public void onJump(EventJump e){
        isJumping = true;
    }
}
