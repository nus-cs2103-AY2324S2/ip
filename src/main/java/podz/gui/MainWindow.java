package podz.gui;

import javafx.animation.PauseTransition;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;
import javafx.util.Duration;
import podz.Podz;
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
    @FXML
    private ImageView imgTop;

    private Podz podz;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/user.png"));
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/podz.png"));

    @FXML
    public void initialize() {
        assert scrollPane != null : "ScrollPane is not initialized";
        assert dialogContainer != null : "DialogContainer is not initialized";
        assert userInput != null : "UserInput is not initialized";
        assert sendButton != null : "SendButton is not initialized";
        assert imgTop != null : "ImgTop is not initialized";

        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        Image img = new Image(this.getClass().getResourceAsStream("/images/arrow-up.png"));
        ImageView view = new ImageView(img);
        view.setFitHeight(10);
        view.setFitWidth(15);
        sendButton.setGraphic(view);
        Circle clip = new Circle(25, 25, 25);
        imgTop.setClip(clip);
    }

    public void setPodz(Podz p) {
        this.podz = p;
        assert podz != null : "Podz is not initialized";

        dialogContainer.getChildren().addAll(
            DialogBox.getDukeDialog(podz.getGreeting(), dukeImage)
        );
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        if (input.isEmpty()) {
            return;
        }
        
        String response = podz.getResponse(input);
        setDialog(input, response);
        userInput.clear();
        closeGuiIfExit();
    }

    private void setDialog(String input, String response) {
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getDukeDialog(response, dukeImage)
        );
    }

    private void closeGuiIfExit() {
        if (this.podz.hasExit()) {
            PauseTransition delay = new PauseTransition(Duration.seconds(2));
            delay.setOnFinished(event -> Platform.exit());
            delay.play();
        }
    }
}
