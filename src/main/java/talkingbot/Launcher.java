package talkingbot;

import javafx.application.Application;
import talkingbot.logic.TalkingBot;

/**
 * Launcher class for the GUI.
 */
public class Launcher {
    /**
     * Main method for the whole application.
     * @param args Arguments to be passed.
     */
    public static void main(String[] args) {
        Application.launch(TalkingBot.class, args);
    }
}
