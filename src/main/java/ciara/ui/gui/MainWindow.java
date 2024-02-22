package ciara.ui.gui;

import ciara.commands.Command;
import ciara.exceptions.CiaraException;
import ciara.exceptions.StorageException;
import ciara.ui.Gui;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

/**
 * Controller for MainWindow of the GUI. Provides the layout for the other
 * controls.
 *
 * @author Ryan NgWH
 */
public class MainWindow extends AnchorPane {
    // Elements of the main GUI window
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    // Instance of the GUI
    private Gui gui;

    // Images for the chatbot
    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/User.jpg"));
    private Image ciaraImage = new Image(this.getClass().getResourceAsStream("/images/Ciara.jpg"));

    /**
     * Initializes the main window
     */
    @FXML
    public void initialize() throws StorageException {
        scrollPane.vvalueProperty().bind(this.dialogContainer.heightProperty());

        // UI Greeting
        String greeting = "Hello! I'm Ciara\n"
                + "What can I do for you?";

        dialogContainer.getChildren().addAll(
                DialogBox.getCiaraDialog(greeting, this.ciaraImage));

    }

    /**
     * Sets GUI instance of the main window
     *
     * @param gui GUI instance to set
     */
    public void setGui(Gui gui) {
        this.gui = gui;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing
     * Ciara's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() throws StorageException {
        String userText = this.userInput.getText();
        String ciaraText = "";
        Boolean toExit = false;
        try {
            Command command = gui.getCommand(this.userInput.getText());

            if (command.isExit()) {
                toExit = true;
            }

            ciaraText = gui.getResponse(command);
        } catch (CiaraException e) {
            ciaraText = String.format("Sorry! I got the following error:\n %s", e.getMessage());
        } finally {
            dialogContainer.getChildren().addAll(
                    DialogBox.getUserDialog(userText, this.userImage),
                    DialogBox.getCiaraDialog(ciaraText, this.ciaraImage));

            this.userInput.clear();

            if (toExit) {
                System.exit(0);
            }
        }

    }
}
