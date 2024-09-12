package kervyn.Commands;

import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import kervyn.Tasks.TaskList;

/**
 * Represents the "Delete" command in the application, used to delete a task from the TaskList.
 */
public class DeleteCommand extends Command {
    private String[] userInput;
    private Image kervynImage;
    private VBox dialogContainer;
    /**
     * Constructs a DeleteCommand with the specified TaskList and user input.
     *
     * @param taskList The TaskList associated with this command.
     * @param userInput The user input array containing details for the deletion command.
     */
    public DeleteCommand(TaskList taskList, String[] userInput, Image kervynImage, VBox dialogContainer) {
        super("Delete", taskList, kervynImage, dialogContainer);
        this.userInput = userInput;
        this.kervynImage = kervynImage;
        this.dialogContainer = dialogContainer;
    }

    /**
     * Executes the "Delete" command.
     * This method removes a task from the task list based on the index provided in the user input.
     */
    @Override
    public void executeCommand() {
        taskList.removeTask(taskList.getTaskList(), this.userInput, this.kervynImage, this.dialogContainer);
    }
}
