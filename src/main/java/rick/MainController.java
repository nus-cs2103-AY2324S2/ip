package rick;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import rick.logic.RickException;
import rick.logic.command.Command;
import rick.ui.DialogBox;

import java.awt.*;

/**
 * A controller for the main window.
 */
public class MainController {
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private Button button;
    @FXML
    private TextField userInput;
    @FXML
    private VBox vBox;
    @FXML
    private Rick rick;
    @FXML
    private Image userImage = new Image(Main.class.getResourceAsStream("/images/morty.png"));
    @FXML
    private Image rickImage = new Image(Main.class.getResourceAsStream("/images/rick.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(vBox.heightProperty());
    }

    @FXML
    protected void handleUserInput() {
        String input = userInput.getText();
        if (!input.isEmpty()) {
            DialogBox dialogUser = DialogBox.getUserDialog(input, userImage);
            DialogBox dialogRick = DialogBox.getRickDialog(rick.getResponse(input), rickImage);
            vBox.getChildren().addAll(dialogUser, dialogRick);
            userInput.clear();
        }
    }

    public void setRick(Rick rick) {
        this.rick = rick;
    }
}
