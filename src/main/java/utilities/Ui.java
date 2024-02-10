package utilities;

import commands.CommandsParser;
import exceptions.RyanGoslingException;
import tasks.Task;

import java.io.FileNotFoundException;
import java.time.DateTimeException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * The Ui class handles the main chat listening and parsing of messages.
 */
public class Ui {
    private Scanner sc = new Scanner(System.in);
    //Handles adding/removing to array of tasks
    private TaskList taskList = new TaskList();
    private String filePath = "data/task_lists.txt";
    //Handles the loading and saving of tasks to text file
    private Storage taskLoader = new Storage(filePath);

    public void oneTimeLoadAllTasks() {
        try {
            ArrayList<Task> parsedTasks = this.taskLoader.parseAndLoadTasks();
            this.taskList = new TaskList(parsedTasks);
        } catch (RyanGoslingException | FileNotFoundException e) {
            ResponseHandler.errorPrinter(e);
            return;
        }
    }

    public String performTaskFromSingleUserInput(String userInput) {
        CommandsParser commandsParser = new CommandsParser(this.taskList, this.filePath, this.taskLoader);
        try {
            return commandsParser.parseCommands(userInput);
        } catch (DateTimeException e) {
            return "Problem with date or time format!\n"
                    + "Date should be in YYYY-MM-DD\n"
                    + "Time should be in 24 HR clock format";
        } catch (RyanGoslingException e) {
            return e.getMessage();
        }

    }
}
