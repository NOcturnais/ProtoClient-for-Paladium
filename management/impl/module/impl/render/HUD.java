package fr.noctu.haxx.proto.management.impl.module.impl.render;

import fr.noctu.haxx.proto.ProtoClient;
import fr.noctu.haxx.proto.management.impl.event.eventbus.EventTarget;
import fr.noctu.haxx.proto.management.impl.event.impl.EventKeyboard;
import fr.noctu.haxx.proto.management.impl.event.impl.EventRender2D;

import fr.noctu.haxx.proto.management.impl.module.Module;
import fr.noctu.haxx.proto.utils.Refs;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.ScaledResolution;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class HUD extends Module {
	
	public HUD() {
		super("HUD", Category.Render, true);
	}

	@Override
    public void onEnable(){
	    super.onEnable();
    }

	ScaledResolution pd = new ScaledResolution(mc, mc.displayWidth, mc.displayHeight);

	@EventTarget
	public void onRender2D(EventRender2D e) {
		ScaledResolution sr = new ScaledResolution(mc, mc.displayWidth, mc.displayHeight);

        //fr.drawString(Refs.NAME, sr.getScaledWidth()-fr.getStringWidth(Refs.NAME), sr.getScaledHeight()-10, 0x40BC0003);
        ProtoClient.instance.fontManager.getTitleFont().drawString(Refs.NAME, sr.getScaledWidth()-fr.getStringWidth(Refs.NAME)-80, sr.getScaledHeight()-10, 0xffBC0003);

        List<Module> mods = ProtoClient.instance.masterManager.moduleManager.getModules();

        Collections.sort(ProtoClient.instance.masterManager.moduleManager.getModules(), new Comparator<Module>() {
            public int compare(Module m1, Module m2) {
                if(fr.getStringWidth(m1.getDisplayName()) > fr.getStringWidth(m2.getDisplayName())) {
                    return -1;
                }
                if(fr.getStringWidth(m1.getDisplayName()) < fr.getStringWidth(m2.getDisplayName())) {
                    return 1;
                }
                return 0;
            }
        });

		int y = 0;
		for(Module m : mods){
			if(m.isToggled() && !m.getDisplayName().equalsIgnoreCase("UtilsMod")){
				Gui.drawRect(sr.getScaledWidth(), y, sr.getScaledWidth()-2, y + 16, 0xff680001);
				fr.drawString(m.getDisplayName(), sr.getScaledWidth()- fr.getStringWidth(m.getDisplayName()) - 3, y + 4, -1);
				y += 16;
			}
		}

		ProtoClient.instance.masterManager.TAB.render();
	}

	@EventTarget
    public void onKey(EventKeyboard e){
	    ProtoClient.instance.masterManager.TAB.onKeyPressed(e.getKey());
    }
}
