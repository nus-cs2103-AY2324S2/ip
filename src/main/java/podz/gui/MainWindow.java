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
import javafx.scene.layout.HBox;
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
    @FXML
    private HBox topHBox;

    private Podz podz;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        Image img = new Image(this.getClass().getResourceAsStream("/images/arrow-up.png"));
        ImageView view = new ImageView(img);
        view.setFitHeight(10);
        view.setFitWidth(15);
        sendButton.setGraphic(view);
        // userInput.setStyle("-fx-text-inner-color: #FFFFFF;");
        Circle clip = new Circle(25, 25, 25);
        imgTop.setClip(clip);
        // topHBox.setEffect(new DropShadow(8, Color.rgb(0, 0, 0, 0.8)));
    }

    public void setPodz(Podz p) {
        this.podz = p;
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
        String response = podz.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getDukeDialog(response, dukeImage)
        );
        userInput.clear();
        if (this.podz.hasExit()) {
            PauseTransition delay = new PauseTransition(Duration.seconds(2));
            delay.setOnFinished(event -> Platform.exit());
            delay.play();
        }
    }
}

