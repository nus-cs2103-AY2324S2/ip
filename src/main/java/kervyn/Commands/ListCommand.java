package kervyn.Commands;

import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import kervyn.Tasks.TaskList;

/**
 * Represents the "List" command in the application, used to list all tasks in the TaskList.
 */
public class ListCommand extends Command {

    private Image kervynImage;
    private VBox dialogContainer;
    /**
     * Constructs a ListCommand with the specified TaskList.
     *
     * @param taskList The TaskList associated with this command.
     */
    public ListCommand(TaskList taskList, Image kervynImage, VBox dialogContainer) {
        super("List", taskList, kervynImage, dialogContainer);
        this.kervynImage = kervynImage;
        this.dialogContainer = dialogContainer;
    }

    /**
     * Executes the "List" command.
     * This method invokes the listing of all tasks present in the TaskList.
     */
    @Override
    public void executeCommand() {
        taskList.listTasks(taskList.getTaskList(), this.kervynImage, this.dialogContainer);
    }
}
