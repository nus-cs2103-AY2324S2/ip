package kervyn;

import javafx.application.Application;
import kervyn.Kervyn;

/**
 * A launcher class to workaround classpath issues.
 */
public class Launcher {
    public static void main(String[] args) {
        Application.launch(Kervyn.class, args);
    }
}