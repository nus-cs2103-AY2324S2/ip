package alpaca;

import alpaca.gui.Main;

import javafx.application.Application;

/**
 * A launcher class to workaround classpath issues.
 */
public class Alpaca {
    public static void main(String[] args) {
        Application.launch(Main.class, args);
    }
}