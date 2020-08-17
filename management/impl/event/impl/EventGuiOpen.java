package fr.noctu.haxx.proto.management.impl.event.impl;

import fr.noctu.haxx.proto.management.impl.event.eventbus.events.Event;
import net.minecraft.client.gui.GuiScreen;

public class EventGuiOpen implements Event {
	
	public GuiScreen gui;
	
	public EventGuiOpen(GuiScreen gui) {
		this.gui = gui;
	}
	
	public GuiScreen getGui() {
		return this.gui;
	}
	
	public void setGui(GuiScreen set) {
		this.gui = set;
	}

}
