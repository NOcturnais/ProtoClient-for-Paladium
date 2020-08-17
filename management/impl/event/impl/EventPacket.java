package fr.noctu.haxx.proto.management.impl.event.impl;

import fr.noctu.haxx.proto.management.impl.event.eventbus.events.Event;
import net.minecraft.network.Packet;

public class EventPacket implements Event {
    public Packet packet;
    public Side side;

    public boolean cancelled;

    public EventPacket(Packet packet, Side side){
        this.packet = packet;
        this.side = side;
    }

    public Packet getPacket(){
        return packet;
    }
    public enum Side {
        IN,
        OUT
    }
}
