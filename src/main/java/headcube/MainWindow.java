package headcube;

import javafx.fxml.FXML;
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

    private HeadCube headcube;
    private final Image USER_IMAGE = new Image(this.getClass().getResourceAsStream("/images/user.png"));
    private final Image HEADCUBE_IMAGE = new Image(this.getClass().getResourceAsStream("/images/headcube.png"));

    /**
     * Initializes the controller class. This method is automatically called
     * after the fxml file has been loaded. Sets up the initial dialog box and binds
     * the scroll pane's vertical scroll property to the dialog container's height property
     * to ensure it scrolls to the bottom as content is added.
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        Ui ui = new Ui();

        dialogContainer.getChildren().addAll(
                DialogBox.getHeadCubeDialog(ui.greet(), HEADCUBE_IMAGE)
        );
    }

    public void setHeadCube(HeadCube h) {
        headcube = h;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing HeadCube's reply and then appends them
     * to the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = input.equals("bye") ? Ui.exit() : headcube.getParser().parse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, USER_IMAGE),
                DialogBox.getHeadCubeDialog(response, HEADCUBE_IMAGE)
        );
        userInput.clear();
    }
}
