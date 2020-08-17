package fr.noctu.haxx.proto.management.impl.event.impl;

import fr.noctu.haxx.proto.management.impl.event.eventbus.events.Event;

public class EventMessage implements Event {
    public String msg;
    public EventMessage(String message){
        this.msg = message;
    }
}
