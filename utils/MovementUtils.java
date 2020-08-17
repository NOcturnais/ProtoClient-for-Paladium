package fr.noctu.haxx.proto.utils;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.network.play.client.C03PacketPlayer;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3;

public class MovementUtils {
	
	private static Minecraft mc = Minecraft.getMinecraft();
	
	public static float getDirection(EntityLivingBase e) {
	    float yaw = e.rotationYaw;
	    float forward = e.moveForward;
	    float strafe = e.moveStrafing;
	    yaw += (forward < 0.0F ? 180 : 0);
	    if (strafe < 0.0F) {
	    	yaw += (forward == 0.0F ? 90 : forward < 0.0F ? -45 : 45);
	    }
	    if (strafe > 0.0F) {
	    	yaw -= (forward == 0.0F ? 90 : forward < 0.0F ? -45 : 45);
	    }
	    return yaw * 0.017453292F;
	}
	
	public static void setSpeed(Entity e, double speed){
		e.motionX = (-MathHelper.sin(getDirection()) * speed);
		e.motionZ = (MathHelper.cos(getDirection()) * speed);
	}
	
	public static double getSpeed(EntityLivingBase e){
	    return Math.sqrt(square(e.motionX) + square(e.motionZ));
	}
	
	public static float getDirection() { return MovementUtils.getDirection(mc.thePlayer); }
	public static void setSpeed(double speed){ MovementUtils.setSpeed((Entity)mc.thePlayer, speed); }
	public static double getSpeed() { return MovementUtils.getSpeed(mc.thePlayer); }
	
	public static double square(double in){
	    return in * in;
	}

    public static void vClip(double d) {
        mc.thePlayer.setPosition(mc.thePlayer.posX, mc.thePlayer.posY + d, mc.thePlayer.posZ);
    }

	public static boolean isMoving(){
	    return (Wrapper.mc.thePlayer.moveForward != 0.0F) || (Wrapper.mc.thePlayer.moveStrafing != 0.0F);
	}
	public static void forward(final double length) {
		final double yaw = Math.toRadians(mc.thePlayer.rotationYaw);
		mc.thePlayer.setPosition(mc.thePlayer.posX + (-Math.sin(yaw) * length), mc.thePlayer.posY, mc.thePlayer.posZ + (Math.cos(yaw) * length));
	}

    public static void blinkToPosFromPos(CustomVec3 src, CustomVec3 dest, double maxTP) {
        double range = 0;
        double xDist = src.xCoord - dest.xCoord;
        double yDist = src.yCoord - dest.yCoord;
        double zDist = src.zCoord - dest.zCoord;
        double x1 = src.xCoord;
        double y1 = src.yCoord;
        double z1 = src.zCoord;
        double x2 = dest.xCoord;
        double y2 = dest.yCoord;
        double z2 = dest.zCoord;
        range = Math.sqrt(xDist * xDist + yDist * yDist + zDist * zDist);
        double step = maxTP / range;
        int steps = 0;
        for (int i = 0; i < range; i++) {
            steps++;
            if (maxTP * steps > range) {
                break;
            }
        }
        for (int i = 0; i < steps; i++) {
            double difX = x1 - x2;
            double difY = y1 - y2;
            double difZ = z1 - z2;
            double divider = step * i;
            double x = x1 - difX * divider;
            double y = y1 - difY * divider;
            double z = z1 - difZ * divider;

            mc.thePlayer.sendQueue.addToSendQueue(new C03PacketPlayer.C04PacketPlayerPosition(x, y, y, z, true));
        }
        mc.thePlayer.sendQueue.addToSendQueue(new C03PacketPlayer.C04PacketPlayerPosition(x2, y2, y2, z2, true));
    }

    public static void setPosition(double x, double y, double z, boolean onGround){
        mc.thePlayer.setPositionAndUpdate(x, y, z);
        mc.thePlayer.onGround = onGround;
    }

    private static double boxHeight;
    private static double boxWidth;
    public static void boxTeleport(int xPos, int yPos, int zPos) {
        if (boxWidth == 0)
            boxWidth = mc.thePlayer.boundingBox.maxX - mc.thePlayer.boundingBox.minX;
        if (boxHeight == 0)
            boxHeight = mc.thePlayer.boundingBox.maxY - mc.thePlayer.boundingBox.minY;

        mc.thePlayer.boundingBox.minX = xPos;
        mc.thePlayer.boundingBox.maxX = xPos + boxWidth;
        mc.thePlayer.boundingBox.minZ = zPos;
        mc.thePlayer.boundingBox.maxZ = zPos + boxWidth;

        mc.thePlayer.boundingBox.minY = yPos;
        mc.thePlayer.boundingBox.maxY = yPos + boxHeight;
    }
}
