package campus.javafxGUI;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import javafx.application.Platform;
import campus.Campus;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import java.util.Objects;

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
    private Campus campus;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/user.jpg"));
    private Image campusImage = new Image(this.getClass().getResourceAsStream("/images/CampusBotlogo.jpg"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    public void setCampus(Campus c) {
        campus = c;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Campus's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    public void handleUserInput() {

        String input = userInput.getText();

        String response = campus.parseMessage(input);

        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getCampusDialog(response, campusImage)
        );
        userInput.clear();

        if (Objects.equals(input, "bye")) {
            terminate();
        }
    }

    public void printGreetMessage() {
        String msg = campus.printGreeting();
        dialogContainer.getChildren().add(DialogBox.getCampusDialog(msg, campusImage));
    }

    public void terminate() {
        // Schedule program exit after 2 seconds
        ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();
        scheduler.schedule(Platform::exit, 2, TimeUnit.SECONDS);
        scheduler.shutdown();
    }
}
