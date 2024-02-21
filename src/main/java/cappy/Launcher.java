package cappy;

import cappy.util.Logger;
import cappy.util.Logger.LogLevel;

import javafx.application.Application;

/** A launcher class to workaround classpath issues. */
public class Launcher {
    public static void main(String[] args) {
        Logger.setLogLevel(LogLevel.DEBUG);
        Application.launch(Cappy.class, args);
    }
}
