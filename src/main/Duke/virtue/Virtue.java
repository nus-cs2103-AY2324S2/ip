package virtue;

import java.io.IOException;
import java.util.Scanner;

public class Virtue {
    private Ui ui;
    private Storage storage;

    // The task list to be used by the chatbot.
    private VirtueTaskList taskList;

    public Virtue() {
        storage = new Storage();
    }

    // Runs the chatbot.
    private void run() {
        try {
            taskList = storage.loadTaskList();
            ui = new Ui(taskList, storage);
            ui.run();
        } catch (VirtueDateTimeException e) {
            System.out.println("OOPS! There is a date not in the correct format.");
        }
    }

    public static void main(String[] args) {
        Virtue virtue = new Virtue();
        virtue.run();
    }
}
