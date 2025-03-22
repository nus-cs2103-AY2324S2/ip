package zhen;
import zhen.command.Command;
/**
 * The main class of the application. This class initialize the application,
 * receive commands from users, process commands, and provide user feedbacks
 */
public class Zhen {
    private final Storage storage;
    private final TaskList tasks;
    private final Ui ui;

    /**
     * Constructs Zhen instance, initializes application's components.
     *
     * @param filePath The path the program restore previously stored tasks.
     */
    public Zhen(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        tasks = new TaskList(storage.loadTasks());
    }

    /**
     * Starts the application, uses the loop to get user's input and process.
     */
    public void run() {
        Ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (Exception e) {
                Ui.print_message(e.getMessage());
            }
        }
    }

    /**
     * Gets program's response by processing user's input.
     *
     * @param userInput Parameter not in use.
     * @return The program's response to user's input.
     */
    public String getResponse(String userInput) {
        Command c = Parser.parse(userInput);
        String response = "";
        try {
            response = c.execute(tasks, ui, storage);
        } catch (RuntimeException e) {
            response = Ui.showException(e);
        }
        return response;
    }

    /**
     * Enters the entire program, create a Zhen instance and run it.
     *
     * @param args Parameter not in use.
     */
    public static void main(String[] args) {
        new Zhen("database.ser").run();
    }
}
