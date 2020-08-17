package fr.noctu.haxx.proto.management.web.tasks;

import fr.noctu.haxx.proto.management.web.TasksManager;
import fr.noctu.haxx.proto.utils.Refs;
import fr.noctu.haxx.proto.utils.TimeHelper;
import fr.noctu.haxx.proto.utils.WebUtils;

import java.io.IOException;
import java.net.URL;
import java.util.TimerTask;

public class CheckVersion extends TimerTask {
    TimeHelper notifLong;

    @Override
    public void run() {
        try {
            String oVers = WebUtils.getPageContent(new URL(TasksManager.versionUrl));
            if(!oVers.equals(Refs.VER))
                Refs.NAME = "NOUVELLE UPDATE DISPO";
            else {
                Refs.NAME = "Proto-Client";
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
