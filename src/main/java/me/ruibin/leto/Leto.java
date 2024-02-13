package me.ruibin.leto;

import me.ruibin.leto.parser.Parser;
import me.ruibin.leto.parser.Result;
import me.ruibin.leto.tasklist.TaskList;
import me.ruibin.leto.ui.Ui;

/** Main program for Leto. Singleton class. */
public class Leto {
    private static final Leto leto = new Leto();

    private Leto() {
        Ui.letoLogo();
        TaskList.initFromFile();
    }

    public static Leto getLeto() {
        return leto;
    }

    public static void main(String[] args) {
        Parser.readExecuteLoop();
    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    public Result getResponse(String inputs) {
//        return "Duke heard: " + input;
        return Parser.readAndExecute(inputs);
    }
}

