package fr.noctu.haxx.proto.management.impl.event;

import fr.noctu.haxx.proto.management.impl.event.eventbus.EventManager;
import fr.noctu.haxx.proto.management.impl.event.impl.*;
import io.netty.channel.ChannelDuplexHandler;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import org.lwjgl.input.Keyboard;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.InputEvent.KeyInputEvent;
import cpw.mods.fml.common.gameevent.TickEvent.PlayerTickEvent;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraftforge.client.event.GuiOpenEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import net.minecraftforge.event.entity.living.LivingEvent;

/**
 * Holy grail of 1.7.10 Forge Hacks 
 * Transmitting Forge Events to our EventBus
 */
public class EventTransmitter {

    @SubscribeEvent
    public void onGuiOpen(GuiOpenEvent event) {
        EventGuiOpen e = new EventGuiOpen(event.gui);
        EventManager.call(e);
        event.gui = e.getGui();
    }

    @SubscribeEvent
    public void onKey(KeyInputEvent event) {
        if (!Keyboard.getEventKeyState()) {
            return;
        }
        EventKeyboard e = new EventKeyboard(Keyboard.getEventKey());
        EventManager.call(e);
    }

    @SubscribeEvent
    public void onRender2D(RenderGameOverlayEvent event) {
        if (event.type == RenderGameOverlayEvent.ElementType.TEXT) {
            EventRender2D e = new EventRender2D(event.partialTicks);
            EventManager.call(e);
        }
    }

    @SubscribeEvent
    public void onRender3D(RenderWorldLastEvent event) {
        EventRender3D e = new EventRender3D(event.partialTicks);
        EventManager.call(e);
    }

    @SubscribeEvent
    public void onTick(PlayerTickEvent event) {
        EventTick e = new EventTick();
        EventManager.call(e);
    }

    @SubscribeEvent
    public void onUpdate(LivingEvent.LivingUpdateEvent event) {
        if (event.entityLiving instanceof EntityPlayerSP) {
            EventUpdate e = new EventUpdate();
            EventManager.call(e);
        }
    }

    @SubscribeEvent
    public void onJump(LivingEvent.LivingJumpEvent e) {
        if (e.entityLiving instanceof EntityPlayerSP) {
            EventJump eventJump = new EventJump();
            EventManager.call(eventJump);
        }
    }

    @SubscribeEvent
    public void onEntityHit(LivingAttackEvent e) {
        if (e.entity instanceof EntityPlayer && !(e.entity instanceof EntityPlayerSP)) {
            EntityHitEvent eventHitEntity = new EntityHitEvent((EntityPlayer) e.entity);
            EventManager.call(eventHitEntity);
        }
    }

    @SubscribeEvent
    public void onWorldJoin(EntityJoinWorldEvent e){
        if(e.entity instanceof EntityPlayerSP){
            EventWorldJoin ev = new EventWorldJoin();
            EventManager.call(ev);
        }
    }
}
