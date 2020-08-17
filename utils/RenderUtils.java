package fr.noctu.haxx.proto.utils;

import fr.noctu.haxx.proto.ProtoClient;
import fr.noctu.haxx.proto.management.impl.event.eventbus.EventTarget;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;
import org.lwjgl.util.glu.Sphere;

import java.awt.*;

public class RenderUtils {
    private static Minecraft mc = Minecraft.getMinecraft();

    public static void drawImage(ResourceLocation image, int x, int y, int xOffset, int yOffset,int width, int height) {
        Minecraft.getMinecraft().getTextureManager().bindTexture(image);
        Minecraft.getMinecraft().ingameGUI.drawTexturedModalRect(x, y, xOffset, yOffset, width, height);
    }

    public static void glColor(float red, float green, float blue, float alpha) {

        GL11.glColor4f(red, green, blue, alpha);

    }

    public static void glColor(Color color) {

        GL11.glColor4f((float) color.getRed() / 255F, (float) color.getGreen() / 255F, (float) color.getBlue() / 255F, (float) color.getAlpha() / 255F);

    }

    public static void glColor(int color) {

        GL11.glColor4f((float) (color >> 16 & 255) / 255F, (float) (color >> 8 & 255) / 255F, (float) (color & 255) / 255F, (float) (color >> 24 & 255) / 255F);

    }
    public static void drawRect(float x, float y, float x1, float y1, int color) {
        Gui.drawRect((int)x, (int)y, (int)x1, (int)y1, color);
    }

    public static void drawRoundedRect(double xCoord, double yCoord, double xSize, double ySize, int colour) {
        float width = (float) (xCoord + xSize);
        float height = (float) (yCoord + ySize);
        drawRect((float)xCoord + 1, (float)yCoord, (float)width - 1, (float)height, colour);
        drawRect((float)xCoord, (float)yCoord + 1, width, height - 1, colour);
    }

    public static void drawEspBoxOutlines(Entity entity, AxisAlignedBB p_147590_0_, int p_147590_1_){

        if(entity instanceof EntityPlayerSP)
            return;

        Tessellator tessellator = Tessellator.instance;
        tessellator.startDrawing(3);

        if (p_147590_1_ != -1)
        {
            tessellator.setColorOpaque_I(p_147590_1_);
        }

        double x = entity.boundingBox.minX - 0.05 - RenderManager.renderPosX;
        double y = entity.boundingBox.minY - RenderManager.renderPosY;
        double z = entity.boundingBox.minZ - 0.05 - RenderManager.renderPosZ;
        double maxX = x + entity.width + 0.1;
        double maxY = y + entity.height + 0.1;
        double maxZ = z + entity.width + 0.1;

        tessellator.addVertex(x, y, z);
        tessellator.addVertex(maxX, y, z);
        tessellator.addVertex(maxX, y, maxZ);
        tessellator.addVertex(x, y, maxZ);
        tessellator.addVertex(x, y, z);
        tessellator.draw();
        tessellator.startDrawing(3);

        if (p_147590_1_ != -1)
        {
            tessellator.setColorOpaque_I(p_147590_1_);
        }

        tessellator.addVertex(x, maxY, z);
        tessellator.addVertex(maxX, maxY, z);
        tessellator.addVertex(maxX, maxY, maxZ);
        tessellator.addVertex(x, maxY, maxZ);
        tessellator.addVertex(x, maxY, z);
        tessellator.draw();
        tessellator.startDrawing(1);

        if (p_147590_1_ != -1)
        {
            tessellator.setColorOpaque_I(p_147590_1_);
        }

        tessellator.addVertex(x, y, z);
        tessellator.addVertex(x, maxY, z);
        tessellator.addVertex(maxX, y, z);
        tessellator.addVertex(maxX, maxY, z);
        tessellator.addVertex(maxX, y, maxZ);
        tessellator.addVertex(maxX, maxY, maxZ);
        tessellator.addVertex(x, y, maxZ);
        tessellator.addVertex(x, maxY, maxZ);
        tessellator.draw();
    }

