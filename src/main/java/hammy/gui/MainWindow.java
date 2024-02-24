package hammy.gui;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import java.io.IOException;

/**
 * Controller for MainWindow. Provides the layout for the other controls.
 */
public class MainWindow extends AnchorPane {
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private Hammy hammy;
    private String hello =
            "| | | |        | | |" +
            "\n| |_| |/ _ \\ | | | / _ \\" +
            "\n|  _  |  __/ | | | |(_) |" +
            "\n|_| |_|\\___ |_|_|\\___/";

    private String cat =
            "      /\\_____/\\" +
            "\n    /   >    <   \\" +
            "\n   ( ==  3  == )" +
            "\n    )                (" +
            "\n   (                  )" +
            "\n  ( (  )          (  ) )" +
            "\n (_(_ )_______( _)_)";
    private String intro = "Hiii!! I am Hammy, your favourite chat bot <3";
    private String tip = "Enter \"help\" to check the list of available commands!"+
                "\nEnter \"save\" to save your tasks!" +
                "\n\"exit\" and \"bye\" commands do not save tasks.";

    /**
     * The initial string for the users
     * Acts as an in-app user guide
     */
    private String initialString = hello + "\n\n" + intro + "\n\n" + cat + "\n\n" + tip;

    /**
     * The profile image of the user
     */
    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));

    /**
     * The profile image of the bot
     */
    private Image botImage = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        dialogContainer.getChildren().addAll(
                DialogBox.getDukeDialog(initialString, botImage)
        );
    }

    public void setHammy(Hammy hammy) {
        this.hammy = hammy;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() throws IOException {
        String input = userInput.getText().trim();
        String response = hammy.getResponse(input);
        if (input.isEmpty()){
            return;
        }
        if (input.equalsIgnoreCase("exit") || input.equalsIgnoreCase("bye") || input.equalsIgnoreCase("quit")) {
            Platform.exit();
            return;
        }
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getDukeDialog(response, botImage)
        );
        userInput.clear();
    }
}
