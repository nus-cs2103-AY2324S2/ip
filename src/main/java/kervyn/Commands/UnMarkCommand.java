package kervyn.Commands;

import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import kervyn.Tasks.TaskList;

/**
 * Represents the "UnMark" command in the application, used to mark a task as not completed in the TaskList.
 */
public class UnMarkCommand extends Command {
    private String[] userInput;
    private Image kervynImage;
    private VBox dialogContainer;

    /**
     * Constructs an UnMarkCommand with the specified TaskList and user input.
     *
     * @param taskList The TaskList associated with this command.
     * @param userInput The user input array containing the index of the task to be unmarked.
     */
    public UnMarkCommand(TaskList taskList, String[] userInput, Image kervynImage, VBox dialogContainer) {
        super("UnMark", taskList, kervynImage, dialogContainer);
        this.userInput = userInput;
        this.kervynImage = kervynImage;
        this.dialogContainer = dialogContainer;
    }

    /**
     * Executes the "UnMark" command.
     * This method marks a task as not completed in the task list based on the index provided in the user input.
     */
    @Override
    public void executeCommand() {
        taskList.unMarkTask(taskList.getTaskList(), this.userInput, this.kervynImage, this.dialogContainer);
    }
}
