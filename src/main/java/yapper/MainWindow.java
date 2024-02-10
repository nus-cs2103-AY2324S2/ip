package yapper;

import java.util.Timer;
import java.util.TimerTask;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

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

    private Yapper yapper;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/fan.png"));
    private Image yapperImage = new Image(this.getClass().getResourceAsStream("/images/yapper.png"));

    /**
     * Initializes the MainWindow by ensuring that essential UI components are not null.
     * Binds the vertical value property of the scroll pane to the height property of the dialog container.
     * Sets padding for the dialog container.
     * <p>
     * This method should be called after the FXML file is loaded and the UI components are initialized.
     * </p>
     * <p>
     * This method will throw an AssertionError if any of the essential UI components are found to be null.
     * </p>
     */
    @FXML
    public void initialize() {
        assert scrollPane != null : "ScrollPane should not be null";
        assert dialogContainer != null : "DialogContainer should not be null";
        assert userInput != null : "UserInput should not be null";
        assert sendButton != null : "SendButton should not be null";

        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        dialogContainer.setStyle("-fx-padding: 10;");
    }
    /**
     * Displays a welcome message in the dialog container.
     * <p>
     * This method requires the dialog container and Yapper instance to be initialized.
     * </p>
     * <p>
     * This method will throw an AssertionError if the dialog container or Yapper instance is null.
     * </p>
     */
    public void showWelcomeMessage() {
        assert dialogContainer != null : "DialogContainer should not be null";
        assert yapper != null : "Yapper instance should not be null";
        dialogContainer.getChildren().add(DialogBox.getYapperDialog(yapper.showWelcomeMessage(), yapperImage));
    }

    public void setYapper(Yapper yap) {
        assert yap != null : "Yapper instance should not be null";
        yapper = yap;
        yapper.setUi(new Ui());
    }

    private void exit() {
        int delayInMillis = 1000;
        Timer timer = new Timer();

        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                Platform.exit();
                System.exit(0);
            }
        }, delayInMillis);
    }


    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() throws YapperException {
        assert userInput != null : "UserInput should not be null";
        assert yapper != null : "Yapper instance should not be null";
        String input = userInput.getText();

        if (input.equalsIgnoreCase("bye")) {
            dialogContainer.getChildren().addAll(
                    DialogBox.getUserDialog(input, userImage),
                    DialogBox.getYapperDialog("Bye. Hope to yap with you again soon!", yapperImage)
            );
            this.exit();
        } else {
            yapper.setUi(new Ui());
            String response = yapper.processUserInput(input);

            Platform.runLater(() -> {
                dialogContainer.getChildren().addAll(
                        DialogBox.getUserDialog(input, userImage),
                        DialogBox.getYapperDialog(response, yapperImage)
                );
                userInput.clear();
            });
        }
    }

    public void updateDialogContainerWithWelcomeMessage(String welcomeMessage) {
        dialogContainer.getChildren().add(DialogBox.getYapperDialog(welcomeMessage, yapperImage));
    }
}