package kervyn.Commands;
import javafx.application.Platform;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import kervyn.FXControls.DialogBox;
import kervyn.Storage;
import kervyn.Tasks.Task;
import kervyn.Tasks.TaskList;

/**
 * Represents the "Bye" command in the application. This command is used to exit the application.
 */
public class ByeCommand extends Command {
    private Storage storage;
    private Image kervynImage;
    private VBox dialogContainer;
    /**
     * Constructs a ByeCommand with the specified TaskList and Storage.
     *
     * @param taskList The TaskList associated with this command.
     * @param storage The Storage object used for saving tasks.
     */
    public ByeCommand(TaskList taskList, Storage storage, Image kervynImage, VBox dialogContainer) {
        super("Bye", taskList, kervynImage, dialogContainer);
        this.storage = storage;
        this.kervynImage = kervynImage;
        this.dialogContainer = dialogContainer;
    }


    /**
     * Executes the "Bye" command.
     * This method outputs a farewell message, saves the current tasks to storage, and prepares for application exit.
     */
    @Override
    public void executeCommand() {
        dialogContainer.getChildren().add(
                DialogBox.getKervynDialog("\tBye. Hope to see you again soon!", kervynImage)
        );
        StringBuilder content = new StringBuilder();
        for (Task userRequest : this.taskList.getTaskList()) {
            content.append(userRequest.toString()).append("\n");
        }

        storage.writeToFile(content.toString());
        Platform.exit();
    }
}
