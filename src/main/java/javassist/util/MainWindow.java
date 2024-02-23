package javassist.util;

import java.util.Arrays;

import javafx.fxml.FXML;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javassist.JavAssist;


/**
 * Represents the main window of the chatbot.
 */
public class MainWindow extends AnchorPane {
    private static final String SPLIT_COMMAND = "\\s*\\|\\|\\s*";

    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;

    private JavAssist javAssist;

    private Image javAssistImage = new Image(this.getClass().getResourceAsStream("/images/user.jpg"));
    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/javassist.jpg"));

    /**
     * Initializes the scene.
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        userInput.requestFocus();
    }

    public void setJavAssist(JavAssist d) {
        javAssist = d;
    }

    @FXML
    private void handleUserInput() {
        try {
            String input = userInput.getText();
            String[] commands = input.split(SPLIT_COMMAND);
            Arrays.stream(commands)
                    .forEach(command -> {
                        String response = javAssist.getResponse(command);
                        dialogContainer.getChildren().addAll(
                                DialogBox.getUserDialog(command, userImage),
                                DialogBox.getDukeDialog(response, javAssistImage)
                        );
                    });
            userInput.clear();
            if (javAssist.isExit()) {
                Stage stage = (Stage) dialogContainer.getScene().getWindow();
                stage.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Displays welcome message.
     */
    public void welcome() {
        String response = javAssist.getLogo();
        dialogContainer.getChildren().addAll(
                DialogBox.getDukeDialog(response, javAssistImage)
        );
        userInput.clear();
    }
}
