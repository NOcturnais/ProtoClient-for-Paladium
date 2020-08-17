package fr.noctu.haxx.proto.management.impl.module.impl.render;

import fr.noctu.haxx.proto.management.impl.module.Module;
import org.lwjgl.input.Keyboard;

public class TabGui extends Module {
    public TabGui(){
        super("TabGui", Keyboard.KEY_NONE, Category.Render, true);
    }
    @Override
    public void onEnable(){
        super.onEnable();
    }
}
