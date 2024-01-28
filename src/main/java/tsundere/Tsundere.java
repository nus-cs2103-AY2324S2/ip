package tsundere;

import java.io.IOException;

public class Tsundere {

    private Storage storage;
    private Ui ui;
    public Tsundere() {
        storage = new Storage();
        ui = new Ui();
    }

    public void run() {
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
