package fr.noctu.haxx.proto.management.impl.event.impl;

import fr.noctu.haxx.proto.management.impl.event.eventbus.events.Event;

public class EventBlockBrightness implements Event {
    public int brightness;
    public EventBlockBrightness(int br){
        this.brightness = br;
    }
}
