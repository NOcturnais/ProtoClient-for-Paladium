package fr.noctu.haxx.proto.management.impl.module.impl.misc;

import fr.noctu.haxx.proto.management.impl.event.eventbus.EventTarget;
import fr.noctu.haxx.proto.management.impl.event.impl.EventUpdate;
import fr.noctu.haxx.proto.management.impl.module.Module;
import net.minecraft.block.Block;
import net.minecraft.block.BlockChest;
import net.minecraft.util.MovingObjectPosition;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;

public class ForceOpen extends Module {
    public ForceOpen(){
        super("ChestContent", Keyboard.CHAR_NONE, Category.Misc);
    }

    @EventTarget
    public void onUpdate(EventUpdate e){
        MovingObjectPosition salePUTE = mc.objectMouseOver;
        if(salePUTE.typeOfHit == MovingObjectPosition.MovingObjectType.BLOCK && Mouse.isButtonDown(1)){
            Block blockPUTE = mc.theWorld.getBlock(salePUTE.blockX, salePUTE.blockY, salePUTE.blockZ);
            if(blockPUTE instanceof BlockChest){
                BlockChest chestPUTE = (BlockChest) blockPUTE;
                mc.thePlayer.displayGUIChest(chestPUTE.func_149951_m(mc.theWorld, salePUTE.blockX, salePUTE.blockY, salePUTE.blockZ));
            }
        }
    }
}
