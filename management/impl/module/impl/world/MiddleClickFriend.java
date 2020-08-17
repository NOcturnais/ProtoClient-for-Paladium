package fr.noctu.haxx.proto.management.impl.module.impl.world;

import fr.noctu.haxx.proto.ProtoClient;
import fr.noctu.haxx.proto.management.impl.event.eventbus.EventTarget;
import fr.noctu.haxx.proto.management.impl.event.impl.EventUpdate;
import fr.noctu.haxx.proto.management.impl.module.Module;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.MovingObjectPosition;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;

public class MiddleClickFriend extends Module {
    public MiddleClickFriend(){
        super("MiddleClickFriend", Keyboard.CHAR_NONE, Category.Player);
    }

    @EventTarget
    public void onUpdate(EventUpdate e){
        MovingObjectPosition mov = mc.objectMouseOver;
        if(mov.typeOfHit == MovingObjectPosition.MovingObjectType.ENTITY && Mouse.isButtonDown(3)){
            Entity entity = mov.entityHit;
            if(entity instanceof EntityPlayer){
                EntityPlayer ep = (EntityPlayer) entity;
                if(ProtoClient.instance.friends.contains(ep))
                    ProtoClient.instance.friends.remove(ep);
                else
                    ProtoClient.instance.friends.add(ep);
            }
        }
    }
}
