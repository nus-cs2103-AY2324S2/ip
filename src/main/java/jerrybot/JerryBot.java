package jerrybot;

import java.util.Scanner;

import exception.DuplicateTaskException;
import exception.IncompleteCommandException;
import exception.InvalidCommandException;
import exception.InvalidTaskNumberException;
import parser.Parser;
import storage.Storage;
import task.Deadline;
import task.Event;
import task.Task;
import task.ToDo;
import tasklist.TaskList;
import ui.Ui;

/**
 * This class is the chatbot.
 *
 */
public class JerryBot {
    private static boolean isTerminate = false;
    private static final String FILEPATH = "data/jerrybot.txt";
    private static Storage storage;
    private static TaskList taskArrayList;
    private static Parser parser;

    /**
     * Constructor to initialise the chatbot class.
     */
    public JerryBot() {
        parser = new Parser();
        storage = new Storage(FILEPATH);
        taskArrayList = new TaskList(storage.readDataStore());
    }

    /**
     * Adds a new task based on the user input command.
     *
     * @param s The user input command containing the task details.
     * @throws Exception If the command is invalid or incomplete.
     */
    public static void addTask(String s) throws Exception {
        String taskDescription;
        switch (parser.extractCommand(s)) {
        case "todo":
            taskDescription = parser.extractDescription(s);
            Task newDescriptionTask = new ToDo(taskDescription);
            safeAddTask(newDescriptionTask);
            break;
        case "deadline":
            taskDescription = parser.extractDescription(s);
            String[] deadlineStringParts = taskDescription.split("/by ");
            if (deadlineStringParts.length < 2) {
                throw new IncompleteCommandException("Deadline command incomplete. It should be in the "
                        + "form of deadline description /by datetime.");
            } else {
                Task newDeadlineTask = new Deadline(deadlineStringParts[0], deadlineStringParts[1]);
                safeAddTask(newDeadlineTask);
            }
            break;
        case "event":
            taskDescription = parser.extractDescription(s);
            String[] eventStringParts = taskDescription.split("/from |/to ");
            if (eventStringParts.length < 3) {
                throw new IncompleteCommandException("Event command incomplete. It should be in the "
                        + "form of event description /from datetime /to datetime.");
            } else {
                Task newEventTask = new Event(eventStringParts[0], eventStringParts[1], eventStringParts[2]);
                safeAddTask(newEventTask);
            }
            break;
        default:
            throw new InvalidCommandException(s);
        }
    }

    /**
     * Adds task to data store and task list if there is no existing duplicate.
     * @param task Task to be added.
     */
    private static void safeAddTask(Task task) throws DuplicateTaskException {
        if (!taskArrayList.checkDuplicate(task)) {
            storage.addToDataStore(task);
            taskArrayList.addTask(task);
        } else {
            throw new DuplicateTaskException();
        }
    }

    /**
     * Generates a response based on the user input command.
     *
     * @param s The user input command.
     * @return The response message.
     */
    public static String response(String s) {
        String command = parser.extractCommand(s);
        switch (command) {
        case "bye":
            isTerminate = true;
            return Ui.printByeMessage();
        case "list":
            return Ui.printListMessage(taskArrayList);
        case "find":
            try {
                return Ui.printFindMessage(taskArrayList, parser.extractDescription(s));
            } catch (IncompleteCommandException e) {
                return e.toString();
            }
        case "mark":
            try {
                int taskNum = convertTaskNumStringToInt(s);
                storage.editDataStoreTaskAsDone(taskNum);
                return markTaskAsDone(taskNum);
            } catch (InvalidTaskNumberException e) {
                return e.toString();
            }
        case "unmark":
            try {
                int taskNum = convertTaskNumStringToInt(s);
                storage.editDataStoreTaskAsUndone(taskNum);
                return unmarkTaskAsDone(taskNum);
            } catch (InvalidTaskNumberException e) {
                return e.toString();
            }
        case "delete":
            String requestedDeletion = s.substring(7);
            try {
                return deleteTask(requestedDeletion);
            } catch (InvalidTaskNumberException e) {
                return e.toString();
            }
        default:
            try {
                addTask(s);
                return Ui.printAddTaskMessage(taskArrayList.getTaskByIdx(taskArrayList.getLastIdx() - 1).toString(),
                        taskArrayList.getLastIdx());
            } catch (Exception e) {
                return e.toString();
            }
        }
    }

    /**
     * Marks the specified task as done.
     *
     * @param taskNum The number of the task to mark as done.
     * @return The message indicating the task was marked as done.
     * @throws InvalidTaskNumberException If the task number is invalid.
     */
    public static String markTaskAsDone(int taskNum) throws InvalidTaskNumberException {
        taskArrayList.getTaskByIdx(taskNum - 1).markAsDone();
        return Ui.printMarkTaskAsDoneMessage(taskArrayList.getTaskByIdx(taskNum - 1).toString());
    }

    /**
     * Converts the task number string to an integer.
     *
     * @param s The task number string.
     * @return The integer representation of the task number.
     * @throws InvalidTaskNumberException If the task number is invalid.
     */
    private static int convertTaskNumStringToInt(String s) throws InvalidTaskNumberException {
        String taskNumString = s.split("\\s+")[1];
        int taskNum = Integer.parseInt(taskNumString);
        if (taskNum > taskArrayList.getLastIdx()) {
            throw new InvalidTaskNumberException(taskNumString);
        }
        return taskNum;
    }

    /**
     * Marks the specified task as undone.
     *
     * @param taskNum The number of the task to mark as undone.
     * @return The message indicating the task was marked as undone.
     * @throws InvalidTaskNumberException If the task number is invalid.
     */
    public static String unmarkTaskAsDone(int taskNum) throws InvalidTaskNumberException {
        taskArrayList.getTaskByIdx(taskNum - 1).markAsUndone();
        return Ui.printMarkTaskAsUndoneMessage(taskArrayList.getTaskByIdx(taskNum - 1).toString());
    }

    /**
     * Deletes the specified task.
     *
     * @param requestedDeletion The number of the task to delete.
     * @return The message indicating the task was deleted.
     * @throws InvalidTaskNumberException If the task number is invalid.
     */
    public static String deleteTask(String requestedDeletion) throws InvalidTaskNumberException {
        try {
            int taskNum = Integer.parseInt(requestedDeletion);
            if (taskNum > taskArrayList.getLastIdx()) {
                throw new InvalidTaskNumberException(requestedDeletion);
            }
            String deletedTaskString = taskArrayList.getTaskByIdx(taskNum - 1).toString();
            storage.removeFromDataStore(taskNum - 1);
            taskArrayList.removeTask(taskNum - 1);
            storage = new Storage(FILEPATH);
            return Ui.printTaskRemovalMessage(deletedTaskString, taskArrayList.getLastIdx());
        } catch (NumberFormatException e) {
            throw new InvalidTaskNumberException(requestedDeletion);
        }
    }

    public static void main(String[] args) {
        parser = new Parser();
        storage = new Storage(FILEPATH);
        taskArrayList = new TaskList(storage.readDataStore());
        System.out.println(Ui.printHiMessage());

        Scanner userInput = new Scanner(System.in);

        while (!isTerminate) {
            String command = userInput.nextLine();
            System.out.println(response(command));
        }
    }
}
