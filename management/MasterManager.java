package fr.noctu.haxx.proto.management;

import fr.noctu.haxx.proto.management.impl.gui.GuiManager;
import fr.noctu.haxx.proto.management.impl.gui.impl.PTabGui;
import fr.noctu.haxx.proto.management.impl.module.ModuleManager;
import fr.noctu.haxx.proto.management.web.TasksManager;

import java.io.IOException;

public class MasterManager {

	public ModuleManager moduleManager;
	public GuiManager guiManager;
	public TasksManager tasksManager;
    public final PTabGui TAB = new PTabGui();
	
	public MasterManager() {
		moduleManager = new ModuleManager();
		guiManager = new GuiManager();
        tasksManager = new TasksManager();
	}
	
	public void preInit() {
		
	}
	
	public void init() throws ClassNotFoundException {
		moduleManager.init();
		tasksManager.init();
	}
	
	public void postInit() throws IOException {
		//Load custom icon UwU
		/*Display.setIcon(new ByteBuffer[] {
			ResourceUtils.loadIcon(new URL("http://magnatar.000webhostapp.com/uwu.png")), //16x16 pixels
			ResourceUtils.loadIcon(new URL("http://magnatar.000webhostapp.com/owo.png")), //32x32 pixels
		});*/
	}
}
