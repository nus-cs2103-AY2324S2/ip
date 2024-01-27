package com.example.artemis;

import java.util.Scanner;

/**
 * Artemis is a simple task management application.
 * It allows users to add, list, mark as done, and delete tasks.
 */
public class Artemis {
    // File path for storing tasks data
    private static final String FILE_PATH = "./data/artemis.txt";

    // Components of Artemis
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Constructor for Artemis class.
     *
     * @param filepath The file path for storing tasks data.
     */
    public Artemis(String filepath) {
        ui = new Ui();
        storage = new Storage(filepath);
        try {
            tasks = new TaskList(storage.load());
        } catch (ArtemisException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    /**
     * Runs the Artemis application, handling user input and performing tasks.
     */
    public void run() {
        Scanner sc = new Scanner(System.in);

        ui.showWelcomeMessage();

        while (true) {
            String input = sc.nextLine();
            try {
                // Parse user input and perform corresponding actions
                Parser.parseInput(input, tasks, ui, storage);
            } catch (ArtemisException e) {
                ui.showError("Oops, there might be invalid input..");
            }
            // Check if the user entered "bye" to exit the application
            if (input.contains("bye")) {
                break;
            }
        }
        sc.close();
    }

    /**
     * Main method to start the Artemis application.
     *
     * @param args Command-line arguments (not used in this application).
     */
    public static void main(String[] args) {
        new Artemis(FILE_PATH).run();
    }
}
