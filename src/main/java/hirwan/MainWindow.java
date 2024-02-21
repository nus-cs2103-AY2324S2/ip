package hirwan;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
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

    private Hirwan hirwan;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image hirwanImage = new Image(this.getClass().getResourceAsStream("/images/photo_2024-01-20_00-10-13.jpg"));

    /**
     * initialises the mainwindow pane
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /**
     * initialises the hirwan object
     * @param d the hirwan object initialised
     */
    public void setHirwan(Hirwan d) {
        hirwan = d;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = hirwan.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getHirwanDialog(response, hirwanImage)
        );
        userInput.clear();
    }

    /**
     * prints the greeting message when the bot starts up
     */
    public void printGreeting() {
        String msg = "I'm\n"
                + "   //      //  //  //==\\\\  //             //    //||       //||   //\n"
                + "  //____//  //  //    //  //             //    //  ||     // ||  //\n"
                + " //----//  //  //=\\\\   //     //     //   //==||   //  || //\n"
                + "//     //  //  //     \\\\ //==//==//  //       || //   ||//\n"
                + "_________________________________\n"
                + "what can I do for you? \n"
                + "_________________________________\n";

        dialogContainer.getChildren().add(DialogBox.getHirwanDialog(msg, hirwanImage));
    }
}