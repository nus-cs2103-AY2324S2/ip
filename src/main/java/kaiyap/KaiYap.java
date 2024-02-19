package kaiyap;

import java.io.IOException;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.List;

import exceptions.AlreadyExistsException;
import exceptions.InvalidInputException;
import exceptions.KaiYapException;
import exceptions.MissingInputException;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

/**
 * Represents the main class for the KaiYap application, which is a task management system.
 * It integrates components for user interaction, task storage, task list management, and command parsing.
 * This class also includes the methods to start the chat interface and handle user inputs.
 */
public class KaiYap {

    private final Ui ui;
    private final Storage storage;
    private final TaskList taskList;
    private final Parser parser;

    /**
     * Initializes a new instance of the KaiYap application.
     * This constructor sets up the user interface, task list, storage, and parser components,
     * and prepares the system for user interaction.
     * The storage path is set to the user's home directory under a specific sub-folder for data storage.
     */
    public KaiYap() {
        this.ui = new Ui();
        this.taskList = new TaskList(ui);
        this.ui.setTaskList(this.taskList);
        this.storage = new Storage(ui, taskList, System.getProperty("user.home") + "/data/", "kaiyap.txt");
        this.parser = new Parser();

        storage.loadData();
    }

    public void setupJavaFx() {
        ScrollPane scrollPane = new ScrollPane();
        VBox dialogContainer = new VBox();
        scrollPane.setContent(dialogContainer);
        TextField userInput = new TextField();
        Button sendButton = new Button("Send");
        AnchorPane mainLayout = new AnchorPane();
        mainLayout.getChildren().addAll(scrollPane, userInput, sendButton);
    }

    public TaskList getTaskList() {
        return taskList;
    }

    /**
     * Deletes the specified file from the data path.
     *
     * @throws IOException If an I/O error occurs or the file cannot be deleted.
     */
    public void clearData() throws IOException {
        Files.deleteIfExists(storage.getPathName());
    }

    /**
     * Enumerates the types of tasks that can be managed by the KaiYap application.
     * The task types include DEADLINE for tasks with a specific deadline,
     * EVENT for scheduled events, and TODO for tasks without a specific time constraint.
     */
    public enum TaskType {
        DEADLINE, EVENT, TODO
    }

    /**
     * Starts the chat interface for the KaiYap application.
     * This method initiates a loop that continuously reads user input from the console,
     * processes it, and performs actions such as listing tasks, marking tasks as done or undone,
     * deleting tasks, or adding new tasks. The loop terminates when the user inputs "bye".
     */
    public String chatResponse(String input) {
        input = input.strip();
        if (input.equals("bye")) {
            return ui.sayBye();
        }

        String action = parser.decideAction(input);
        assert action != null : "Action determined by parser should not be null";

        try {
            switch (action) {
            case "listInputs":
                return ui.listInputs();
            case "mark":
                return this.markAsDone(input);
            case "unmark":
                return this.unmarkDone(input);
            case "find":
                return this.findTasks(input);
            case "delete":
                return this.deleteTask(input);
            default:
                try {
                    Task task = taskList.taskCreator(input);
                    this.taskList.add(task);
                    storage.saveData();
                    return this.ui.echo(task);
                } catch (KaiYapException k) {
                    return this.ui.printError(k.getMessage());
                }
            }
        } catch (KaiYapException e) {
            return this.ui.printError(e.getMessage());
        }
    }

    /**
     * Deletes a task from the task list based on the user's input.
     * The input should contain a numeric index that corresponds to the task's position in the list.
     *
     * @param input A string containing the command and index of the task to be deleted.
     * @throws KaiYapException If the input is missing, invalid, or if the task does not exist.
     */
    public String findTasks(String input) throws KaiYapException {
        if (input.length() <= 5) {
            throw new MissingInputException(
                    "\tSorry, it seems like there is some missing input. Please try again! UwU :3"
            );
        }
        String searchPhrase = input.substring(5).toUpperCase();
        List<Task> tasksFound = taskList.findTasks(searchPhrase);
        if (tasksFound.isEmpty()) {
            throw new InvalidInputException("\tSorry, no such tasks exist. Please try again! UwU :3");
        } else {
            return ui.printTasksFound(tasksFound);
        }
    }

