package fr.noctu.haxx.proto.management.impl.module.impl.movement;

import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.common.FMLCommonHandler;
import fr.noctu.haxx.proto.management.impl.event.eventbus.EventTarget;
import fr.noctu.haxx.proto.management.impl.event.impl.EventUpdate;
import fr.noctu.haxx.proto.management.impl.module.Module;
import fr.noctu.haxx.proto.utils.MovementUtils;
import fr.noctu.haxx.proto.utils.TimeHelper;
import net.minecraft.client.Minecraft;
import net.minecraft.network.play.client.C03PacketPlayer;
import net.minecraftforge.client.MinecraftForgeClient;
import net.minecraftforge.common.MinecraftForge;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.Display;

public class Fly extends Module {
    public Fly(){
        super("Fly", Keyboard.KEY_NONE, Category.Movement);
    }

    TimeHelper suceMoiFdp;
    double oldY;

    @Override
    public void onEnable(){
        super.onEnable();
        suceMoiFdp = new TimeHelper();
    }

    @Override
    public void onDisable(){
        super.onDisable();
        if(mc.thePlayer.fallDistance>2)
            mc.thePlayer.sendQueue.addToSendQueue(new C03PacketPlayer(true));
        suceMoiFdp.reset();
    }

    @EventTarget
    public void onUpdate(EventUpdate e){
        if(mc.thePlayer.onGround)
            oldY = mc.thePlayer.posY;
        mc.thePlayer.cameraYaw = 0.105f;
        if(mc.gameSettings.keyBindForward.getIsKeyPressed())
            MovementUtils.setSpeed(2f);
        else
            MovementUtils.setSpeed(0f);
        if(mc.gameSettings.keyBindJump.getIsKeyPressed())
            MovementUtils.vClip(1);
        if(mc.gameSettings.keyBindSneak.getIsKeyPressed())
            MovementUtils.vClip(-1);
        mc.thePlayer.motionY=10E-10;
        if(mc.thePlayer.fallDistance>2)
            mc.thePlayer.sendQueue.addToSendQueue(new C03PacketPlayer(true));
    }
}
