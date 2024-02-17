package jerry;

import jerry.command.ByeCommand;
import jerry.command.Command;


/**
 * Main class for the Jerry Chatbot application.
 * <p>
 * This class is responsible for initializing the application, including
 * setting up the user interface, the parser for interpreting user commands,
 * and the storage system for managing tasks. It contains the main loop that
 * reads and executes commands until the application is terminated.
 */
public class Jerry {
    private TaskList tasks;
    private final Ui ui;
    private Parser parser;
    private final String filePath;

    /**
     * Constructs a new instance of Jerry with default settings.
     * Initializes the UI, parser, and storage components, and sets the file path
     * used for storing tasks.
     */
    public Jerry() {
        filePath = "./data/jerry.txt";
        ui = new Ui();
        tasks = new TaskList(Storage.loadTasks(filePath));
        parser = new Parser(ui, tasks);
    }

    /**
     * Given the input from the user, get the response from the chatbot.
     */
    public String getResponse(String input) {
        Command command = parser.parse(input);
        String response = command.execute();

        Storage.saveTasks(tasks.getTasks(), "./data/jerry.txt");
        return "JERRY\n " + response;
    }


    /**
     * The entry point of the application.
     *
     * @param args Command line arguments passed to the application (not used).
     */
    public static void main(String[] args) {
        new Jerry().run();
    }

    /**
     * Starts the application's main loop.
     * <p>
     * In the loop, the application reads commands from the user, parses them,
     * executes them, and then waits for the next command. This process repeats
     * until a termination command is received.
     */
    public void run() {
        ui.showWelcome();
        tasks = new TaskList(Storage.loadTasks(filePath));
        parser = new Parser(ui, tasks);
        boolean isExit = false;

        while (!isExit) {
            String input = ui.readCommand();
            Command command = parser.parse(input);
            command.execute();

            Storage.saveTasks(tasks.getTasks(), "./data/jerry.txt");

            if (command instanceof ByeCommand) {
                isExit = true;
            }

        }
    }
}




