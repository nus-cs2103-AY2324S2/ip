package ui;
import javafx.application.Platform;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import parser.ParseExecutionable;
import parser.Parser;
import task.ActionTask;
import task.TaskStorage;
import util.DataWriter;

/**
 * This class abstracts the management of the input and output for the bot.
 *
 * It will process all inputs using the Duke methods.
**/
public class ChatManager {
    private UiManager uiManager;
    private Parser parser;
    private TaskStorage taskStorage;

    /**
     * Creates a ChatManager object.
     * Will override UiManager's button method to manage it.
     *
     * @param uiManager The uimanager shown to users.
     * @param parser The parser class to parse user inputs into actions.
     * @param taskStorage The storage of tasks.
     */
    public ChatManager(UiManager uiManager, Parser parser, TaskStorage taskStorage) {
        this.uiManager = uiManager;
        this.parser = parser;
        this.taskStorage = taskStorage;
    }

    /**
     * Initial setup of the class, and will override provided
     * uimanager object.
     *
     */
    public void setupHandlers() {
        uiManager.getSendButton().setOnAction(e -> handleUserInput());
        uiManager.getUserInput().setOnAction(e -> handleUserInput());
    }

    /**
     * This method help to manage the users input, by directing
     * them to the respective class, getting the respective images
     * and clearing the input once it has been sent.
     */
    private void handleUserInput() {
        String input = uiManager.getUserInput().getText();
        Label userText = new Label(input);
        Label dukeText = new Label(getResponse(input));
        DialogBox userDialog = new DialogBox(userText, new ImageView(uiManager.getUserImage()));

        uiManager.getDialogContainer().getChildren().addAll(
            userDialog, DialogBox.getDukeDialog(dukeText, new ImageView(uiManager.getDukeImage()))
        );
        uiManager.getUserInput().clear();
    }

    /**
     * This method will handle user input by parsering the input
     * and creating an Action object for it to be executed.
     * When executed, it will return the output of the action.
     */
    private String getResponse(String input) {
        ParseExecutionable actionable = parser.parseInput(input);
        String botResponse = actionable.execute(this.taskStorage);
        if (actionable instanceof ActionTask) {
            ActionTask actionTask = (ActionTask) actionable;
            if (actionTask.isItExitAction()) {
                this.saveStorageInformation();
                Platform.exit();
                return botResponse;
            }
        }
        return botResponse;
    }

    /**
     * This method will save all Task objects in the TaskStroage class
     * to the text file for persistence.
     */
    private void saveStorageInformation() {
        DataWriter dataWriter = new DataWriter();
        dataWriter.saveData(this.taskStorage);
    }
}
