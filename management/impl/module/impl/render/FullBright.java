package fr.noctu.haxx.proto.management.impl.module.impl.render;

import fr.noctu.haxx.proto.management.impl.module.Module;
import org.lwjgl.input.Keyboard;

public class FullBright extends Module {
    public FullBright(){
        super("FullBright", Keyboard.KEY_NONE, Category.Render);
    }

    @Override
    public void onEnable(){
        super.onEnable();
        mc.gameSettings.gammaSetting = 20f;
    }

    @Override
    public void onDisable(){
        super.onDisable();
        mc.gameSettings.gammaSetting = 2f;
    }
}
