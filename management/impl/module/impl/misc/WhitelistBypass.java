package fr.noctu.haxx.proto.management.impl.module.impl.misc;

import fr.noctu.haxx.proto.management.impl.event.eventbus.EventTarget;
import fr.noctu.haxx.proto.management.impl.module.Module;
import fr.noctu.haxx.proto.utils.Reflection;
import org.lwjgl.input.Keyboard;

public class WhitelistBypass extends Module {
    public WhitelistBypass() {
        super("WhitelistBypass", Keyboard.KEY_2, Category.Misc);
    }
    @Override
    public void onEnable(){
        super.onEnable();
    }
    @EventTarget
    public void onUpdate() throws ClassNotFoundException {
        Class AntiOverloadVerifier = Class.forName("fr.welsymc.guardiangolem.common.utils.AntiOverloadVerifier");
        if(Reflection.getFieldValue(AntiOverloadVerifier, "verify").equals(true))
            Reflection.setFieldBooleanValue(AntiOverloadVerifier, "verify", false);
    }
}
