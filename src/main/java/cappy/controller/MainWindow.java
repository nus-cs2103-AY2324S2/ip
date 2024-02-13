package cappy.controller;

import static cappy.constant.FilePath.CAPPY_IMAGE_PATH;
import static cappy.constant.FilePath.USER_IMAGE_PATH;
import static cappy.constant.Message.GOODBYE;
import static cappy.constant.Message.GREETING;

import cappy.Cappy;
import cappy.task.Task;
import cappy.task.TaskList;
import cappy.ui.Ui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

/** Controller for MainWindow. Provides the layout for the other controls. */
public class MainWindow extends AnchorPane implements Ui {
    @FXML private ScrollPane scrollPane;
    @FXML private VBox dialogContainer;
    @FXML private TextField userInput;
    @FXML private Button sendButton;

    private Cappy cappy;

    private Image userImage = new Image(this.getClass().getResourceAsStream(USER_IMAGE_PATH));
    private Image dukeImage = new Image(this.getClass().getResourceAsStream(CAPPY_IMAGE_PATH));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    public void setCappy(Cappy c) {
        assert c != null : "Cannot set cappy to be null!";
        cappy = c;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Cappy's reply and
     * then appends them to the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        userShow(input);
        userInput.clear();
        cappy.handleUserInput(input);
    }

    @FXML
    public void userShow(String message) {
        dialogContainer.getChildren().add(DialogBox.getUserDialog(message, userImage));
    }

    @FXML
    public void show(String message) {
        dialogContainer.getChildren().add(DialogBox.getCappyDialog(message, dukeImage));
    }

    /**
     * Shows the given messages to the user. Each message will be on a newline.
     *
     * @param messages The String array of messages to be displayed.
     */
    public void show(String[] messages) {
        show(String.join("\n", messages));
    }

    @FXML
    public void showError(String error) {
        dialogContainer.getChildren().add(DialogBox.getCappyDialog(error, dukeImage));
    }

    @FXML
    public void showError(Exception e) {
        dialogContainer.getChildren().add(DialogBox.getCappyDialog(e.getMessage(), dukeImage));
    }

    /**
     * Shows the newly added task and the current number of tasks in the task list to the user.
     *
     * @param task The newly added task.
     * @param tasks The current TaskList.
     */
    public void showAddedTask(Task task, TaskList tasks) {
        String[] messages = {
            "Got it. I've added this task:",
            task.toString(),
            "Now you have " + tasks.size() + " tasks in the list."
        };
        show(messages);
    }

    /** Shows a greeting message to the user. */
    public void showGreetings() {
        show(GREETING);
    }

    /** Shows a farewell message to the user. */
    public void showExit() {
        show(GOODBYE);
    }
}
