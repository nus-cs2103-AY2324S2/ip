package rochin;

import java.util.Scanner;



/**
 * Main class representing the RochinBot application.
 */
public class RochinBot {

    private static final String filePath = "./data/rochin.txt";
    private final Ui ui;
    private final Storage storage;
    private TaskList tasks;

    /**
     * Constructs a RochinBot with default file path and UI.
     * @throws RochinException If there is an error initializing the bot.
     */
    public RochinBot() throws RochinException {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (RochinException e) {
            ui.showError("Error loading tasks: " + e.getMessage());
            tasks = new TaskList();
        }
    }

    /**
     * Constructs a RochinBot with specified file path and UI.
     * @param filePath The file path for task storage.
     * @param ui The user interface.
     * @throws RochinException If there is an error initializing the bot.
     */
    public RochinBot(String filePath, Ui ui) throws RochinException {
        this.ui = ui;
        this.storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (RochinException e) {
            ui.showError("Error loading tasks: " + e.getMessage());
            tasks = new TaskList();
        }
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
    public static void main(String[] args) throws RochinException {
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
            CommandProcessor commandProcessor = new CommandProcessor();

            if (commandProcessor.isExitCommand(userInput)) {
                break;
            }

            commandProcessor.process(userInput, tasks, ui);
        }

        scanner.close();
    }

    /**
     * Method to load tasks from the storage.
     */
    public void loadTasks() {
        try {
            tasks = new TaskList(storage.load());
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

    /**
     * Retrieves a response based on the provided input string.
     * @param input The input string to be processed.
     * @return The response generated based on the input.
     * @throws RochinException If there is an error in parsing or executing the command.
     */
    String getResponse(String input) throws RochinException {
        try {
            CommandProcessor.process(input, tasks, ui);
            storage.save(tasks.convertTasksToStrings());
        } catch (RochinException e) {
            ui.showError(e.getMessage());
        }
        return ui.ReplyMessage();
    }

    /**
     * Displays a welcome message.
     * @return The welcome message.
     */
    static String showWelcomeMsg() {
        return "Hello! I'm Rochin.\n"
                + "What can I do for you?\n";
    }

}

