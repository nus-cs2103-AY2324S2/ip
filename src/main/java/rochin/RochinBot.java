package rochin;

import java.util.Scanner;


/**
 * Main class representing the RochinBot application.
 */
public class RochinBot {

    private static final String FILE_PATH = "./data/rochin.txt";
    private final Ui ui;
    private final Storage storage;
    private final TaskList tasks;

    public RochinBot() {
        ui = new Ui();
        storage = new Storage(FILE_PATH);
        tasks = new TaskList();
    }

    /**
     * Method to start the RochinBot application.
     */
    public void run() {
        ui.showWelcomeMessage();
        loadTasks();
        processCommands();
        saveTasks();
        ui.showGoodbyeMessage();
    }

    /**
     * Main method to launch the RochinBot application.
     *
     * @param args Command-line arguments (not used).
     */
    public static void main(String[] args) {
        new RochinBot().run();
    }

    /**
     * Method to process user commands.
     */
    public void processCommands() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            ui.showCommandPrompt();
            String userInput = scanner.nextLine();
            CommandProcessor commandProcessor = new CommandProcessor(userInput);

            if (commandProcessor.isExitCommand()) {
                break;
            }

            commandProcessor.process(tasks, ui);
        }

        scanner.close();
    }

    /**
     * Method to load tasks from the storage.
     */
    public void loadTasks() {
        try {
            tasks.load(storage.load());
        } catch (RochinException e) {
            ui.showLoadingError();
        }
    }

    /**
     * Method to save tasks to the storage.
     */
    public void saveTasks() {
        try {
            storage.save(tasks.convertTasksToStrings());
        } catch (RochinException e) {
            ui.showSavingError();
        }
    }

}

