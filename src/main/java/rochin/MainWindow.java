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

    /**
     * Initializes the MainWindow.
     * Sets up binding for scrollPane and sets background image.
     * Displays a welcome message from RochinBot.
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        BackgroundSize size = new BackgroundSize(3600, 3600, true, true, true, true);
        BackgroundImage image = new BackgroundImage(
                background,
                BackgroundRepeat.REPEAT,
                BackgroundRepeat.REPEAT,
                BackgroundPosition.CENTER,
                size);
        dialogContainer.setBackground(new Background(image));
        dialogContainer.getChildren().add(DialogBox.getRochinDialog(RochinBot.showWelcomeMsg(), rochinImage));
    }

    /**
     * Sets the RochinBot instance for this window.
     * @param r The RochinBot instance to be set.
     */
    public void setRochin(RochinBot r) {
        rochin = r;
    }

    /**
     * Handles user input.
     * Echoes user input and displays RochinBot's response in dialog boxes.
     * Clears the user input after processing.
     * Exits the application if the user inputs "bye".
     * @throws RochinException If an error occurs while processing the user input.
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

