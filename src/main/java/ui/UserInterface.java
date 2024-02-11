package ui;

import exceptions.DukeException;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import parser.Parser;
import storage.TaskList;
import tasks.Task;

import java.io.IOException;


/**
 * Manages GUI
 */
public class UserInterface {
    public static final String OPENING_MSG = "Hello! I'm Stille\n" + "What can I do for you?\n";
    public static final String CLOSING_MSG = "Bye. Hope to see you again soon!";
    public static final String ERROR_MSG = "Error: ";

    private MainWindow mainWindow;
    private Scene scene;
    private TaskList list;

    public UserInterface(TaskList list) {
        this.list = list;
        this.mainWindow = new MainWindow(this);
        this.scene = new Scene(this.mainWindow);
    }

    public Scene getScene() {
        return this.scene;
    }

    public void runCommand(String input) {
        boolean isExit;
        try {
             isExit = Parser.parseInput(input).execute(this.list, this);
             if (isExit) {
                 Platform.exit();
             }
        } catch (DukeException e) {
            this.showError(e);
        }
    }

    public void showMessage(String message) {
        this.mainWindow.displayText(message);
    }
    public void showOpeningMessage() {
        this.showMessage(OPENING_MSG);
    }

    public void showClosingMessage() {
        this.showMessage(CLOSING_MSG);
    }

    public void showError(Exception e) {
        this.showMessage(ERROR_MSG + e.getMessage());
    }

    public void showList(String list) {
        this.showMessage("Here are the tasks in your list:\n" + list);
    }

    public void showMarkDone(Task task) {
        this.showMessage("Nice! I've marked this task as done:\n " + task);
    }

    public void showMarkNotDone(Task task) {
        this.showMessage("OK, I've marked this task as not done yet:\n " + task);
    }

    public void showDelete(Task task) {
        this.showMessage("Noted. I've removed this task:\n " + task);
    }

    public void showAdd(Task task) {
        this.showMessage("Got it. I've added this task:\n " + task);
    }

    public void showListSize(int size) {
        this.showMessage("Now you have " + size + " tasks in the list.");
    }

    public void showFound(String list) {
        this.showMessage("Here are the matching tasks in your list:\n" + list);
    }

}
