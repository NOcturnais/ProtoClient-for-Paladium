package fr.noctu.haxx.proto.management.impl.gui;

import cpw.mods.fml.common.eventhandler.EventPriority;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import fr.noctu.haxx.proto.management.impl.event.eventbus.EventManager;
import fr.noctu.haxx.proto.management.impl.event.impl.EventGuiOpen;
import fr.noctu.haxx.proto.management.impl.gui.impl.overrides.CMainMenu;
import net.minecraft.client.gui.GuiIngameMenu;

public class GuiManager {

	public GuiManager() {
		EventManager.register(this);
	}

	@SubscribeEvent(priority = EventPriority.HIGHEST)
	public void onGui(EventGuiOpen event) {

		if(event != null && event.gui != null && event.gui.getClass() != null && event.gui.getClass().getName() != null){

			if(event.gui.getClass().getName().contains("delta")) return;

			if(event.gui.getClass().getName().toLowerCase().contains("main") && event.gui.getClass().getName().toLowerCase().contains("menu"))
				event.setGui(new CMainMenu());

		}


	}
}
