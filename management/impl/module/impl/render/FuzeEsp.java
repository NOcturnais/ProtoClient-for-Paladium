package fr.noctu.haxx.proto.management.impl.module.impl.render;

import fr.noctu.haxx.proto.management.impl.event.eventbus.EventTarget;
import fr.noctu.haxx.proto.management.impl.event.impl.EventRender3D;
import fr.noctu.haxx.proto.management.impl.module.Module;
import fr.noctu.haxx.proto.utils.RenderUtils;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.input.Keyboard;
import org.lwjgl.util.vector.Vector3f;

public class FuzeEsp extends Module {
    public FuzeEsp() {
        super("FuzeEsp", Keyboard.KEY_NONE, Category.Render);
    }

    @EventTarget
    public void on3D(EventRender3D ev){
        for(Object o : mc.theWorld.loadedEntityList){
            if(o != mc.thePlayer) {
                EntityPlayer ep = (EntityPlayer) o;

                Vector3f vector3f = getEntCoord(ep, ev.getPartialTicks());
                //RenderUtils.drawTexture(new ResourceLocation(""));
            }
        }
    }

    public static Vector3f getEntCoord(Entity entity, float partialTicks){
        float xPos = (float) ((entity.lastTickPosX + (entity.posX - entity.lastTickPosX) * partialTicks) - RenderManager.renderPosX);
        float yPos = (float) ((entity.lastTickPosY + (entity.posY - entity.lastTickPosY) * partialTicks) - RenderManager.renderPosY);
        float zPos = (float) ((entity.lastTickPosZ + (entity.posZ - entity.lastTickPosZ) * partialTicks) - RenderManager.renderPosZ);
        return new Vector3f(xPos,yPos,zPos);
    }
}
