import essentials.Parser;
import essentials.Storage;
import essentials.TaskList;
import essentials.Ui;
import exceptions.JimmyException;

import java.util.Scanner;

/**
 * Represents the main bot class.
 */
public class Jimmy {
    private final Storage storage;
    private TaskList tasks;
    private final Ui ui;
    private final Parser parser;

    /**
     * Constructor for Jimmy bot.
     *
     * @param filePath The file path to the file where the tasks are stored.
     */
    public Jimmy(String filePath) {
        ui = new Ui();
        parser = new Parser();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage, ui);
        } catch (JimmyException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Run the bot.
     */
    public void run() {
        Scanner sc = new Scanner(System.in);
        ui.greetUser();
        boolean isBotRunning = true; // flag to check if bot is running

        try {
            try {
                while (isBotRunning) {
                    String userInput = sc.nextLine();
                    String[] parsedInput = parser.parseUserInput(userInput);
                    String instruction = parsedInput[0];
                    String details = parsedInput[1];
                    switch (instruction) {
                    case "bye":
                        ui.exit();
                        isBotRunning = false;
                        break;
                    case "list":
                        tasks.displayTasks();
                        break;
                    case "mark":
                        tasks.markTask(details);
                        break;
                    case "unmark":
                        tasks.unmarkTask(details);
                        break;
                    case "delete":
                        tasks.deleteTask(details);
                        break;
                    case "find":
                        tasks.findTask(details);
                        break;
                    default:
                        tasks.createNewTask(instruction, details);
                    }
                }
            } catch (IllegalArgumentException e) {
                System.out.println("Error: Please only use the specified commands in the user guide.");
            } finally {
                storage.writeToFile(tasks.getTaskList());
            }
        } catch (JimmyException e) {
            System.out.println(e.getMessage());
        }
    }
}
