package rochin;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.Background;
import javafx.scene.layout.VBox;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

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

    private RochinBot rochin;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/heart.png"));
    private Image rochinImage = new Image(this.getClass().getResourceAsStream("/images/ga.png"));
    private Image background = new Image("/images/Background.jpg");

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        BackgroundSize size = new BackgroundSize(100, 100, true, true, false, true);
        BackgroundImage image = new BackgroundImage(
                background,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.CENTER,
                size);
        dialogContainer.setBackground(new Background(image));
        dialogContainer.getChildren().add(DialogBox.getRochinDialog(RochinBot.showWelcomeMsg(), rochinImage));

    }


    public void setRochin(RochinBot r) {
        rochin = r;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() throws RochinException {
        String input = userInput.getText();
        String response = rochin.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getRochinDialog(response, rochinImage)
        );
        userInput.clear();

        if (input.equals("bye")) {
            ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();
            scheduler.schedule(() -> Platform.exit(), 1, TimeUnit.SECONDS);
            scheduler.schedule(() -> System.exit(0), 1, TimeUnit.SECONDS);
        }
    }
}

