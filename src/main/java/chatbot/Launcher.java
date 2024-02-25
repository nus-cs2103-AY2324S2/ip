package chatbot;

import javafx.application.Application;
/**
 * A launcher class to workaround classpath issues.
 */
public class Launcher {
    public static void main(String[] args) {
        try {
            Application.launch(AlfredGui.class, args);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
