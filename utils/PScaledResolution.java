package fr.noctu.haxx.proto.utils;

import net.minecraft.client.Minecraft;
import net.minecraft.util.MathHelper;

public class PScaledResolution {
    private int scaledWidth;
    private int scaledHeight;
    private double scaledWidthD;
    private double scaledHeightD;
    private int scaleFactor;
    private static final String __OBFID = "CL_00000666";

    public PScaledResolution(Minecraft p_i1094_1_)
    {
        this.scaledWidth = p_i1094_1_.displayWidth;
        this.scaledHeight = p_i1094_1_.displayHeight;
        this.scaleFactor = 1;
        boolean flag = p_i1094_1_.func_152349_b();
        int k = p_i1094_1_.gameSettings.guiScale;

        if (k == 0)
        {
            k = 1000;
        }

        while (this.scaleFactor < k && this.scaledWidth / (this.scaleFactor + 1) >= 320 && this.scaledHeight / (this.scaleFactor + 1) >= 240)
        {
            ++this.scaleFactor;
        }

        if (flag && this.scaleFactor % 2 != 0 && this.scaleFactor != 1)
        {
            --this.scaleFactor;
        }

        this.scaledWidthD = (double)this.scaledWidth / (double)this.scaleFactor;
        this.scaledHeightD = (double)this.scaledHeight / (double)this.scaleFactor;
        this.scaledWidth = MathHelper.ceiling_double_int(this.scaledWidthD);
        this.scaledHeight = MathHelper.ceiling_double_int(this.scaledHeightD);
    }

    public int getScaledWidth()
    {
        return this.scaledWidth;
    }

    public int getScaledHeight()
    {
        return this.scaledHeight;
    }

    public double getScaledWidth_double()
    {
        return this.scaledWidthD;
    }

    public double getScaledHeight_double()
    {
        return this.scaledHeightD;
    }

    public int getScaleFactor()
    {
        return this.scaleFactor;
    }
}
