package sleepy.taskstorage;

import sleepy.tasks.Deadline;
import sleepy.tasks.Event;
import sleepy.tasks.Task;
import sleepy.tasks.ToDo;
import sleepy.tools.Parser;
import sleepy.tools.Ui;

import java.util.ArrayList;

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
     * based on the saved data in the SleepyTaskList file.
     *
     * @param filePath The data path to the file linked to this TaskList.
     */
    public TaskList(String filePath) {
        storage = new Storage(filePath);
        ArrayList<String> listOfTasks = storage.readFile();
        for (String task : listOfTasks) {
            addTask(task);
        }
        isStartingUp = false;
    }

    /**
     * Determines the action to be applied on this TaskList according to the access command given.
     *
     * @param accessCommand Access command given.
     */
    public void access(String accessCommand) {
        try {
            String[] parsedCommand = Parser.parse(accessCommand);
            switch (parsedCommand[0]) {
            case "list":
                printTasks();
                break;
            case "mark", "unmark", "delete":
                int taskNumber = Integer.parseInt(parsedCommand[1]);
                if (taskNumber < 1 || taskNumber > tasks.size()) {
                    throw new IllegalArgumentException("Invalid task number!");
                }
                if (parsedCommand[0].equals("mark")) {
                    markTaskAsDone(taskNumber);
                } else if (parsedCommand[0].equals("unmark")) {
                    markTaskAsUndone(taskNumber);
                } else {
                    deleteTask(taskNumber);
                }
                break;
            case "add":
                addTask(accessCommand);
                break;
            }
        } catch (NumberFormatException n) {
            Ui.printError("Zzz... The target task must be an integer!");
        } catch (IllegalArgumentException i) {
            Ui.printError(i.getMessage());
        }
    }

    /**
     * Creates and adds a task to this list.
     *
     * @param task Task to be added.
     */
    public void addTask(String task) throws IllegalArgumentException {
        Task createdTask = null;
        String[] parsedTask = Parser.parseTask(task);
        switch (parsedTask[0]) {
        case "todo":
            createdTask = new ToDo(task, parsedTask[1]);
            break;
        case "deadline":
            createdTask = new Deadline(task, parsedTask[1], parsedTask[2]);
            break;
        case "event":
            createdTask = new Event(task, parsedTask[1], parsedTask[2], parsedTask[3]);
            break;
        }
        tasks.add(createdTask);
        storage.saveTasks(tasks);
        if (!isStartingUp) {
            Ui.printLine("added: " + createdTask.getDescription());
        }
    }

    /**
     * Deletes a task from this list.
     *
     * @param taskNumber Task number to be deleted.
     */
    public void deleteTask(int taskNumber) throws IllegalArgumentException {
        if (taskNumber <= 0 || taskNumber > tasks.size()) {
            throw new IllegalArgumentException("Invalid task number!");
        }
        Task removedTask = tasks.remove(taskNumber - 1);
        storage.saveTasks(tasks);
        Ui.printLine("Noted. I've removed this task:");
        Ui.printLine("  " + removedTask.getDescription());
        Ui.printLine(String.format("Now you have %d task(s) in the list.", tasks.size()));
    }

    /**
     * Marks a specific task in this list as done, and prints its status.
     *
     * @param taskNumber Number (in this list) of the task to be marked.
     */
    public void markTaskAsDone(int taskNumber) {
        tasks.get(taskNumber - 1).markAsDone();
    }

    /**
     * Marks a specific task in this list as undone, and prints its status.
     *
     * @param taskNumber Number (in this list) of the task to be marked.
     */
    public void markTaskAsUndone(int taskNumber) {
        tasks.get(taskNumber - 1).markAsUndone();
    }

    /**
     * Prints out the list of tasks.
     */
    public void printTasks() {
        for (int i = 1; i <= tasks.size(); i++) {
            Task nextTask = tasks.get(i - 1);
            Ui.printLine(i + "." + nextTask.getDescription());
        }
    }
}
