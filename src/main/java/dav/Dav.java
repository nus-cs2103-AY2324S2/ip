package dav;

import java.util.Scanner;

public class Dav {

    private static final String FILE_PATH = "./data/dav.txt";
    private static TaskList tasks;
    private static Ui ui;
    private static Storage storage;

    public Dav() {
        ui = new Ui();
        storage = new Storage(FILE_PATH);
        try {
            tasks = new TaskList(storage.load(), storage);
        } catch (DukeException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    public void run() {
        ui.greetUser();

        Scanner scanner = new Scanner(System.in);
        String userInput;

        do {
            userInput = ui.getUserInput(scanner);
            Parser.parseUserInput(userInput, tasks, ui, storage);
        } while (!userInput.equalsIgnoreCase("bye"));

        ui.exit();
    }

    public static void main(String[] args) {
        new Dav().run();
    }
}
