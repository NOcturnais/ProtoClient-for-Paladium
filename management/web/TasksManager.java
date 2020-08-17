package fr.noctu.haxx.proto.management.web;

import fr.noctu.haxx.proto.management.web.tasks.CheckLocked;
import fr.noctu.haxx.proto.management.web.tasks.CheckVersion;

import java.util.Timer;

public class TasksManager {
    public static final String versionUrl = "https://pastebin.com/raw/7ETDapWk";
    public static final String lockUrl = "https://pastebin.com/raw/QWs3ytRX";

    public void init(){
        Timer t = new Timer();

        //Registers
        t.schedule(new CheckVersion(), 0L, 1000L);
        t.schedule(new CheckLocked(), 0L, 1000L);
    }
}
