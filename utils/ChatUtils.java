package fr.noctu.haxx.proto.utils;

import net.minecraft.client.Minecraft;
import net.minecraft.util.ChatComponentText;

import static net.minecraft.util.EnumChatFormatting.*;

public class ChatUtils {
    public static void sendMessage(String message){
        Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText(DARK_GRAY + "[" + DARK_RED + "Proto" + RED + "Client" + DARK_GRAY + "]" + " " + WHITE + message));
    }
}
