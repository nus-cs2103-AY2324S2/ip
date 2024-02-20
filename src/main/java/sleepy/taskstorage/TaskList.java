package sleepy.taskstorage;

import java.util.ArrayList;

import javafx.util.Pair;
import sleepy.tasks.DeadlineTask;
import sleepy.tasks.EventTask;
import sleepy.tasks.PlannedTask;
import sleepy.tasks.Task;
import sleepy.tasks.ToDoTask;
import sleepy.tools.Parser;
import sleepy.tools.ResponseHandler;

/**
 * This class stores the tasks in the Sleepy AI Chatbot.
 *
 * @author kjw142857
 */
public class TaskList {
    private ArrayList<Task> tasks = new ArrayList<>();

    private Storage storage;

    private boolean isStartingUp = true;

    /**
     * Constructor for the TaskList class. Initialises the list of tasks
     * based on the saved data in the HardDiskStorage file.
     */
    public TaskList() {
        try {
            storage = new Storage();
            ArrayList<Pair<String, Boolean>> listOfTasks = storage.readFile();
            for (Pair<String, Boolean> task : listOfTasks) {
                String taskDescription = task.getKey();
                boolean isDone = task.getValue();
                addTask(Parser.parse(taskDescription));
                if (isDone) {
                    markTaskAsDone(tasks.size());
                }
            }
        } catch (IllegalArgumentException i) {
            tasks = new ArrayList<>();
            ResponseHandler.appendLineToResponse("Sleepy encountered a problem upon startup!"
                    + " Your saved data has unfortunately been lost.");
        }
        isStartingUp = false;
    }

    /**
     * Determines the action to be applied on this TaskList according to the access command given.
     *
     * @param parsedCommand Access command given.
     * @throws IllegalArgumentException If the command format is invalid.
     */
    public void access(String[] parsedCommand) throws IllegalArgumentException {
        String command = parsedCommand[0];
        switch (command) {
        case "list":
            printTasks();
            break;
        case "mark":
            // Fallthrough
        case "unmark":
            // Fallthrough
        case "delete":
            int taskNumber = Integer.parseInt(parsedCommand[1]);
            checkValidTaskNumber(taskNumber);
            handleTask(command, taskNumber);
            break;
        case "find":
            String keywords = parsedCommand[1];
            findTask(keywords);
            break;
        case "todo":
            // Fallthrough
        case "deadline":
            // Fallthrough
        case "plan":
            // Fallthrough
        case "event":
            addTask(parsedCommand);
            break;
        default:
            // Should never reach here - parser should have caught invalid command
            assert false : "Parser did not parse command correctly";
            throw new IllegalArgumentException("Incorrect command!");
        }
    }

    /**
     * Handles a task according to the operation type.
     *
     * @param operation Operation type.
     * @param taskNumber Task number to be handled.
     */
    private void handleTask(String operation, int taskNumber) throws IllegalArgumentException {
        assert taskNumber >= 1 && taskNumber <= tasks.size() : "Invalid task number!";
        switch (operation) {
        case "mark":
            markTaskAsDone(taskNumber);
            break;
        case "unmark":
            markTaskAsUndone(taskNumber);
            break;
        case "delete":
            deleteTask(taskNumber);
            break;
        default:
            // Should never reach here, parser should have detected invalid operation
            assert false : "Parser did not parse command correctly";
            throw new IllegalArgumentException("Invalid operation on a task!");
        }
    }

    /**
     * Searches for a task in this list by keyword(s), and responds with the match(es).
     *
     * @param keywords Keyword(s) used for the search.
     */
    private void findTask(String keywords) {
        ResponseHandler.appendLineToResponse("Here are the matching tasks in your list:");
        int i = 1;
        for (Task task: tasks) {
            if (task.getDescription().contains(keywords)) {
                ResponseHandler.appendLineToResponse(i + "." + task.getDescription());
                i++;
            }
        }
    }

