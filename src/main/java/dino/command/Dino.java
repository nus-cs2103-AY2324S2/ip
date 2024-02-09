package dino.command;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

import dino.task.Task;
import javafx.application.Platform;

/** The main class for the Dino application. */
public class Dino {
    private static final String FILE_PATH = "./data/dino.txt";
    private Ui ui;
    private Storage storage;
    private TaskList tasks;
    private TaskType taskType;

    /**
     * Enumeration representing the types of tasks in the Dino application.
     * Tasks can be of type TODO, DEADLINE, or EVENT.
     */
    public enum TaskType {
        TODO,
        DEADLINE,
        EVENT
    }

    /**
     * Constructs a new Dino instance with the specified file path.
     */
    public Dino() {
        ui = new Ui();
        storage = new Storage(FILE_PATH);
        tasks = storage.loadTasksFromFile();
    }

    /**
     * Parses the given command and performs the corresponding action.
     *
     * @param input The user command to be parsed.
     * @return String representation of command.
     */
    public String parseCommand(String input) {
        assert input != null : "Input cannot be null";
        Scanner sc = new Scanner(System.in);
        Parser parser = new Parser(tasks);
        String[] parts = input.trim().split(" "); // Split into command and argument
        String command = parts[0];
        String argument = parts.length > 1
                ? String.join(" ", Arrays.copyOfRange(parts, 1, parts.length))
                : "";

        switch (command) {
        case "list":
            return tasks.listTask();

        case "bye":
            try {
                storage.saveTasksToFile(tasks.getTaskList());
                return ui.goodbye();
            } catch (IOException e) {
                return "Error: " + e.getMessage();
            } finally {
                Platform.exit();
            }


        case "delete":
            try {
                int taskNum = Integer.parseInt(argument);
                return tasks.deleteTask(taskNum);
            } catch (DinoException e) {
                return "Error: " + e.getMessage();
            }

        case "todo":
            taskType = Dino.TaskType.TODO;
            return parser.handleTaskCreation(taskType, argument);

        case "deadline":
            taskType = Dino.TaskType.DEADLINE;
            return parser.handleTaskCreation(taskType, argument);

        case "event":
            taskType = Dino.TaskType.EVENT;
            return parser.handleTaskCreation(taskType, argument);

        case "filter":
            return parser.printTasksForDate(argument.trim());

        case "mark":
            int taskNum = Integer.parseInt(argument);
            if (taskNum > tasks.size()) {
                return ("Uh oh, we do not have a task assigned to that number.");
            } else {
                Task completed = tasks.get(taskNum - 1);
                return completed.markAsDone();
            }

        case "unmark":
            int taskNumber = Integer.parseInt(argument);
            if (taskNumber > tasks.size()) {
                return ("Uh oh, we do not have a task assigned to that number.");
            } else {
                Task missing = tasks.get(taskNumber - 1);
                return missing.markAsUndone();
            }

        case "find":
            String searchKeyword = argument.trim();
            ArrayList<Task> matchingTasks = tasks.findTasksByKeyword(searchKeyword);

            if (matchingTasks.isEmpty()) {
                return ("Aww, there are no tasks that contains that keyword.");
            } else {
                StringBuilder printTask = new StringBuilder("Matching tasks:\n");
                for (Task task : matchingTasks) {
                    printTask.append(task).append("\n");
                }
                return String.valueOf(printTask);
            }

        default:
            try {
                throw new DinoException("I don't understand ;;");
            } catch (DinoException e) {
                return ("Error: " + e.getMessage());
            }
        }
    }
}
