package chatbot.gui;

import javafx.application.Application;

/**
 * Encapsulates a launcher class to workaround classpath issues.
 *
 * @author Huang Zhuoyan, Celeste
 * @version CS2103T AY24/25 Semester 1, G07
 */
public class Launcher {
    /**
     * Serves as the entry point for the chatbot application.
     *
     * @param args The command-line arguments passed to the Java application.
     */
    public static void main(String[] args) {
        Application.launch(Main.class, args);
    }
}