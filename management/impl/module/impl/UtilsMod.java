package fr.noctu.haxx.proto.management.impl.module.impl;

import fr.noctu.haxx.proto.ProtoClient;
import fr.noctu.haxx.proto.management.impl.event.eventbus.EventManager;
import fr.noctu.haxx.proto.management.impl.event.eventbus.EventTarget;
import fr.noctu.haxx.proto.management.impl.event.impl.*;
import fr.noctu.haxx.proto.management.impl.gui.impl.overrides.CMainMenu;
import fr.noctu.haxx.proto.management.impl.module.Module;
import fr.noctu.haxx.proto.others.PacketHandler;
import fr.noctu.haxx.proto.utils.ChatUtils;
import fr.noctu.haxx.proto.utils.TimeHelper;
import net.minecraft.client.gui.GuiMainMenu;
import net.minecraft.network.play.client.C01PacketChatMessage;
import org.lwjgl.input.Keyboard;

import java.awt.*;

public class UtilsMod extends Module {
    public UtilsMod(){
        super("UtilsMod", Keyboard.CHAR_NONE, Category.Movement);
    }

    @EventTarget
    public void onTick(EventTick e){new PacketHandler();
    }

    @EventTarget
    public void onGui(EventGuiOpen e){
        //String guiName = e.getGui().getClass().getName();
        //ProtoClient.instance.console.println(guiName, Color.red);

        if(e.getGui().getClass().getName().equalsIgnoreCase("fr.paladium.Jk") || e.getGui() instanceof GuiMainMenu || e.getGui().getClass().getName().equalsIgnoreCase("delta.dZW5")){
            ProtoClient.instance.console.println("Custom main menu injected !", Color.red);
            e.setGui(new CMainMenu());
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
}
