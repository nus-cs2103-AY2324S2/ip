package area;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
/**
 * Controller for MainWindow. Provides the layout for the other controls.
 * Citation: https://se-education.org/guides/tutorials/javaFxPart4.html
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

    private Area area = new Area();

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image areaImage = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));

    /**
     *  Creates a dialogue box displaying Area's welcome message.
     */

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        String greetUser = area.greetUser();
        dialogContainer.getChildren().addAll(
                DialogBox.getAreaDialog(greetUser, areaImage)
        );
    }

    /**
     * Assigns Area object to Area variable a.
     * 
     * @param a Area object with name a.
     */
    public void setArea(Area a) {
        area = a;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     * Citation:https://se-education.org/guides/tutorials/javaFxPart4.html
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = area.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getAreaDialog(response, areaImage)
        );

        if (input.equals("bye")) {
            javafx.application.Platform.exit();
        }

        userInput.clear();
    }
}


