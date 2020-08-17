package fr.noctu.haxx.proto.management.impl.module.impl.player;

import java.util.Random;

import fr.noctu.haxx.proto.management.impl.event.eventbus.EventTarget;
import fr.noctu.haxx.proto.management.impl.event.impl.EventUpdate;
import fr.noctu.haxx.proto.utils.Refs;
import fr.noctu.haxx.proto.utils.TimeHelper;
import org.lwjgl.input.Keyboard;

import fr.noctu.haxx.proto.management.impl.module.Module;
import net.minecraft.network.play.client.C01PacketChatMessage;

public class Spammer extends Module {
	
	public Spammer() {
		super("Spammer", Keyboard.KEY_K, Category.Player);
	}
	
	private TimeHelper timer = new TimeHelper();
	
	@EventTarget
	public void onUpdate(EventUpdate e) {
		if(!timer.hasReached(3050))
			return;
		timer.reset();
		
		boolean antiSpamBypass = true;
		
		String message = String.format("ProtoClient v%s by xTrM_", Refs.VER);
		
		if(antiSpamBypass) {			
			message = message + " [" + (new Random().nextInt(1000000)) + "]";
		}
		
		mc.thePlayer.sendQueue.addToSendQueue(new C01PacketChatMessage(message));
	}

}