    /**
     * Creates and adds a task to this list.
     *
     * @param parsedTaskDetails Details of the task to be added, in the form of an array of strings.
     * @throws IllegalArgumentException If the first string in the array does not contain a task type.
     */
    private void addTask(String[] parsedTaskDetails) throws IllegalArgumentException {
        String taskType = parsedTaskDetails[0];
        Task createdTask = null;
        switch (taskType) {
        case "todo":
            createdTask = new ToDoTask(parsedTaskDetails[1]);
            break;
        case "deadline":
            createdTask = new DeadlineTask(parsedTaskDetails[1], parsedTaskDetails[2]);
            break;
        case "plan":
            createdTask = new PlannedTask(parsedTaskDetails[1], parsedTaskDetails[2]);
            break;
        case "event":
            createdTask = new EventTask(parsedTaskDetails[1], parsedTaskDetails[2], parsedTaskDetails[3]);
            break;
        default:
            // Should never reach here - parser should have caught invalid task type
            assert false : "Parser did not parse command correctly";
            throw new IllegalArgumentException("This is not a task!");
        }
        tasks.add(createdTask);
        storage.saveTasks(tasks);
        if (!isStartingUp) {
            ResponseHandler.appendLineToResponse("added: " + createdTask.getDescription());
        }
    }

    /**
     * Deletes a task from this list.
     *
     * @param taskNumber Task number to be deleted.
     * @throws IllegalArgumentException If the task number is invalid.
     */
    private void deleteTask(int taskNumber) throws IllegalArgumentException {
        Task removedTask = tasks.remove(taskNumber - 1);
        storage.saveTasks(tasks);
        ResponseHandler.appendLineToResponse("Noted. I've removed this task:");
        ResponseHandler.appendLineToResponse("  " + removedTask.getDescription());
        ResponseHandler.appendLineToResponse(
                String.format("Now you have %d task(s) in the list.", tasks.size()));
    }

    /**
     * Marks a specific task in this list as done, and prints its status.
     *
     * @param taskNumber Number (in this list) of the task to be marked.
     */
    private void markTaskAsDone(int taskNumber) {
        Task targetTask = tasks.get(taskNumber - 1);
        targetTask.markAsDone();
        storage.saveTasks(tasks);
        if (!isStartingUp) {
            ResponseHandler.appendLineToResponse("Nice! I've marked this task as done:");
            ResponseHandler.appendLineToResponse(targetTask.getDescription());
        }
        assert targetTask.isDone() == "true" : "Task was not marked as done correctly!";
    }

    /**
     * Marks a specific task in this list as undone, and prints its status.
     *
     * @param taskNumber Number (in this list) of the task to be marked.
     */
    private void markTaskAsUndone(int taskNumber) {
        Task targetTask = tasks.get(taskNumber - 1);
        targetTask.markAsUndone();
        storage.saveTasks(tasks);
        if (!isStartingUp) {
            ResponseHandler.appendLineToResponse("OK, I've marked this task as not done yet:");
            ResponseHandler.appendLineToResponse(targetTask.getDescription());
        }
        assert targetTask.isDone() == "false" : "Task was not marked as undone correctly!";
    }

    /**
     * Prints out the list of tasks.
     */
    private void printTasks() {
        if (tasks.size() == 0) {
            ResponseHandler.appendLineToResponse("Your task list is empty! "
                    + "Looks like you can go back to sleep.");
            return;
        }
        for (int i = 1; i <= tasks.size(); i++) {
            Task nextTask = tasks.get(i - 1);
            ResponseHandler.appendLineToResponse(i + "." + nextTask.getDescription());
        }
    }

    /**
     * Checks if a task number is in the range of accessible task numbers.
     * @param taskNumber The task number to be checked.
     * @throws IllegalArgumentException If the task number is out of the valid range.
     */
    private void checkValidTaskNumber(int taskNumber) throws IllegalArgumentException {
        if (taskNumber < 1 || taskNumber > tasks.size()) {
            throw new IllegalArgumentException("Invalid task number!");
        }
    }
}
