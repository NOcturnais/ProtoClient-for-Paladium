package fr.noctu.haxx.proto.management.impl.module.impl.player;

import fr.noctu.haxx.proto.management.impl.event.eventbus.EventTarget;
import fr.noctu.haxx.proto.management.impl.event.impl.EventUpdate;
import fr.noctu.haxx.proto.management.impl.module.Module;
import net.minecraft.client.Minecraft;
import org.lwjgl.input.Keyboard;

import java.lang.reflect.Field;

public class FastUse extends Module {
    public FastUse(){
        super("FastUse", Keyboard.KEY_NONE, Category.Player);
    }

    @EventTarget
    public void onUpdate(EventUpdate e){
        try {
            Class oof = Class.forName("net.minecraft.client.Minecraft");
            Field rightClickDelayTimer = oof.getDeclaredField("rightClickDelayTimer");
            rightClickDelayTimer.setAccessible(true);
            rightClickDelayTimer.setInt(Minecraft.getMinecraft(), 0);
        } catch (ClassNotFoundException | NoSuchFieldException | IllegalAccessException ex) {
            ex.printStackTrace();
        }

    }
}
