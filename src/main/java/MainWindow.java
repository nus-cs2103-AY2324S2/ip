import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

public class MainWindow extends AnchorPane {
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private Lai lai;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/user.png"));
    private Image laiImage = new Image(this.getClass().getResourceAsStream("/images/lai.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    public void setLai(Lai l) {
        lai = l;
    }

    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = lai.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getLaiDialog(response, laiImage)
        );
        userInput.clear();
    }
}