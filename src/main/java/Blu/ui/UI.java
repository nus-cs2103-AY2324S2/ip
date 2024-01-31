package blu.ui;

import static blu.utils.Messages.MESSAGE_DIVIDER;
import static blu.utils.Messages.MESSAGE_EXIT;
import static blu.utils.Messages.MESSAGE_GREET;
import static blu.utils.Messages.MESSAGE_STORAGE_PATH;

import java.io.PrintWriter;
import java.util.Scanner;

import blu.task.Task;
import blu.task.TaskList;

/**
 * Handles all user input/output operations for the Blu application.
 */
public class UI {
    private static final String ANSI_RESET = "\u001B[0m";
    private static final String ANSI_RED = "\u001B[31m";
    private static final String PROMPT = "> ";
    private final Scanner in;
    private final PrintWriter out;

    /**
     * Constructs a UI object. Initializes the Scanner for handling user input 
     * and PrintWriter handling user output.
     */
    public UI() {
        this.in = new Scanner(System.in);
        this.out = new PrintWriter(System.out);
    }

    private void showToUser(String message) {
        out.println(MESSAGE_DIVIDER);
        out.println(message);
        out.println(MESSAGE_DIVIDER);
        out.flush();
    }

    private void showToUser(String[] messages) {
        out.println(MESSAGE_DIVIDER);
        for (String message : messages) {
            out.println(message);
        }
        out.println(MESSAGE_DIVIDER);
        out.flush();
    }

    /**
     * Displays the welcome message and the storage file path.
     * 
     * @param storageFilePath The file path of the storage used.
     */
    public void showWelcomeMessage(String storageFilePath) {
        String storagePathString = String.format(MESSAGE_STORAGE_PATH, storageFilePath);
        String[] messages = {storagePathString, MESSAGE_GREET};
        showToUser(messages);
    }

    /**
     * Prompts the user and reads the input provided.
     * 
     * @return The user input as a String.
     */
    public String getUserInput() {
        out.print(PROMPT);
        out.flush();
        String userInput = in.nextLine();
        return userInput;
    }

    /**
     * Displays the tasks in the task list to the user.
     * 
     * @param taskList The TaskList to be displayed.
     */
    public void showTaskList(TaskList taskList) {
        showToUser(taskList.toString());
    }

    /**
     * Displays a message indicating that a task has been added.
     * 
     * @param task The task that was added.
     * @param taskList The TaskList after adding the new task.
     */
    public void showTaskAdded(Task task, TaskList taskList) {
        String[] messages = {"Added task to the list:", task.toString(), "You have " + taskList.getNumberOfTasks() 
            + " tasks currently."};
        showToUser(messages);
    }

    /**
     * Displays a message indicating that a task has been deleted.
     * 
     * @param task The task that was deleted.
     * @param taskList The TaskList after deleting the task.
     */
    public void showTaskDeleted(Task task, TaskList taskList) {
        String[] messages = {"Deleted task from list:", task.toString(), "You have " 
            + taskList.getNumberOfTasks() + " tasks currently"};
        showToUser(messages);
    }

    /**
     * Displays a message indicating that a task has been marked successfully.
     * 
     * @param task The task that was marked.
     * @param taskIdx The index of the marked task.
     */
    public void showTaskMarked(Task task, int taskIdx) {
        String[] messages = {"Marked task as done:", task.toString()};
        showToUser(messages);
    }

    /**
     * Displays a message indicating that a task is already marked.
     * 
     * @param taskIdx The index of the marked task.
     */
    public void showTaskAlreadyMarked(int taskIdx) {
        showToUser("Task number " + taskIdx + " is already marked as done");
    }

    /**
     * Displays a message indicating that a task is already unmarked.
     * 
      * @param task The task that was unmarked.
     * @param taskIdx The index of the unmarked task.
     */
    public void showTaskAlreadyUnmarked(int taskIdx) {
        showToUser("Task number " + taskIdx + " is already unmarked as not done");
    }

    /**
     * Displays a message indicating that a task is already unmarked.
     * 
     * @param taskIdx The index of the unmarked task.
     */
    public void showTaskUnmarked(Task task) {
        String[] messages = {"Unmarked task as not done:", task.toString()};
        showToUser(messages);
    }

    /**
     * Displays an error message to the user.
     * 
     * @param errorMsg The error message to be displayed.
     */
    public void showErrorMessage(String errorMsg) {
        out.println(ANSI_RED + errorMsg + ANSI_RESET);
        out.flush();
    }

    /**
     * Displays the exit message to the user.
     */
    public void showExitMessage() {
        showToUser(MESSAGE_EXIT);
    }

    /**
     * Closes the input and output streams associated with this UI.
     */
    public void exit() {
        in.close();
        out.close();
    }
}
