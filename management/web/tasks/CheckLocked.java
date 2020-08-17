package fr.noctu.haxx.proto.management.web.tasks;

import fr.noctu.haxx.proto.management.web.TasksManager;
import fr.noctu.haxx.proto.utils.GameUtils;
import fr.noctu.haxx.proto.utils.WebUtils;

import java.io.IOException;
import java.net.URL;
import java.util.TimerTask;

public class CheckLocked extends TimerTask {
    @Override
    public void run() {
        try {
            String locked = WebUtils.getPageContent(new URL(TasksManager.lockUrl));
            if(locked.equalsIgnoreCase("true"))
                GameUtils.closeGame();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
