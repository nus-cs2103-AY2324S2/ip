package duke.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import duke.command.CommandParser;
import duke.command.CommandType;
import duke.commons.exceptions.DukeException;
import duke.commons.utils.DateUtils;
import duke.storage.PersistentStorageHandler;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.TaskList;
import duke.task.ToDo;
import duke.ui.UIFormatter;

public class LogicController {

    private TaskList taskList;

    public LogicController() {
    }

    public LogicController(TaskList taskList) {
        this.taskList = taskList;
    }

    /** 
     * Necessary for dependency injection in Main.
     * @param taskList
     */
    public void setTaskList(TaskList taskList)  {
        this.taskList = taskList;
    }

    /**
     * Process user input by parsing the command and arguments.
     * 
     * @param userInput Provided by the user.
     * @return String to be displayed on the GUI.
     * @throws DukeException if invalid command
     */
    public String processUserInput(String userInput) throws DukeException {
        CommandType commandType = CommandParser.parseCommand(userInput);
        String response = "Invalid Command";
        try {
            switch (commandType) {
                case LIST:
                    response = handleList();
                    break;

                case MARK:
                    response = handleMark(userInput);
                    break;

                case UNMARK:
                    response = handleUnmark(userInput);
                    break;

                case DELETE:
                    response = handleDelete(userInput);
                    break;

                case FIND:
                    response = handleFind(userInput);
                    break;

                case TODO:
                    response = handleToDo(userInput);
                    break;

                case DEADLINE:
                    response = handleDeadline(userInput);
                    break;

                case EVENT:
                    response = handleEvent(userInput);
                    break;

                case BYE:
                    response = handleExit();
                    break;

                default:
                    response = "Invalid Command" + commandType;
            }
            PersistentStorageHandler.writeTaskFileToDisc(taskList);
        } catch (DukeException e) {
            response = e.getMessage();
        }
        return response;
    }

    /**
     * Displays the list of tasks to the user.
     * 
     * @throws DukeException If an error during task listing.
     */
    private String handleList() throws DukeException {
        String response = taskList.getFormattedTasks();
        return UIFormatter.formatResponse(response);
    }

    /**
     * Marks a specified task as done.
     * 
     * @param userInput The user input containing the index of the task to mark as
     *                  done.
     * @throws DukeException If the task index is invalid.
     */
    private String handleMark(String userInput) throws DukeException {
        int idx = CommandParser.parseTaskIndex(userInput);
        String response = taskList.markTaskDone(idx);
        return UIFormatter.formatResponse(response);
    }

    /**
     * Marks a specified task as not done.
     * 
     * @param userInput The user input containing the index of the task to mark as
     *                  not done.
     * @throws DukeException If the task index is invalid.
     */
    private String handleUnmark(String userInput) throws DukeException {
        int idx = CommandParser.parseTaskIndex(userInput);
        String response = taskList.markTaskUndone(idx);
        return UIFormatter.formatResponse(response);
    }

    /**
     * Deletes a specified task from the task list.
     * 
     * @param userInput The user input containing the index of the task to delete.
     * @throws DukeException If the task index is invalid.
     */
    private String handleDelete(String userInput) throws DukeException {
        int idx = CommandParser.parseTaskIndex(userInput);
        String response = taskList.deleteTask(idx);
        int totalTasks = taskList.getNumberTasks();
        return UIFormatter.formatTaskDeletedResponse(response, totalTasks);
    }

    private String handleFind(String userInput) throws DukeException {
        String[] keywords = CommandParser.parseFind(userInput);
        ArrayList<Integer> taskIndices = taskList.findTasksByKeywordsMatching(keywords);
        ArrayList<String> response = taskList.getTaskRepresentationsByIndices(taskIndices);
        return UIFormatter.formatTasksByIndicesResponse(response);
    }

    /**
     * Adds a new ToDo task to the task list.
     * 
     * @param userInput The user input containing the description of the ToDo task.
     * @throws DukeException If the description is invalid or other errors occur.
     */
    private String handleToDo(String userInput) throws DukeException {
        String description = CommandParser.parseToDo(userInput);
        String response = taskList.addTask(new ToDo(description));
        int totalTasks = taskList.getNumberTasks();
        return UIFormatter.formatTaskAddedResponse(response, totalTasks);
    }

    /**
     * Adds a new Deadline task to the task list.
     * 
     * @param userInput The user input containing the description of the Deadline
     *                  task.
     * @throws DukeException If the description is invalid or other errors occur.
     */
    private String handleDeadline(String userInput) throws DukeException {
        String[] deadlineDetails = CommandParser.parseDeadline(userInput);
        String description = deadlineDetails[0];
        LocalDate due = DateUtils.parseDateString(deadlineDetails[1]);
        String response = taskList.addTask(new Deadline(description, due));
        int totalTasks = taskList.getNumberTasks();
        return UIFormatter.formatTaskAddedResponse(response, totalTasks);
    }

    /**
     * Adds a new Event task to the task list.
     * 
     * @param userInput The user input containing the description of the Event task.
     * @throws DukeException If the description is invalid or other errors occur.
     */
    private String handleEvent(String userInput) throws DukeException {
        String[] eventDetails = CommandParser.parseEvent(userInput);
        String description = eventDetails[0];
        LocalDate start = DateUtils.parseDateString(eventDetails[1]);
        LocalDate end = DateUtils.parseDateString(eventDetails[2]);
        String response = taskList.addTask(new Event(description, start, end));
        int totalTasks = taskList.getNumberTasks();
        return UIFormatter.formatTaskAddedResponse(response, totalTasks);
    }

    private String handleExit() throws DukeException {
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                System.exit(0);
            }
        }, 1000);

        return UIFormatter.formatExit();
    }
}
