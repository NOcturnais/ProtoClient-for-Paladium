package fr.noctu.haxx.proto.management.impl.gui.impl.overrides.transitions;

import fr.noctu.haxx.proto.management.impl.gui.impl.buttons.PImageButton;
import fr.noctu.haxx.proto.utils.PScaledResolution;
import fr.noctu.haxx.proto.utils.RenderUtils;
import fr.noctu.haxx.proto.utils.anims.Translate;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class BlazarMenu extends GuiScreen {
    static PScaledResolution pd = new PScaledResolution(Minecraft.getMinecraft());
    public static Translate translate = new Translate(0, 0);
    public static Translate translate2 = new Translate(0, 0);
    public static Translate line1 = new Translate(0, 0);
    public static Translate line2 = new Translate(pd.getScaledWidth(), 0);
    public static Translate logo = new Translate(-50, 0);

    @Override
    public void initGui(){
        super.initGui();
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        PScaledResolution sr = new PScaledResolution(mc);
        translate.interpolate(sr.getScaledWidth(), 0, 3);
        translate2.interpolate(sr.getScaledWidth(), 0, 1.5f);
        Gui.drawRect(0, 0, (int) translate.getX(), sr.getScaledHeight(), 0xff171717);
        Gui.drawRect(0, sr.getScaledWidth()/2-300, (int) translate2.getX(), sr.getScaledWidth()/2-150, 0xff363636); //Bon flemme de modif mais qu'est-ce que j'ai foutu ptdr
        if(translate2.getX() == sr.getScaledWidth()){
            line1.interpolate(sr.getScaledWidth()-200, 0, 5);
            RenderUtils.drawLine("hor", 0, 40, (int) line1.getX(), 7, -1);
            if(line1.getX() == sr.getScaledWidth()-200){
                line2.interpolate(200, 0, 5);
                RenderUtils.drawLine("hor", sr.getScaledWidth(), sr.getScaledHeight()-40, (int) line2.getX(), 7, -1);
                if(line2.getX() == 200){
                    logo.interpolate(40, 0, 3f);
                    GL11.glMatrixMode(GL11.GL_TEXTURE);
                    GL11.glEnable(GL11.GL_TEXTURE_2D);
                    GL11.glEnable(GL11.GL_BLEND);
                    GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
                    GL11.glPushMatrix();
                    RenderUtils.drawTexture(new ResourceLocation("proto", "textures/gui/blazar.png"), (int) logo.getX(), sr.getScaledHeight()/2-103, 140, 160);
                }
            }
        }
        super.drawScreen(mouseX, mouseY, partialTicks);
    }
}
