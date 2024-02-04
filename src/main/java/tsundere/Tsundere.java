package tsundere;

import tsundere.task.Storage;


import javafx.application.Application;

public class Tsundere {

    public static Storage storage;

    /**
     * Initializes Tsundere with Storage
     */
    public Tsundere() {
        storage = new Storage();
    }

    public static void main(String[] args) {
        Application.launch(Main.class, args);
    }
}
