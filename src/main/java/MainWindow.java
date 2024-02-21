import exception.TobiasException;
import javafx.animation.PauseTransition;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;
import ui.Ui;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

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
    private MenuItem about;

    private Tobias tobias;

    private final Image userImage = new Image(this.getClass().getResourceAsStream("/images/michaelUser.jpeg"));
    private final Image tobiasImage = new Image(this.getClass().getResourceAsStream("/images/tobiasBot.jpeg"));

    public void setTobias(Tobias tobias) {
        this.tobias = tobias;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Tobias's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        dialogContainer.heightProperty().addListener((observable -> scrollPane.setVvalue(1.0)));
        String input = userInput.getText();
        String response = "";
        try {
            response = tobias.getResponse(input);
        } catch (TobiasException e) {
            response = e.printMessage();
        }

        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getTobiasDialog(response, tobiasImage)
        );

        userInput.clear();

        if (input.trim().equals("bye")) {
            PauseTransition pause = new PauseTransition(Duration.seconds(2.5));
            pause.setOnFinished(event -> Platform.exit());
            pause.play();
        }
    }

    @FXML
    private void handleAbout() {
        Alert aboutPopup = new Alert(Alert.AlertType.INFORMATION);
        aboutPopup.setTitle("About");
        aboutPopup.setHeaderText("Tobias Bot");
        aboutPopup.setContentText("A powerful text-based chat-bot to keep track of your life !!");

        aboutPopup.showAndWait();
    }

    @FXML
    private void handleHelp() {
        try {
            Desktop.getDesktop().browse(new URI("https://jawad280.github.io/ip/"));
        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
        }
    }

    public void initialize() {
        showWelcomeMessage();
    }

    private void showWelcomeMessage() {
        String result = Ui.printDivider()
                + System.lineSeparator()
                + "   Hello there ! I'm Tobias Funke !!"
                + System.lineSeparator()
                + "   What can I do for you today ?"
                + System.lineSeparator()
                + Ui.printDivider();

        dialogContainer.getChildren().addAll(
                DialogBox.getTobiasDialog(result, tobiasImage)
        );
    }
}
