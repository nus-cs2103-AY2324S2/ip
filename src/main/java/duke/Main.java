package duke;

import java.time.LocalDate;

import duke.command.CommandParser;
import duke.command.CommandType;
import duke.commons.exceptions.DukeException;
import duke.storage.PersistentStorageHandler;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.TaskList;
import duke.task.ToDo;
import duke.ui.UserInterface;

/**
 * The main entry point for the Duke application.
 * This class handles the initialization and main execution loop of the application,
 * including loading tasks from disk, processing user commands, and exiting the application.
 */
public class Main {

    private static TaskList taskList = new TaskList();

    private static PersistentStorageHandler persistentStorageHandler = new PersistentStorageHandler();

    
    /** 
     * Processes and responds to user commands until exit is requested.
     * 
     * @param args Command line arguments (not used)
     */
    public static void main(String[] args) {

        UserInterface.printWelcome();

        try {
            if (persistentStorageHandler.ensureTaskFileExists()) {
                taskList = persistentStorageHandler.readTaskFileFromDisc();
                int numTasks = taskList.getNumberTasks();
                UserInterface.print("Read existing tasks (" + numTasks + ") from disc");
            }
        } catch (DukeException e) {
            UserInterface.showError(e.getMessage());
        }

        boolean isExit = false;
        while (!isExit) {
            try {
                String userInput = UserInterface.getUserInput();
                CommandType commandType = CommandParser.parseCommand(userInput);

                switch (commandType) {
                    case LIST:
                        handleList();
                        break;

                    case MARK:
                        handleMark(userInput);
                        break;

                    case UNMARK:
                        handleUnmark(userInput);
                        break;

                    case DELETE:
                        handleDelete(userInput);
                        break;

                    case TODO:
                        handleToDo(userInput);
                        break;

                    case DEADLINE:
                        handleDeadline(userInput);
                        break;

                    case EVENT:
                        handleEvent(userInput);
                        break;

                    case EXIT:
                        isExit = true;
                        break;

                    case BYE:
                        isExit = true;
                        break;

                    default:
                        throw new DukeException("Invalid Command" + commandType);
                }

                persistentStorageHandler.writeTaskFileToDisc(taskList);
            } catch (DukeException e) {
                UserInterface.showError(e.getMessage());
            }
        }

        UserInterface.printExit();
        return;
    }

    
    /** 
     * Displays the list of tasks to the user.
     * 
     * @throws DukeException If an error during task listing.
     */
    private static void handleList() throws DukeException {
        taskList.printTasks();
    }

    
    /** 
     * Marks a specified task as done.
     * 
     * @param userInput The user input containing the index of the task to mark as done.
     * @throws DukeException If the task index is invalid.
     */
    private static void handleMark(String userInput) throws DukeException {
        int idx = CommandParser.parseTaskIndex(userInput);
        String response = taskList.markTaskDone(idx);
        UserInterface.print(response);
    }

    
    /** 
     * Marks a specified task as not done.
     * 
     * @param userInput The user input containing the index of the task to mark as not done.
     * @throws DukeException If the task index is invalid.
     */
    private static void handleUnmark(String userInput) throws DukeException {
        int idx = CommandParser.parseTaskIndex(userInput);
        String response = taskList.markTaskUndone(idx);
        UserInterface.print(response);
    }

    
    /** 
     * Deletes a specified task from the task list.
     * 
     * @param userInput The user input containing the index of the task to delete.
     * @throws DukeException If the task index is invalid.
     */
    private static void handleDelete(String userInput) throws DukeException {
        int idx = CommandParser.parseTaskIndex(userInput);
        String response = taskList.deleteTask(idx);
        int totalTasks = taskList.getNumberTasks();
        UserInterface.printTaskDeleted(response, totalTasks);
    }

    
    /**
     * Adds a new ToDo task to the task list.
     * 
     * @param userInput The user input containing the description of the ToDo task.
     * @throws DukeException If the description is invalid or other errors occur.
     */
    private static void handleToDo(String userInput) throws DukeException {
        String description = CommandParser.parseToDo(userInput);
        String response = taskList.addTask(new ToDo(description));
        int totalTasks = taskList.getNumberTasks();
        UserInterface.printTaskAdded(response, totalTasks);
    }

    /**
     * Adds a new Deadline task to the task list.
     * 
     * @param userInput The user input containing the description of the Deadline task.
     * @throws DukeException If the description is invalid or other errors occur.
     */
    private static void handleDeadline(String userInput) throws DukeException {
        String[] deadlineDetails = CommandParser.parseDeadline(userInput);
        String description = deadlineDetails[0];
        LocalDate due = LocalDate.parse(deadlineDetails[1]);
        String response = taskList.addTask(new Deadline(description, due));
        int totalTasks = taskList.getNumberTasks();
        UserInterface.printTaskAdded(response, totalTasks);
    }

    /**
     * Adds a new Event task to the task list.
     * 
     * @param userInput The user input containing the description of the Event task.
     * @throws DukeException If the description is invalid or other errors occur.
     */
    private static void handleEvent(String userInput) throws DukeException {
        String[] eventDetails = CommandParser.parseEvent(userInput);
        String description = eventDetails[0];
        LocalDate start = LocalDate.parse(eventDetails[1]);
        LocalDate end = LocalDate.parse(eventDetails[2]);
        String response = taskList.addTask(new Event(description, start, end));
        int totalTasks = taskList.getNumberTasks();
        UserInterface.printTaskAdded(response, totalTasks);
    }
}
