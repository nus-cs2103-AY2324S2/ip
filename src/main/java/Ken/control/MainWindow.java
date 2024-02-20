package ken.control;

import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import ken.Ken;
import ken.ui.Ui;

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

    private Ken ken;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/barbie.png"));
    private Image kenImage = new Image(this.getClass().getResourceAsStream("/images/ken.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    public void setKen(Ken k) {
        ken = k;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    public void handleUserInput() {
        String input = userInput.getText();
        String response = ken.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getKenDialog(response, kenImage)
        );
        userInput.clear();
    }

    Ui ui = new Ui();

    @FXML
    public void sayHi() {
        String kenText = ui.welcomeMessage().getMessage();
        dialogContainer.getChildren().addAll(
                DialogBox.getKenDialog(kenText, kenImage)
        );
        userInput.clear();
    }

}

