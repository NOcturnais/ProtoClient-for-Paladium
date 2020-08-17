package fr.noctu.haxx.proto.management.impl.gui.impl.buttons;

import fr.noctu.haxx.proto.management.impl.gui.impl.overrides.transitions.BlazarMenu;
import fr.noctu.haxx.proto.utils.PScaledResolution;
import fr.noctu.haxx.proto.utils.RenderUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.util.ResourceLocation;

import java.awt.*;

public class PImageButton {
    protected ResourceLocation image;
    public int x, y, width, height, anim = 0, target;
    protected Minecraft mc;

    public PImageButton(ResourceLocation img, int x, int y, int width, int height, int target){
        this.image = img;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.target = target;
        this.mc = Minecraft.getMinecraft();
    }

    public void draw(int mouseX, int mouseY, Color c){
        this.hoverAnim(mouseX, mouseY);
        if(this.anim>0){
            RenderUtils.drawTexture(this.image, this.x - this.anim, this.y - this.anim, this.width + this.anim*2, this.height + this.anim*2);
        }else{
            RenderUtils.drawTexture(this.image, this.x, this.y, this.width, this.height);
        }
    }

    public void onClick(int mouseX, int mouseY){
        PScaledResolution sr = new PScaledResolution(mc);
        if(this.isHovered(mouseX, mouseY)){
            if(this.target == 1){

            }
            if(this.target == 2){
                mc.displayGuiScreen(new BlazarMenu());
            }
        }
    }

    protected void hoverAnim(int mouseX, int mouseY){
        if(this.isHovered(mouseX, mouseY)){
            if(this.anim<5)
                this.anim++;
        }else{
            if(this.anim>0)
                this.anim--;
        }
    }

    public boolean isHovered(int mouseX, int mouseY){
        return RenderUtils.isHovered(this.x, this.y, this.x + this.width, this.y + this.height, mouseX, mouseY);
    }
}
