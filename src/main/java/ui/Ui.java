package ui;

import java.io.FileNotFoundException;
import java.util.List;

import enums.Commands;
import exceptions.WilliamException;
import tasks.Task;
import utilities.AdditionalInfoParser;
import utilities.Parser;
import utilities.Storage;
import utilities.TaskList;

/**
 * Deals with interactions with the user
 */
public class Ui {
    private static String filePath = "data/tasks.txt";
    private TaskList taskList = new TaskList();
    private Storage storage;

    public Ui() {
        this.loadTasks();
    }

    /**
     * Returns the enum command
     *
     * @param input Input that is a command in String format
     * @return output Output that is a command in Commands format
     * @throws WilliamException If the command does not exist in the Commands class
     */
    public Commands retrieveCommand(String input) throws WilliamException {
        try {
            return Commands.valueOf(input);
        } catch (IllegalArgumentException e) {
            throw new WilliamException(
                    "This command " + input + " does not exist, please try again!");
        }
    }

    /**
     * Processes user input and returns a response
     *
     * @param userInput The user input to process
     * @return A response based on the processed input
     */
    public String interactWithUser(String userInput) {
        Parser parser = new Parser(taskList, storage);
        Commands command = null;
        String[] texts = AdditionalInfoParser.retrieveTexts(userInput);
        try {
            command = retrieveCommand(texts[0].toUpperCase());
        } catch (WilliamException e) {
            return e.getMessage() + "\n";
        }
        return parser.parseCommands(command, texts[1]);
    }

    /**
     * Loads tasks into the arraylist. If the file does not exist or the format is wrong, initializes an
     * empty list first.
     */
    public void loadTasks() {
        try {
            this.storage = new Storage(filePath);
            List<Task> tasks = this.storage.loadFromFile();
            this.taskList = new TaskList(tasks);
        } catch (FileNotFoundException e) {
            System.out.println(
                    "File not found at " + filePath + ", hence initializing an empty task list.\n");
            this.taskList = new TaskList();
        } catch (WilliamException e) {
            System.out.println(e.getMessage() + "\n");
            this.taskList = new TaskList();
        }
    }
}
