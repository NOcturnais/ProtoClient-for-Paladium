package fr.noctu.haxx.proto.utils;

import cpw.mods.fml.common.FMLCommonHandler;
import net.minecraft.client.Minecraft;

public class GameUtils {
    private static Minecraft mc = Minecraft.getMinecraft();
    public static void closeGame(){
        FMLCommonHandler.instance().exitJava(0, true);
    }
}
