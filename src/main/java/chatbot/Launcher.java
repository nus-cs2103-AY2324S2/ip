package chatbot;

import javafx.application.Application;

/**
 * A launcher class to workaround classpath issues.
 * Reused from {@code https://se-education.org/guides/tutorials/javaFx.html}.
 *
 * @author Titus Chew
 */
public class Launcher {
    public static void main(String[] args) {
        Application.launch(ChatBot.class, args);
    }
}
