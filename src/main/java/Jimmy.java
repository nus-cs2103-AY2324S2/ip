import java.io.BufferedWriter;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Jimmy {
    private final Storage storage;
    private TaskList tasks;
    private final Ui ui;
    private final Parser parser;

    /**
     * Constructor for Jimmy bot.
     * @param filePath The file path to the file where the tasks are stored.
     */
    public Jimmy(String filePath) {
        ui = new Ui();
        parser = new Parser();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage);
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
                        tasks.markTaskComplete(details);
                        break;
                    case "unmark":
                        tasks.markTaskIncomplete(details);
                        break;
                    case "delete":
                        tasks.deleteTask(details);
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
