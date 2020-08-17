package fr.noctu.haxx.proto.management.impl.module;

import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import fr.noctu.haxx.proto.ProtoClient;
import fr.noctu.haxx.proto.management.impl.event.eventbus.EventManager;
import fr.noctu.haxx.proto.management.impl.event.eventbus.EventTarget;
import fr.noctu.haxx.proto.management.impl.event.impl.*;
import fr.noctu.haxx.proto.management.impl.gui.impl.overrides.CMainMenu;
import fr.noctu.haxx.proto.management.impl.gui.impl.overrides.PCheatSelectionMenu;
import fr.noctu.haxx.proto.management.impl.module.impl.combat.*;
import fr.noctu.haxx.proto.management.impl.module.impl.misc.*;
import fr.noctu.haxx.proto.management.impl.module.impl.movement.*;
import fr.noctu.haxx.proto.management.impl.module.impl.player.*;
import fr.noctu.haxx.proto.management.impl.module.impl.render.ESP;
import fr.noctu.haxx.proto.management.impl.module.impl.render.FullBright;
import fr.noctu.haxx.proto.management.impl.module.impl.world.MiddleClickFriend;
import fr.noctu.haxx.proto.management.impl.module.impl.world.Timer;
import fr.noctu.haxx.proto.others.PacketHandler;
import fr.noctu.haxx.proto.utils.ChatUtils;
import fr.noctu.haxx.proto.utils.Refs;
import fr.noctu.haxx.proto.utils.TimeHelper;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiMainMenu;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.network.play.client.C01PacketChatMessage;

public class ModuleManager {
    protected static Minecraft mc = Minecraft.getMinecraft();
	private ArrayList<Module> modules;
	
	public ModuleManager() {
		modules = new ArrayList<>();
		EventManager.register(this);
	}
	
	public void init() throws ClassNotFoundException {
		//modules.add(new HUD());
		modules.add(new KillAura());
		modules.add(new Speed());
		modules.add(new LongJump());
		//modules.add(new WhitelistBypass());
		modules.add(new Step());
		modules.add(new Teleport());
		modules.add(new DeathBypass());
		modules.add(new NoClip());
		modules.add(new Criticals());
		modules.add(new Fly());
		modules.add(new AirJump());
		modules.add(new HighJump());
		modules.add(new NoFall());
		modules.add(new FastUse());
		modules.add(new Timer());
		modules.add(new FullBright());
		modules.add(new ESP());
		modules.add(new Aimbot());
        modules.add(new Spawn());
        modules.add(new Sprint());
        modules.add(new FastEat());
        //modules.add(new UtilsMod());
        modules.add(new Blink());
        modules.add(new PacketListener());
        modules.add(new AntiBK());
        modules.add(new AntiFire());
        modules.add(new GigaKB());
        modules.add(new GodMod());
        //modules.add(new MiddleClickFriend()); //P1.6
        modules.add(new FastLadder());
        //modules.add(new ForceOpen());
		//modules.add(new Crasher());

        //getModule("UtilsMod").toggle();
	}
	
	public ArrayList<Module> getModules() {
		return modules;
	}
	
	public Module getModule(String mod) {
		for(Module m : getModules()) {
			if(m.getName().equalsIgnoreCase(mod))
				return m;
		}
		return null;
	}
	
	public ArrayList<Module> getModulesInCategory(Module.Category cat){
		ArrayList<Module> returnable = new ArrayList<>();
		for(Module m : getModules()) {
			if(m.getCategory() == cat) {
				returnable.add(m);
			}
		}
		return returnable;
	}
	
	@EventTarget
	public void onKey(EventKeyboard e) {
		for(Module m : getModules()) {
			if(m.getKey() == e.getKey()) {
				m.toggle();
			}
		}
        ProtoClient.instance.masterManager.TAB.onKeyPressed(e.getKey());
	}

	//------------------UtilsMod-------------------------------------
    @EventTarget
    public void onTick(EventTick e){new PacketHandler();}

    @EventTarget
    public void onGui(EventGuiOpen e){
        //String guiName = e.getGui().getClass().getName();
        //ProtoClient.instance.console.println(guiName, Color.red);

        if(e.getGui().getClass().getName().equalsIgnoreCase("fr.paladium.Jk") || e.getGui() instanceof GuiMainMenu || e.getGui().getClass().getName().equalsIgnoreCase("delta.dZW5")){
            ProtoClient.instance.console.println("Custom main menu injected !", Color.red);
            e.setGui(new PCheatSelectionMenu());
            //ProtoClient.instance.timePd = new TimeHelper();
        }
    }

    @EventTarget
    public void onWorldJoin(EventWorldJoin e){
        ChatUtils.sendMessage("ProtoClient by NOctu");
        ChatUtils.sendMessage("Pour avoir les prochaines mises à jours -> https://discord.gg/cWysWeH");
    }

    @EventTarget
    public void onPacket(EventPacket e){
        if(e.getPacket() instanceof C01PacketChatMessage){
            C01PacketChatMessage oof = (C01PacketChatMessage) e.getPacket();
            EventMessage eventMessage = new EventMessage(oof.func_149439_c());
            EventManager.call(eventMessage);
        }
    }

    //------------------HUD-------------------------------------
    @EventTarget
    public void onRender2D(EventRender2D e) {
        ScaledResolution sr = new ScaledResolution(mc, mc.displayWidth, mc.displayHeight);

        //fr.drawString(Refs.NAME, sr.getScaledWidth()-fr.getStringWidth(Refs.NAME), sr.getScaledHeight()-10, 0x40BC0003);
        ProtoClient.instance.fontManager.getTitleFont().drawString(Refs.NAME, sr.getScaledWidth()-mc.fontRenderer.getStringWidth(Refs.NAME)-80, sr.getScaledHeight()-10, 0xffBC0003);

        List<Module> mods = ProtoClient.instance.masterManager.moduleManager.getModules();

        Collections.sort(ProtoClient.instance.masterManager.moduleManager.getModules(), new Comparator<Module>() {
            public int compare(Module m1, Module m2) {
                if(mc.fontRenderer.getStringWidth(m1.getDisplayName()) > mc.fontRenderer.getStringWidth(m2.getDisplayName())) {
                    return -1;
                }
                if(mc.fontRenderer.getStringWidth(m1.getDisplayName()) < mc.fontRenderer.getStringWidth(m2.getDisplayName())) {
                    return 1;
                }
                return 0;
            }
        });

        int y = 0;
        for(Module m : mods){
            if(m.isToggled()){
                Gui.drawRect(sr.getScaledWidth(), y, sr.getScaledWidth()-2, y + 16, 0xff680001);
                mc.fontRenderer.drawString(m.getDisplayName(), sr.getScaledWidth()- mc.fontRenderer.getStringWidth(m.getDisplayName()) - 3, y + 4, -1);
                y += 16;
            }
        }

        ProtoClient.instance.masterManager.TAB.render();
    }
}
