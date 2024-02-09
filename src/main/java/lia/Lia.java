package lia;

import java.util.Scanner;

/**
 * The Lia class represents the main class for the Lia task manger.
 * It orchestrates the interaction between the user interface, task storage, and command parser.
 */
public class Lia {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private Parser parser;

    /**
     * Constructs a Lia object, initializing the user interface, storage, task list, and parser.
     * It attempts to load tasks from storage and handles any loading exceptions.
     */
    public Lia() {
        ui = new Ui();
        storage = new Storage();
        tasks = new TaskList();
        parser = new Parser(ui, tasks);

        try {
            tasks.setTasks(storage.loadTasks());
        } catch (LiaException e) {
            ui.showLoadingError();
        }
    }

    /**
     * Runs the Lia application, displaying the welcome message and processing user commands until "exit" is entered.
     */
    public void run() {
        Scanner sc = new Scanner(System.in);

        ui.showWelcomeMessage();

        while (true) {
            String input = sc.nextLine();
            if (input.equals("exit")) {
                break;
            }

            parser.parseCommand(input);
            storage.saveTasks(tasks.getTasks());
        }

        ui.showGoodbye();
    }

    public static void main(String[] args) {
        new Lia().run();
    }
}