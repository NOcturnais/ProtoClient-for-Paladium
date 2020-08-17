package fr.noctu.haxx.proto.management.impl.module.impl.combat;

import fr.noctu.haxx.proto.ProtoClient;
import fr.noctu.haxx.proto.management.impl.event.eventbus.EventTarget;
import fr.noctu.haxx.proto.management.impl.event.impl.EventRender3D;
import fr.noctu.haxx.proto.management.impl.event.impl.EventUpdate;
import fr.noctu.haxx.proto.utils.RenderUtils;
import net.minecraft.entity.player.EntityPlayer;
import org.lwjgl.input.Keyboard;

import fr.noctu.haxx.proto.management.impl.module.Module;
import net.minecraft.entity.Entity;
import net.minecraft.network.play.client.C02PacketUseEntity;
import net.minecraft.network.play.client.C02PacketUseEntity.Action;

public class KillAura extends Module {

	public KillAura() {
		super("KillAura", Keyboard.KEY_R, Category.Combat);
	}

	private static EntityPlayer targetSuce = null;

	@EventTarget
	public void onUpdate(EventUpdate event) {
		try {
			for(Object o : mc.theWorld.loadedEntityList) {
				if(((Entity)o) != null && ((Entity)o) != mc.thePlayer && ((Entity)o).isEntityAlive()&&o instanceof EntityPlayer) {
				    EntityPlayer ep = (EntityPlayer) o;
				    targetSuce = ep;
				    if(!ProtoClient.instance.friends.contains(ep)) {
                        if (((Entity) o).getDistanceToEntity(mc.thePlayer) <= 6F) {
                            mc.thePlayer.swingItem();
                            mc.thePlayer.sendQueue.addToSendQueue(new C02PacketUseEntity(((Entity) o), Action.ATTACK));
                        }
                    }
				}
			}
		} catch(Exception ignored) {}
	}

	@EventTarget
    public void on3D(EventRender3D e){
        RenderUtils.renderSphere(targetSuce, 6f);
    }
}
