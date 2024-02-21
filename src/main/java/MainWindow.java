import haro.Ui;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;
import haro.Haro;
import javafx.scene.SnapshotParameters;
import javafx.scene.paint.Color;

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

    private Haro haro;
    private double imageRadius = 220.0;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/Osaka.png"));
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/Haro.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        dialogContainer.getChildren().addAll(DialogBox.getDukeDialog(Ui.greet(), cropImage(dukeImage, imageRadius)));
    }

    public void setHaro(Haro h) {
        haro = h;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = "";
        try {
            response = haro.getResponse(input);
            dialogContainer.getChildren().addAll(
                    DialogBox.getUserDialog(input, cropImage(userImage, imageRadius)),
                    DialogBox.getDukeDialog(response, cropImage(dukeImage, imageRadius))
            );
        } catch (Exception e) {
            response = e.getMessage();
            dialogContainer.getChildren().addAll(
                    DialogBox.getUserDialog(input, cropImage(userImage, imageRadius)),
                    DialogBox.getDukeErrorDialog(response, cropImage(dukeImage, imageRadius))
            );
        } finally {
            userInput.clear();
            haro.saveList();
        }
    }

    private Image cropImage(Image image, double radius) {
        Circle clip = new Circle(image.getWidth() / 2, image.getHeight() / 2, radius);
        ImageView imageView = new ImageView(image);
        imageView.setClip(clip);
        SnapshotParameters parameters = new SnapshotParameters();
        parameters.setFill(Color.TRANSPARENT);
        return imageView.snapshot(parameters, null);
    }
}
