package fr.noctu.haxx.proto.management.impl.gui.impl.overrides;

import fr.noctu.haxx.proto.management.impl.gui.impl.buttons.PImageButton;
import fr.noctu.haxx.proto.utils.PScaledResolution;
import fr.noctu.haxx.proto.utils.RenderUtils;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

import java.awt.*;
import java.util.ArrayList;

public class PCheatSelectionMenu extends GuiScreen {
    protected ArrayList<PImageButton> imageButtons = new ArrayList<>();

    @Override
    public void initGui(){
        PScaledResolution sr = new PScaledResolution(mc);
        this.imageButtons.clear();
        this.imageButtons.add(new PImageButton(new ResourceLocation("proto", "textures/gui/proto.png"), sr.getScaledWidth()/2/2+30, sr.getScaledHeight()/2/2+40, sr.getScaledWidth()/6-50, sr.getScaledHeight()/3, 1));
        this.imageButtons.add(new PImageButton(new ResourceLocation("proto", "textures/gui/blazar.png"), sr.getScaledWidth()/2+120, sr.getScaledHeight()/2/2+40, sr.getScaledWidth()/6, sr.getScaledHeight()/3+12, 2));
        super.initGui();
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        PScaledResolution sr = new PScaledResolution(mc);

        RenderUtils.drawRect(0, 0, sr.getScaledWidth(), sr.getScaledHeight(), 0xff1a1919);
        RenderUtils.drawRectOnBorders(sr, 4, -1);
        GL11.glMatrixMode(GL11.GL_TEXTURE);
        GL11.glEnable(GL11.GL_TEXTURE_2D);
        GL11.glEnable(GL11.GL_BLEND);
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
        GL11.glPushMatrix();
        RenderUtils.drawTexture(new ResourceLocation("proto", "textures/gui/protoportal.png"), sr.getScaledWidth()/2-200, -70, 400, 300);

        for(PImageButton imageButton : imageButtons){
            imageButton.draw(mouseX, mouseY, Color.WHITE);
        }

        super.drawScreen(mouseX, mouseY, partialTicks);
    }

    @Override
    protected void actionPerformed(GuiButton button) {

        super.actionPerformed(button);
    }

    protected void mouseClicked(int mouseX, int mouseY, int mouseButton){
        PScaledResolution sr = new PScaledResolution(mc);
        for(PImageButton imageButton : imageButtons)
            imageButton.onClick(mouseX, mouseY);
        super.mouseClicked(mouseX, mouseY, mouseButton);
    }
}
