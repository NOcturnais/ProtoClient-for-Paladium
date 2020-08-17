package fr.noctu.haxx.proto.management.impl.event.impl;

import fr.noctu.haxx.proto.management.impl.event.eventbus.events.Event;
import net.minecraft.entity.player.EntityPlayer;

public class EntityHitEvent implements Event {
    private EntityPlayer player;
    public EntityHitEvent(EntityPlayer ep){
        this.player = ep;
    }

    public EntityPlayer getPlayer(){
        return this.player;
    }
}
