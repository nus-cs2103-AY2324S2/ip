package kervyn.Commands;

import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import kervyn.FXControls.DialogBox;
import kervyn.Tasks.TaskList;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents a generic command in the application.
 * This class is designed to be extended by specific command types.
 */
public class Command {
    private String keyword;
    protected TaskList taskList;
    private Image kervynImage;
    private VBox dialogContainer;

    /**
     * Constructs a Command object with a specific keyword and associated TaskList.
     *
     * @param keyword The keyword that triggers this command.
     * @param taskList The TaskList associated with this command.
     */
    public Command(String keyword, TaskList taskList, Image kervynImage, VBox dialogContainer) {
        this.keyword = keyword;
        this.taskList = taskList;
        this.kervynImage = kervynImage;
        this.dialogContainer = dialogContainer;
    }

    /**
     * Displays a message indicating that a task has been added.
     */
    public void taskAdded() {
        this.dialogContainer.getChildren().add(
                DialogBox.getKervynDialog("\tUnderstood. I've added this task:", this.kervynImage)
        );
    }

    /**
     * Converts a date string from the format "dd-MM-yyyy HHmm" to "yyyy-MM-dd HHmm".
     *
     * @param inputDateTime The date string to be converted.
     * @return The converted date string or null if the input format is invalid.
     */
    public String convertDate(String inputDateTime) {
        DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HHmm");
        DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");

        try {
            LocalDateTime dateTime = LocalDateTime.parse(inputDateTime, inputFormatter);
            return dateTime.format(outputFormatter);
        } catch (DateTimeParseException e) {
            this.dialogContainer.getChildren().add(
                    DialogBox.getKervynDialog("\tInvalid date format, please try again with a format that looks like dd-MM-yyyy HHmm", this.kervynImage)
            );
            return null;
        }
    }

    /**
     * Executes the command. This method is intended to be overridden by subclasses.
     */
    public void executeCommand() {}
}
