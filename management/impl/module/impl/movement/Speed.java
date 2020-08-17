package fr.noctu.haxx.proto.management.impl.module.impl.movement;

import fr.noctu.haxx.proto.management.impl.event.eventbus.EventTarget;
import fr.noctu.haxx.proto.management.impl.event.impl.EventUpdate;
import fr.noctu.haxx.proto.utils.MovementUtils;
import org.lwjgl.input.Keyboard;

import fr.noctu.haxx.proto.management.impl.module.Module;

public class Speed extends Module {
	public Speed() {
		super("Speed", Keyboard.KEY_M, Category.Movement);
	}
	@EventTarget
	public void onUpdate(EventUpdate e) {
		MovementUtils.setSpeed(0);
		if(MovementUtils.isMoving())
			MovementUtils.setSpeed(2);
	}

}
