package javafx;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import main.MainResponseCategorized;
import main.RyanGosling;
import utilities.ResponseHandler;

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

    private RyanGosling ryanGosling;


    private final Image userImage = new Image(this.getClass().getResourceAsStream("/images/AlPacinoUser.png"));
    private final Image ryanGoslingImage = new Image(this.getClass().getResourceAsStream("/images/RyanGoslingBot.png"));

    /**
     * Initializes the JavaFX controller. Binds the vertical value property of the scroll pane to
     * the height property of the dialog container. Adds the initial Duke dialog box with a greeting
     * to the dialog container.
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        dialogContainer.getChildren().add(DialogBox.getDukeDialog(ResponseHandler.greeting("Ryan Gosling"),
                                                                  ryanGoslingImage, false));
    }

    public void setRyanGosling(RyanGosling ryanGosling) {
        this.ryanGosling = ryanGosling;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        MainResponseCategorized ryanGoslingResponse = ryanGosling.getResponse(input);
        String responseToShow = ryanGoslingResponse.getResponseReturnedFromMain();
        boolean isResponseToShowAError = ryanGoslingResponse.isMessageAError();
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getDukeDialog(ryanGoslingResponse.getResponseReturnedFromMain(),
                                        ryanGoslingImage, isResponseToShowAError));
        userInput.clear();

        //From ChatGPT
        if (input.trim().equalsIgnoreCase("bye")) {
            Stage stage = (Stage) userInput.getScene().getWindow();
            stage.close(); // Close the application window
        }
    }
}
