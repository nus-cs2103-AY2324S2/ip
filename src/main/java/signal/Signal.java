package signal;


import javafx.application.Platform;
import signal.task.Task;

import signal.util.Parser;
import signal.util.Storage;
import signal.util.Ui;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;


public class Signal {


    private static Storage fileManager = new Storage();
    private static ArrayList<Task> taskList = fileManager.loadTasks();
    private static Ui ui = new Ui(taskList, fileManager);
    public static Parser parser = new Parser(taskList, ui);

    public Signal() {

    }
    


    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    public String getResponse(String input) {
        if (input.equals("bye")) {
            shutDown();
        }
        return parser.parse(input);
    }

    public void shutDown() {
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                Platform.exit();
                System.exit(0);
            }
        }, 1000);
    }

    public String getIntro() {
        return ui.intro();
    }


}

