package lamball.ui;

import static javafx.application.Platform.exit;

import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import lamball.Lamball;

/**
 * Command text field class
 */
public class CommandTextField extends HBox {
    private static final String EXIT_TEXT = "     See you again!\n";
    private Image user = new Image(this.getClass().getResourceAsStream("/images/User.png"));
    private Image lamballImg = new Image(this.getClass().getResourceAsStream("/images/Lamball.png"));

    private TextField userInput;
    private Button sendButton;
    private ScrollableList chatOutputLocation;
    private Lamball lamb;

    /**
     * Constructor for Command Text field.
     */
    public CommandTextField(ScrollableList output, Lamball lamb) {
        this.userInput = new TextField();
        this.sendButton = new Button();
        this.chatOutputLocation = output;
        this.lamb = lamb;
        this.init();
    }

    private void init() {
        sendButton.setPrefWidth(55.0);
        userInput.setMinWidth(325.0);
        sendButton.setText("Send");

        sendButton.setOnMouseClicked((event) -> {
            handleUserInput();
        });

        userInput.setOnAction((event) -> {
            handleUserInput();
        });

        this.getChildren().addAll(userInput, sendButton);
    }

    private void handleUserInput() {
        Text userText = new Text(userInput.getText());
        Text lamballText = new Text(lamb.getResponse(userInput.getText()));
        chatOutputLocation.addToList(
                DialogBox.getUserDialog(userText, new ImageView(user), 400),
                DialogBox.getLamballDialog(lamballText, new ImageView(lamballImg), 400)
        );

        if (lamballText.getText() == EXIT_TEXT) {
            exit();
        }
        userInput.clear();
    }

}
