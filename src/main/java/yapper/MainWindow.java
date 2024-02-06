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

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        dialogContainer.setStyle("-fx-padding: 10;");
    }

    public void showWelcomeMessage() {
        dialogContainer.getChildren().add(DialogBox.getYapperDialog(yapper.showWelcomeMessage(), yapperImage));
    }

    public void setYapper(Yapper yap) {
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