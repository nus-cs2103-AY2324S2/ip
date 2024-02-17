package utilities;

import java.io.FileNotFoundException;
import java.time.DateTimeException;
import java.util.ArrayList;
import java.util.Scanner;

import commands.CommandsParser;
import exceptions.RyanGoslingException;
import main.MainResponseCategorized;
import tasks.Task;

/**
 * The Ui class handles the main chat listening and parsing of messages.
 */
public class Ui {

    /**
     * Scanner for user input.
     */
    private final Scanner sc = new Scanner(System.in);

    /**
     * Handles adding/removing to an array of tasks.
     */
    private TaskList taskList = new TaskList();

    /**
     * File path for task data storage.
     */
    private final String filePath = "data/task_lists.txt";

    /**
     * Handles the loading and saving of tasks to a text file.
     */
    private final Storage taskLoader = new Storage(filePath);

    /**
     * Loads all tasks from the data file during initialization.
     */
    public void oneTimeLoadAllTasks() {
        try {
            ArrayList<Task> parsedTasks = this.taskLoader.parseAndLoadTasks();
            this.taskList = new TaskList(parsedTasks);
        } catch (RyanGoslingException | FileNotFoundException e) {
            ResponseHandler.errorPrinter(e);
            return;
        }
    }

    /**
     * Performs a task based on the given user input.
     *
     * @param userInput The user input to be processed.
     * @return The response string based on the executed task.
     */
    public MainResponseCategorized performTaskFromSingleUserInput(String userInput) {
        CommandsParser commandsParser = new CommandsParser(this.taskList, this.filePath, this.taskLoader);
        try {
            String responseFromParsingofCommand = commandsParser.parseCommands(userInput);
            return new MainResponseCategorized(false, responseFromParsingofCommand);
        } catch (DateTimeException e) {
            return new MainResponseCategorized(true, "Problem with date or time format!\n"
                    + "Date should be in YYYY-MM-DD\n"
                    + "Time should be in 24 HR clock format");

        } catch (RyanGoslingException e) {
            return new MainResponseCategorized(true, e.getMessage());
        }
    }
}
