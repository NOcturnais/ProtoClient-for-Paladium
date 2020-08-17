package fr.noctu.haxx.proto.utils;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;

public class PlayerUtils {
    public static EntityPlayer getClosestEntity(float range) {
        EntityPlayer closestEntity = null;
        float mindistance = range;
        for (Object o : Minecraft.getMinecraft().theWorld.loadedEntityList) {
            if (o instanceof EntityPlayer && !(o instanceof EntityPlayerSP)) {
                EntityPlayer en = (EntityPlayer) o;
                if (Minecraft.getMinecraft().thePlayer.getDistanceToEntity(en) < mindistance) {
                    mindistance = Minecraft.getMinecraft().thePlayer.getDistanceToEntity(en);
                    closestEntity = en;
                }
            }
        }
        return closestEntity;
    }
}
