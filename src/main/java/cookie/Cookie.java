package cookie;

import cookie.command.Command;
import cookie.command.Parser;
import cookie.command.Storage;
import cookie.task.TaskList;
import cookie.ui.Ui;

/**
 * Represents the main Cookie application class responsible for running the program.
 */
public class Cookie {
    private Ui ui;
    private Storage storage;
    private TaskList tasks;
    protected boolean isExit = false;

    /**
     * Constructs a Cookie object with the specified file path.
     */
    public Cookie() {
        ui = new Ui();
        storage = new Storage("./tasks.txt");
        tasks = new TaskList();
        storage.getFileContents(tasks);
    }

    /**
     * Processes the user input and generates a response.
     *
     * This method parses the user input, executes the corresponding command,
     * and generates a response message based on the command execution.
     * If the command indicates that the program should exit, it sets the {@code isExit}
     * flag accordingly.
     *
     * @param input The user input to be processed.
     * @return The response message generated based on the user input and command execution.
     * @throws CookieException If an error occurs during command parsing or execution.
     */
    public String getResponse(String input) throws CookieException {
        try {
            Command command = Parser.parseCommand(input);
            isExit = command.isBye();
            return command.executeAndReply(ui, tasks, storage);
        } catch (CookieException e) {
            System.out.println(e.getMessage());
            return e.getMessage();
        }
    }

    /**
     * Runs the Cookie application.
     */
    public void run() {
        System.out.println(ui.showWelcomeMessage());
    }

    /**
     * The entry point for running the Cookie application.
     *
     * @param args The command-line arguments.
     */
    public static void main(String[] args) {
        new Cookie().run();
    }

}

