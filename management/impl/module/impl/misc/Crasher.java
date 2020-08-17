package fr.noctu.haxx.proto.management.impl.module.impl.misc;

import fr.noctu.haxx.proto.management.impl.event.eventbus.EventTarget;
import fr.noctu.haxx.proto.management.impl.event.impl.EventUpdate;
import fr.noctu.haxx.proto.management.impl.module.Module;
import org.lwjgl.input.Keyboard;

import java.io.IOException;

public class Crasher extends Module {
    public Crasher() {
        super("Crasher", Keyboard.KEY_L, Category.Misc);
    }


    @EventTarget
    public void onUpdate(EventUpdate e) throws IOException {
        //Nop qu'en version de dev
    }

    private int generateRandomInt(){
        return (int) (Math.random() * (30000 - -30000)) + -30000;
    }
}
