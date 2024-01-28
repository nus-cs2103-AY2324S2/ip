package tsundere;

import tsundere.task.Storage;
import tsundere.ui.Ui;

import java.io.IOException;

public class Tsundere {

    private final Storage storage;
    private final Ui ui;

    public Tsundere() {
        storage = new Storage();
        ui = new Ui();
    }

    private void run() {
        ui.chat();
        try {
            storage.saveTasksToFile();
        } catch (IOException e) {
            System.out.println("Something went wrong with saving your current session data!");
        }
    }

    public static void main(String[] args) {
        new Tsundere().run();
    }
}
