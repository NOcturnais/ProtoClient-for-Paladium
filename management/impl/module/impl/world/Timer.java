package fr.noctu.haxx.proto.management.impl.module.impl.world;

import fr.noctu.haxx.proto.management.impl.event.eventbus.EventTarget;
import fr.noctu.haxx.proto.management.impl.event.impl.EventUpdate;
import fr.noctu.haxx.proto.management.impl.module.Module;
import net.minecraft.client.Minecraft;
import org.lwjgl.input.Keyboard;

import java.lang.reflect.Field;

public class Timer extends Module {
    public Timer(){
        super("Timer", Keyboard.KEY_NONE, Category.World);
    }

    @EventTarget
    public void onUpdate(EventUpdate e){
        try {
            Field timer = Class.forName("net.minecraft.client.Minecraft").getDeclaredField("timer");
            timer.setAccessible(true);

            Field timerSpeed = Class.forName("net.minecraft.util.Timer").getDeclaredField("timerSpeed");
            timerSpeed.setAccessible(true);
            timerSpeed.setFloat(timer, 2f);
        } catch (NoSuchFieldException | ClassNotFoundException | IllegalAccessException ex) {
            ex.printStackTrace();
        }
    }
}