    public static void drawTexture(ResourceLocation rl, int x, int y, int width, int height){
        mc.renderEngine.bindTexture(rl);
        Tessellator tessellator = Tessellator.instance;
        tessellator.startDrawingQuads();
        tessellator.addVertexWithUV(x        , y + height, 0, 0.0, 1.0);
        tessellator.addVertexWithUV(x + width, y + height, 0, 1.0, 1.0);
        tessellator.addVertexWithUV(x + width, y         , 0, 1.0, 0.0);
        tessellator.addVertexWithUV(x        , y         , 0, 0.0, 0.0);
        tessellator.draw();
    }

    public static void drawGradientSideways(double left, double top, double right, double bottom, int col1, int col2) {
        float f = (col1 >> 24 & 0xFF) / 255.0F;
        float f1 = (col1 >> 16 & 0xFF) / 255.0F;
        float f2 = (col1 >> 8 & 0xFF) / 255.0F;
        float f3 = (col1 & 0xFF) / 255.0F;

        float f4 = (col2 >> 24 & 0xFF) / 255.0F;
        float f5 = (col2 >> 16 & 0xFF) / 255.0F;
        float f6 = (col2 >> 8 & 0xFF) / 255.0F;
        float f7 = (col2 & 0xFF) / 255.0F;
        GL11.glEnable(3042);
        GL11.glDisable(3553);
        GL11.glBlendFunc(770, 771);
        GL11.glEnable(2848);
        GL11.glShadeModel(7425);

        GL11.glPushMatrix();
        GL11.glBegin(7);
        GL11.glColor4f(f1, f2, f3, f);
        GL11.glVertex2d(left, top);
        GL11.glVertex2d(left, bottom);

        GL11.glColor4f(f5, f6, f7, f4);
        GL11.glVertex2d(right, bottom);
        GL11.glVertex2d(right, top);
        GL11.glEnd();
        GL11.glPopMatrix();

        GL11.glEnable(3553);
        GL11.glDisable(3042);
        GL11.glDisable(2848);
        GL11.glShadeModel(7424);
        GL11.glColor4d(255, 255, 255, 255);
    }

    private static int lineProgression1;
    private static int lineProgression2;
    private static int lineProgression3;
    private static int lineProgression4;
    public static void drawRectOnBorders(PScaledResolution sr, int lineSize, int color){
        drawRect(0, 0, lineProgression1, lineSize, color); //top line
        drawRect(sr.getScaledWidth(), 0, sr.getScaledWidth()-lineSize, lineProgression2, color); //right line
        drawRect(0, sr.getScaledHeight(), lineProgression3, sr.getScaledHeight()-lineSize, color); //bottom line
        drawRect(0, 0, lineSize, lineProgression4, color); //left line
        if(lineProgression1<sr.getScaledWidth())
            lineProgression1 += 18;
        else{
            if(lineProgression2<sr.getScaledHeight())
                lineProgression2 += 16;
        }

        if(lineProgression4<sr.getScaledHeight())
            lineProgression4 += 16;
        else{
            if(lineProgression3<sr.getScaledWidth())
                lineProgression3+=18;
        }
    }

    public static boolean isHovered(double x, double y, double width, double height, int mouseX,  int mouseY){
        return mouseX>x&&mouseY>y&&mouseX<width&&mouseY<height;
    }

    private static Sphere s = new Sphere();
    public static void renderSphere(Entity entity, float rad) {

        float x = (float) entity.boundingBox.minX - 0.05f - (float) RenderManager.renderPosX;
        float y = (float) entity.boundingBox.minY - (float) RenderManager.renderPosY;
        float z = (float) entity.boundingBox.minZ - 0.05f - (float) RenderManager.renderPosZ;

        GL11.glPushMatrix();
        GL11.glTranslatef(x, (y + 1.0f), z);
        GL11.glColor4f(189, 68, 68, 0.5f);
        GL11.glEnable(3042);
        GL11.glDisable(2929);
        GL11.glDepthMask(true);
        GL11.glDisable(3553);
        GL11.glDisable(2896);
        GL11.glLineWidth(0.5f);
        s.setDrawStyle(100011);
        float radius = rad;
        s.draw(radius, 32, 32);
        GL11.glEnable(3553);
        GL11.glPopMatrix();
    }

    public static void drawLine(String hororver, int x, int y, int size, int radius, int color){
        if(hororver.equalsIgnoreCase("ver")){
            RenderUtils.drawRect(x, y, x+radius, size, color);
        }else if(hororver.equalsIgnoreCase("hor")){
            RenderUtils.drawRect(x, y, size, y+radius, color);
        }
    }
}
