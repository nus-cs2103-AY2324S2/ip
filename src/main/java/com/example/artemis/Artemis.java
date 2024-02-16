package com.example.artemis;

/**
 * Artemis is a simple task management application.
 * It allows users to add, list, mark as done, and delete tasks.
 */
public class Artemis {
    public static final String INVALID_INPUT = "Oops, there might be invalid input..";
    private static final String FILE_PATH = "./data/artemis.txt";
    private static final Storage STORAGE = new Storage(FILE_PATH);

    private static final Ui UI = new Ui();
    private TaskList tasks;


    /**
     * Constructs an instance of the Artemis application.
     * Initializes the application components, including the task list,
     * user interface, and storage. Attempts to load tasks from the storage,
     * and displays an error message if loading fails.
     */
    public Artemis() {
        try {
            tasks = new TaskList(STORAGE.load());
        } catch (ArtemisException e) {
            UI.showLoadingError();
            tasks = new TaskList();
        }
    }

    /**
     * Generates a response to user input by parsing the input and performing
     * corresponding actions in the Artemis application.
     *
     * @param input The user input to be processed.
     * @return A string representing the response to the user input.
     */
    public String getResponse(String input) {
        try {
            // Parse user input and perform corresponding actions
            return Parser.parseInput(input, tasks, UI, STORAGE);
        } catch (ArtemisException e) {
            return UI.showError(INVALID_INPUT);
        }
    }
}
