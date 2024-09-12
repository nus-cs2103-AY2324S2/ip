package kervyn.Commands;

import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import kervyn.FXControls.DialogBox;
import kervyn.Tasks.Deadline;
import kervyn.Tasks.Task;
import kervyn.Tasks.TaskList;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Objects;

/**
 * Represents the "Deadline" command in the application, used to create and add a Deadline task to the TaskList.
 */
public class DeadlineCommand extends Command {
    private String userInput;
    private Image kervynImage;

    private VBox dialogContainer;
    /**
     * Constructs a DeadlineCommand with the specified TaskList and user input.
     *
     * @param taskList The TaskList associated with this command.
     * @param userInput The user input string containing the deadline details.
     */
    public DeadlineCommand(TaskList taskList, String userInput, Image kervynImage, VBox dialogContainer) {
        super("Deadline", taskList, kervynImage, dialogContainer);
        this.userInput = userInput;
        this.kervynImage = kervynImage;
        this.dialogContainer = dialogContainer;
    }

    /**
     * Processes the user input and creates a Deadline task.
     * The method parses the input, converts the date, and constructs a Deadline object.
     *
     * @param userInput The user input string to process.
     * @return A Deadline object, or null if the input format is invalid.
     */
    private Deadline getProcessedDeadline(String userInput) {
        // Input Format: deadline return book /by Sunday
        // Output Format: [D][ ] return book (by: Sunday)
        try {
            String[] deadlineProcessedInput = userInput.split("/");
            if (Objects.equals(deadlineProcessedInput[1], "")) {
                this.dialogContainer.getChildren().add(
                        DialogBox.getKervynDialog("\tThe deadline of a Deadline task cannot be empty. Please try again.", this.kervynImage)
                );
                return null;
            }
            String[] deadlineDescriptionArray = deadlineProcessedInput[0].split(" ");

            StringBuilder deadlineDescription = new StringBuilder();

            for (int i = 1; i < deadlineDescriptionArray.length; i++) {
                deadlineDescription.append(" ");
                deadlineDescription.append(deadlineDescriptionArray[i]);
            }

            String[] deadlineTimeArray = userInput.split("/by");
            // No longer a String
            String convertedDeadline = convertDate(deadlineTimeArray[1].trim());

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
            LocalDateTime deadline = LocalDateTime.parse(convertedDeadline,  formatter);

            taskAdded();
            return new Deadline(deadlineDescription.toString(), false, deadline);
        }
        catch (ArrayIndexOutOfBoundsException | DateTimeParseException e) {
            this.dialogContainer.getChildren().add(
                    DialogBox.getKervynDialog("\tPlease provide the deadline in the required format.", this.kervynImage)
            );
            return null;
        }
    }

    /**
     * Displays information about the newly added Deadline task.
     *
     * @param deadline The Deadline task that was added.
     * @param userTasks The current list of tasks, including the newly added deadline task.
     */
    private void deadlineTaskTextDisplay(Deadline deadline, ArrayList<Task> userTasks) {
        this.dialogContainer.getChildren().add(
                DialogBox.getKervynDialog(deadline.toString(), this.kervynImage)
        );
        this.dialogContainer.getChildren().add(
                DialogBox.getKervynDialog("\tNow you have " + userTasks.size() + " tasks in the list.", this.kervynImage)
        );
    }


    /**
     * Executes the "Deadline" command.
     * This method processes the user input, creates a new Deadline task, adds it to the task list, and displays a confirmation message.
     */
    @Override
    public void executeCommand() {
        Deadline newDeadline = getProcessedDeadline(this.userInput);
        if (newDeadline != null) {
            this.taskList.addTask(newDeadline);
            deadlineTaskTextDisplay(newDeadline, this.taskList.getTaskList());
        }
    }
}
