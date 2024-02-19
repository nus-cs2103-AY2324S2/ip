package lia;

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
            e.printStackTrace();
        }
    }

    /**
     * Retrieves response for user input.
     *
     * @param input The user's input.
     */
    public String getResponse(String input) {
        try {
            String response = parser.parseCommand(input);
            storage.saveTasks(tasks.getTasks());
            return response;
        } catch (Exception e) {
            e.printStackTrace();
            return "Error while processing command";
        }
    }
}