    /**
     * Deletes a task from the task list based on the user's input.
     * The input should contain a numeric index that corresponds to the task's position in the list.
     *
     * @param input A string containing the command and index of the task to be deleted.
     * @throws KaiYapException If the input is missing, invalid, or if the task does not exist.
     */
    public String deleteTask(String input) throws KaiYapException {
        if (input.length() <= 7) {
            throw new MissingInputException(
                    "\tSorry, it seems like there is some missing input. Please try again! UwU :3"
            );
        }
        int numericIndex = Integer.parseInt(input.substring(7).strip()) - 1;
        if (numericIndex >= this.taskList.size()) {
            throw new InvalidInputException("\tSorry, this task does not exist. Please try again! UwU :3");
        } else {
            String output = ui.printTaskRemoved(taskList.get(numericIndex), taskList.size() - 1);
            taskList.remove(numericIndex);
            return output;
        }
    }

    /**
     * Marks a task as done in the task list based on the user's input.
     * The input should contain a numeric index that corresponds to the task's position in the list.
     * If the task is already marked as done, it throws an exception.
     *
     * @param index A string containing the command and index of the task to be marked as done.
     * @throws KaiYapException If the input is missing, invalid, or if the task is already marked as done.
     */
    public String markAsDone(String index) throws KaiYapException {
        if (index.length() <= 5) {
            throw new MissingInputException(
                    "\tSorry, it seems like there is some missing input. Please try again! UwU :3"
            );
        }
        index = index.substring(index.indexOf(' ') + 1);
        int[] numericIndices = Arrays.stream(index.split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();
        for (int numericIndex : numericIndices) {
            assert numericIndex > 0 && numericIndex <= this.taskList.size() : "Task index out of bounds";
            if (numericIndex <= 0 || numericIndex > this.taskList.size()) {
                throw new InvalidInputException("\tSorry, this task does not exist. Please try again! UwU :3");
            } else if (taskList.get(numericIndex - 1).isTaskDone()) {
                throw new AlreadyExistsException("\tOne of these tasks has already been marked as done. Great job!");
            } else {
                taskList.get(numericIndex - 1).setTaskDone(true);
                storage.saveData();
            }
        }
        return ui.printTaskMarked(numericIndices);
    }

    /**
     * Marks a task as not done in the task list based on the user's input.
     * The input should contain a numeric index that corresponds to the task's position in the list.
     * If the task is already marked as not done, it throws an exception.
     *
     * @param index A string containing the command and index of the task to be marked as not done.
     * @throws KaiYapException If the input is missing, invalid, or if the task is already marked as not done.
     */
    public String unmarkDone(String index) throws KaiYapException {
        if (index.length() <= 7) {
            throw new MissingInputException(
                    "\tSorry, it seems like there is some missing input. Please try again! UwU :3"
            );
        }
        try {
            int numericIndex = Integer.parseInt(index.substring(7).strip()) - 1;
            if (numericIndex >= taskList.size()) {
                throw new InvalidInputException("\tSorry, this task does not exist. Please try again! UwU :3");
            } else if (!taskList.get(numericIndex).isTaskDone()) {
                throw new AlreadyExistsException("\tThis task has already been marked as undone. Good luck!");
            } else {
                taskList.get(numericIndex).setTaskDone(false);
                storage.saveData();
                return ui.printTaskUnmarked(numericIndex);
            }
        } catch (Exception e) {
            throw new InvalidInputException("\tSorry, your input is invalid. Please try again! UwU :3");
        }
    }
}
