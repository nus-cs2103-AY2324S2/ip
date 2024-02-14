package iggly;

import java.util.Scanner;

import iggly.command.Command;
import iggly.command.ExitCommand;
import iggly.command.GreetCommand;
import iggly.duke.DukeException;
import iggly.duke.Parser;
import iggly.duke.Storage;
import iggly.javafx.Launcher;
import iggly.model.TaskList;

/**
 * {@link Duke} is the main class of this program.
 *
 * @author Tsui Yi Wern (yiwern5)
 */
public class Duke {
    private final TaskList taskList;
    private final Storage storage;
    private boolean isExit;

    /**
     * Constructor for {@link Duke} to initialize the storage or
     * get task list from the storage.
     *
     * @param filePath the file path of the storage.
     */
    public Duke(String filePath) {
        this.storage = new Storage(filePath);
        this.isExit = false;
        if (storage.isExistingFile()) {
            this.taskList = new TaskList(storage.load());
        } else {
            storage.createNewFile();
            this.taskList = new TaskList();
            storage.updateFile(taskList.getTaskList());
        }
    }

    /**
     * Launch the program and initiate the command loop to process user input.
     * It displays a greeting message by calling the {@link GreetCommand}.
     */
    public void launch() {
        new GreetCommand().execute(storage);
        try (Scanner scanner = new Scanner(System.in)) {
            while (true) {
                System.out.print("Enter a command:\n");
                String input = scanner.nextLine();

                if (input.equals(ExitCommand.COMMAND_WORD)) {
                    new ExitCommand().execute(storage);
                    break;
                } else {
                    try {
                        Command c = new Parser(input, taskList).parse();
                        c.execute(storage);
                    } catch (DukeException e) {
                        System.out.println(e.getMessage());
                    }
                }
            }
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * The program's main function to start the application.
     *
     * @param args the arguments passed into the application.
     */
    public static void main(String[] args) {
        Launcher.main(args);
    }

    /**
     * Generates a response based on the user input. Processes the input by checking for specific commands,
     * parsing the input using a parser, and executing the corresponding command.
     *
     * @param input The user input to be processed.
     * @return A string representing the response generated based on the input.
     */
    public String getResponse(String input) {
        String response = null;
        if (input.equals(ExitCommand.COMMAND_WORD)) {
            response = new ExitCommand().execute(storage);
            this.isExit = true;
        } else {
            try {
                Command c = new Parser(input, taskList).parse();
                response = c.execute(storage);
            } catch (DukeException e) {
                response = e.getMessage();
            }
        }
        return response;
    }

    public boolean isExit() {
        return this.isExit;
    }
}
