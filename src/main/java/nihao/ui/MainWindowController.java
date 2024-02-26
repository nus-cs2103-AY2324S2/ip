package nihao.ui;

import javafx.css.PseudoClass;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.ScrollEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import nihao.Nihao;

/**
 * Controller for MainWindow. Provides the layout for the other controls.
 */
public class MainWindowController extends AnchorPane {
    @FXML
    private ScrollPane dialoguePane;
    @FXML
    private VBox dialogueContainer;
    @FXML
    private TextField inputField;
    @FXML
    private Button inputButton;
    @FXML
    private HBox inputBox;
    private Nihao nihao;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/user.png"));
    private Image nihaoImage = new Image(this.getClass().getResourceAsStream("/images/logo_600.png"));

    @FXML
    public void initialize() {
        dialoguePane.vvalueProperty().bind(dialogueContainer.heightProperty());
    }

    public void setNihao(Nihao nihao) {
        this.nihao = nihao;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String userText = inputField.getText();
        if (userText.isEmpty()) {
            return;
        }
        String nihaoText = nihao.getResponse(inputField.getText());
        dialogueContainer.getChildren().addAll(
                DialogueBoxController.getUserDialogue(userText, userImage),
                DialogueBoxController.getNihaoDialogue(nihaoText, nihaoImage)
        );
        inputField.clear();
    }

    @FXML
    private void handleEnterReleased(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER) {
            this.inputField.setEditable(true);
            this.inputField.requestFocus();
        }
    }
}
