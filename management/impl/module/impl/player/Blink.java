package fr.noctu.haxx.proto.management.impl.module.impl.player;

import cpw.mods.fml.common.FMLCommonHandler;
import fr.noctu.haxx.proto.management.impl.event.eventbus.EventTarget;
import fr.noctu.haxx.proto.management.impl.event.impl.EventPacket;
import fr.noctu.haxx.proto.management.impl.module.Module;
import net.minecraft.client.Minecraft;
import net.minecraft.network.Packet;
import net.minecraft.network.play.client.C01PacketChatMessage;
import net.minecraft.network.play.client.C03PacketPlayer;
import net.minecraft.world.WorldServer;
import net.minecraftforge.common.util.FakePlayer;
import org.lwjgl.input.Keyboard;

public class Blink extends Module {
    public Blink(){
        super("Blink", Keyboard.CHAR_NONE, Category.Player);
    }

    private static FakePlayer oof;

    @Override
    public void onEnable(){
        super.onEnable();
        oof = new FakePlayer((WorldServer) Minecraft.getMinecraft().thePlayer.worldObj, mc.thePlayer.getGameProfile());
        oof.setPosition(mc.thePlayer.posX, mc.thePlayer.posY, mc.thePlayer.posZ);
        oof.inventory = mc.thePlayer.inventory;
        oof.yOffset = mc.thePlayer.yOffset;
        oof.ySize = mc.thePlayer.ySize;
        oof.rotationPitch = mc.thePlayer.rotationPitch;
        oof.rotationYaw = mc.thePlayer.rotationYaw;
        oof.rotationYawHead = mc.thePlayer.rotationYawHead;
        mc.theWorld.spawnEntityInWorld(oof);
    }

    @Override
    public void onDisable(){
        super.onDisable();
        mc.theWorld.removeEntityFromWorld(oof.getEntityId());
        oof = null;
    }

    @EventTarget
    public void onPacket(EventPacket e){
        if(e.getPacket() instanceof C03PacketPlayer)
            e.cancelled=true;
    }
}
