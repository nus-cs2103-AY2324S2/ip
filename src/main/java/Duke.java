import java.util.Scanner;

import command.Command;
import command.ExitCommand;
import command.GreetCommand;
import duke.DukeException;
import duke.Parser;
import duke.Storage;
import model.TaskList;

/**
 * {@code Duke} is the main class of this program.
 *
 * @author Tsui Yi Wern (yiwern5)
 */
public class Duke {
    private final TaskList taskList;
    private final Storage storage;

    /**
     * Constructor for {@code Duke} to initialize the storage or
     * get task list from the storage.
     * @param filePath the file path of the storage.
     */
    public Duke(String filePath) {
        this.storage = new Storage(filePath);
        if (storage.isFileExists()) {
            this.taskList = new TaskList(storage.load());
        } else {
            storage.createNewFile();
            this.taskList = new TaskList();
            storage.update(taskList.getTaskList());
        }
    }

    /**
     * Launch the program and initiate the command loop to process user input.
     * It displays a greeting message by calling the {@code GreetCommand}.
     */
    public void launch() {
        new GreetCommand().execute(storage);
        try (Scanner scanner = new Scanner(System.in)) {
            while (true) {
                System.out.print("Enter a command:\n");
                String input = scanner.nextLine();

                if (input.equals("bye")) {
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
     * @param args the arguments passed into the application.
     */
    public static void main(String[] args) {
        new Duke("./data.dat").launch();
    }
}
