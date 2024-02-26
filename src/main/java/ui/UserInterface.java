package ui;

import java.time.format.DateTimeParseException;

import exceptions.DukeException;
import javafx.application.Platform;
import javafx.scene.Scene;
import parser.Parser;
import storage.Storage;
import storage.TaskList;
import tasks.Task;


/**
 * Manages interaction between MainWindow, TaskList, and Storage.
 */
public class UserInterface {
    public static final String OPENING_MSG = "Hello! I'm Stille\n" + "What can I do for you?\n";
    public static final String CLOSING_MSG = "Bye. Hope to see you again soon!\n";
    public static final String ERROR_MSG = "Error: ";

    private MainWindow mainWindow;
    private Scene scene;
    private TaskList list;
    private Storage storage;

    /**
     * Instantiates UserInterface object.
     */
    public UserInterface(TaskList list, Storage storage) {
        this.list = list;
        this.mainWindow = new MainWindow(this);
        this.scene = new Scene(this.mainWindow);
        this.storage = storage;
    }

    public Scene getScene() {
        return this.scene;
    }

    /**
     * Executes the command corresponding to the given user input.
     *
     * @param input String to be parsed to a Command.
     */
    public void runCommand(String input) {
        boolean isExit;
        try {
            isExit = Parser.parseInput(input).execute(this.list, this);
            if (isExit) {
                Platform.exit();
            }
        } catch (DukeException e) {
            this.showError(e);
        } catch (DateTimeParseException e) {
            this.showMessage("Please enter dates in the form yyyy-mm-dd");
        }
    }

    /**
     * Displays the given message to the user.
     */
    public void showMessage(String message) {
        this.mainWindow.displayText(message);
    }

    /**
     * Displays the predefined opening message.
     */
    public void showOpeningMessage() {
        this.showMessage(OPENING_MSG);
    }

    /**
     * Displays the predefined closing message.
     */
    public void showClosingMessage() {
        this.showMessage(CLOSING_MSG);
    }

    /**
     * Displays an error warning along with the error message.
     * @param e Exception containing the message to be displayed.
     */
    public void showError(Exception e) {
        this.showMessage(ERROR_MSG + e.getMessage());
    }

    /**
     * Displays the given String assumed to be the String representation of TaskList.
     */
    public void showList(String list) {
        this.showMessage("Here are the tasks in your list:\n" + list);
    }

    /**
     * Displays a message indicating that a task has been marked done along with the String representation
     * of the task itself.
     */
    public void showMarkDone(Task task) {
        this.showMessage("Nice! I've marked this task as done:\n " + task);
    }

    /**
     * Displays a message indicating that a task has been marked not done along with the String representation
     * of the task itself.
     */
    public void showMarkNotDone(Task task) {
        this.showMessage("OK, I've marked this task as not done yet:\n " + task);
    }

    /**
     * Displays a message indicating that a task has been deleted from the list along with the String representation
     * of the task itself.
     */
    public void showDelete(Task task) {
        this.showMessage("Noted. I've removed this task:\n " + task);
    }

    /**
     * Displays a message indicating that a task has been added to the list along with the String representation
     * of the task itself.
     */
    public void showAdd(Task task) {
        this.showMessage("Got it. I've added this task:\n " + task);
    }

    /**
     * Displays the size of the current list.
     */
    public void showListSize(int size) {
        this.showMessage("Now you have " + size + " tasks in the list.\n");
    }

    /**
     * Displays the given String assumed to be the String representation of the list of tasks found.
     */
    public void showFound(String list) {
        this.showMessage("Here are the matching tasks in your list:\n" + list);
    }

    /**
     * Displays the String representation of the task after update.
     */
    public void showUpdate(Task task) {
        this.showMessage("Sure! Here's how the task looks like now:\n " + task);
    }

}
