package fredricksen.gui;

import fredricksen.Fredricksen;
import fredricksen.ui.Ui;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

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

    private Fredricksen fredricksen;
    private Stage stage;

    private Ui ui = new Ui();

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/Russell.jpeg"));
    private Image fredricksenImage = new Image(this.getClass().getResourceAsStream("/images/Fredricksen.jpg"));

    /**
     * Initialise the height property of the dialog container,
     * set the background of the dialog container and greet the user
     * when they open the application.
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        dialogContainer.setBackground(getBg());
        dialogContainer.getChildren().add(greetUser());
    }

    public Background getBg() {
        BackgroundFill bgFill = new BackgroundFill(Color.LAVENDER, CornerRadii.EMPTY, Insets.EMPTY);
        return new Background(bgFill);
    }

    public void setFredricksen(Fredricksen d, Stage stage) {
        fredricksen = d;
        this.stage = stage;
    }

    /**
     * Greet the user with a welcome message when the user opens the application.
     *
     * @return A DialogBox with the welcome message inside.
     */
    @FXML
    public DialogBox greetUser() {
        DialogBox greetDialog = DialogBox.getDukeDialog(this.ui.showWelcome(), fredricksenImage);
        greetDialog.configBotBox();
        return greetDialog;
    }

    /**
     * Adds a short timeout after the function is called.
     * After this timeout ends, the window application is closed.
     */
    @FXML
    public void closeWindow() {
        new Thread(() -> {
            try {
                Thread.sleep(1300);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }

            Platform.runLater(() -> {
                this.stage.close();
            });
        }).start();
    }

    public String checkByeString() {
        return "Bye! Sad to see you leave! Please come again!";
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing
     * Fredricksen's reply and then appends them to the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = fredricksen.getResponse(input);
        DialogBox userBox = DialogBox.getUserDialog(input, userImage);
        DialogBox botBox = DialogBox.getDukeDialog(response, fredricksenImage);
        dialogContainer.getChildren().addAll(
                userBox,
                botBox
        );
        userInput.clear();
        userBox.configUserBox();
        botBox.configBotBox();

        boolean isExit = response.equals(checkByeString());
        if (isExit) {
            closeWindow();
        }
    }
}
