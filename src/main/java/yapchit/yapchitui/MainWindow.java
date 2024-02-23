package yapchit.yapchitui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import yapchit.yapchitbackend.Ui;

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

    private Yapchit yapchit;

    private Stage stage;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image yaphitImage = new Image(this.getClass().getResourceAsStream("/images/DaYapchit.png"));


    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    public void setYapchit(Yapchit d) {
        yapchit = d;
        DialogBox yapchitIntro = DialogBox.getYapchitDialog(yapchit.getIntro(), yaphitImage);
        dialogContainer.getChildren().add(yapchitIntro);
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        checkAndCloseStage();

        String input = userInput.getText();
        String response = yapchit.getResponse(input);

        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getYapchitDialog(response, yaphitImage)
        );

        userInput.clear();
    }

    private void checkAndCloseStage() {
        if (!yapchit.getHasNext()) {
            stage.close();
        }
    }

    protected void setStage(Stage s) {
        this.stage = s;
    }

}
