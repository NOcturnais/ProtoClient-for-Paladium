package fr.noctu.haxx.proto.management.impl.gui.impl;

import fr.noctu.haxx.proto.ProtoClient;
import fr.noctu.haxx.proto.management.impl.module.Module;
import fr.noctu.haxx.proto.utils.RenderUtils;
import net.minecraft.client.Minecraft;
import org.lwjgl.input.Keyboard;

import java.util.ArrayList;

public class PTabGui {
    private Minecraft mc = Minecraft.getMinecraft();
    public ArrayList<String> categoryArrayList = new ArrayList<>();
    public int selectedMod, selectedTab;
    private int tab;
    private int tabHeight = 20;

    public PTabGui(){
        Module.Category[] array;
        int j = (array = Module.Category.values()).length;

        for(int i = 0; i < j; i++){
            Module.Category category = array[i];
            categoryArrayList.add(category.toString().substring(0, 1) + category.toString().substring(1, category.toString().length()).toLowerCase());
        }
    }

    public void render(){
        RenderUtils.drawRoundedRect(0, 3, 70, 95, 0xffa30a0a);

        int count1 = 0;
        for(Module.Category c : Module.Category.values()){
            if(c.name().equalsIgnoreCase(categoryArrayList.get(selectedTab))){
                ProtoClient.instance.fontManager.tabGuiFont.drawStringWithShadow(c.name(), 6, tabHeight+count1*15-12, (-1));
            }else{
                ProtoClient.instance.fontManager.tabGuiFont.drawStringWithShadow(c.name(), 6, tabHeight+count1*15-12, 0xffc7c3c3);
            }
            count1++;
        }

        if(tab==1||tab==2){
            int modCount=0;
            int color;
            for(Module m : getModsForCategory()){
                if(!m.getDisplayName().equals("HUD")){
                    if(m.getState())
                        color = -1;
                    else
                        color = 0xffc7c3c3;

                    int y = modCount*10;
                    RenderUtils.drawRect(73, y, 140+getLongestModuleWidth(), y+15, 0xffa30a0a);
                    ProtoClient.instance.fontManager.tabGuiFont.drawStringWithShadow((!m.getName().equalsIgnoreCase(getModsForCategory().get(selectedMod).getDisplayName()) ? m.getDisplayName() : " >" + m.getDisplayName()), 80 , y + 2, color);
                    modCount++;
                }
            }
        }
    }

    public void onKeyPressed(int key){
        if(key== Keyboard.KEY_UP)
            up();
        if(key==Keyboard.KEY_DOWN)
            down();
        if(key==Keyboard.KEY_RIGHT)
            right();
        if(key==Keyboard.KEY_LEFT)
            left();
        if(key==Keyboard.KEY_RETURN)
            key_return();
    }

    private void key_return() {
        if(tab==1)
            getModsForCategory().get(selectedMod).toggle();
    }

    private void left() {
        if(tab==1)
            tab=0;
    }

    private void right() {
        if(tab==1)
            key_return();
        else if(tab==0){
            tab = 1;
            selectedMod=0;
        }
    }

    private void down() {
        if(tab==0) {
            if(selectedTab>categoryArrayList.size()-2)
                selectedTab=-1;
            selectedTab++;
        }else if(tab==1){
            if(selectedMod>getModsForCategory().size()-2)
                selectedMod=-1;
            selectedMod++;
        }
    }

    private void up() {
        if(tab==0) {
            if(selectedTab<1)
                selectedTab=categoryArrayList.size();
            selectedTab--;
        }else if(tab==1){
            if(selectedMod<=0)
                selectedMod=getModsForCategory().size();
            selectedMod-=1;
        }
    }

    private ArrayList<Module> getModsForCategory(){
        ArrayList<Module> modules = new ArrayList<>();
        for(Module m : ProtoClient.instance.masterManager.moduleManager.getModules()){
            if(m.getCategory() == Module.Category.valueOf(categoryArrayList.get(this.selectedTab))) modules.add(m);
        }
        return modules;
    }

    private int getLongestModuleWidth(){
        ArrayList<Integer> sizes = new ArrayList<>();
        int longest = 0;
        for(Module m : getModsForCategory()){
            if(mc.fontRenderer.getStringWidth(String.valueOf(m.getDisplayName().length() + 5))> longest)
                longest = m.getDisplayName().length() + 5;
        }
        return longest;
    }
}
