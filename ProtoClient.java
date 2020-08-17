package fr.noctu.haxx.proto;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import fr.noctu.haxx.proto.extconsole.Console;
import fr.noctu.haxx.proto.management.MasterManager;
import fr.noctu.haxx.proto.proxy.CommonProxy;
import fr.noctu.haxx.proto.utils.Refs;
import fr.noctu.haxx.proto.utils.TimeHelper;
import fr.noctu.haxx.proto.utils.font.FontManager;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;

import java.io.IOException;
import java.util.ArrayList;

@Mod(name = "Proto", version = Refs.VER, modid = Refs.MODID)
public class ProtoClient {
	
	@SidedProxy(clientSide = Refs.CliPROXY, serverSide = Refs.ComPROXY)
	public static CommonProxy proxy;
	
	public static ProtoClient instance;
	
	public MasterManager masterManager;
	public Console console;;
	public FontManager fontManager = new FontManager();

	public boolean firstLaunch;
    public TimeHelper timePd;

    public ArrayList<EntityPlayer> friends = new ArrayList<>();

	@EventHandler
	public void onPreInit(FMLPreInitializationEvent event) {
		instance = this;
		Display.setTitle("Proto P2 - " + Minecraft.getMinecraft().getSession().getUsername());
		proxy.preInit(event);
		masterManager = new MasterManager();
		masterManager.preInit();
	}
	
	@EventHandler
	public void onInit(FMLInitializationEvent event) throws ClassNotFoundException {
		proxy.init(event);
		masterManager.init();
    }
	
	@EventHandler
	public void onPostInit(FMLPostInitializationEvent event) throws IOException {
		proxy.postInit(event);
		masterManager.postInit();
        console = new Console();
        firstLaunch = true;
        fontManager.onStart();

        Minecraft.getMinecraft().gameSettings.guiScale=2;
    }
}
