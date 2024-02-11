package chatbot.controllers;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import chatbot.Plana;
import chatbot.Response;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

/**
 * Controller for MainWindow. Provides the layout for the other controls.
 */
public class MainWindow extends AnchorPane {
    private final Image userImage = new Image(this.getClass().getResourceAsStream("/media/sensei.png"));
    private final Image planaImage = new Image(this.getClass().getResourceAsStream("/media/plana.png"));
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;
    private Plana plana;

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        // Display welcome greeting
        List<String> greeting = Response.displayGreeting().getMessages();
        dialogContainer.getChildren().addAll(DialogBox.getBotDialog(greeting.get(0), planaImage));
        dialogContainer.getChildren().addAll(DialogBox.getBotDialog(greeting.get(1), planaImage));
        // Set expand for button
        GridPane.setHgrow(sendButton, Priority.ALWAYS);
    }

    public void setPlana(Plana p) {
        this.plana = p;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Plana's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() throws IOException {
        String input = userInput.getText();
        Response response = this.plana.getResponse(input);

        ObservableList<Node> ol = dialogContainer.getChildren();
        ol.add(DialogBox.getUserDialog(input, userImage));
        userInput.clear();
        if (response.isError()) {
            ol.add(ErrorBox.getErrorDialog(response.getErrorMessage()));
            return;
        }
        for (String s : response.getMessages()) {
            ol.add(DialogBox.getBotDialog(s, planaImage));
        }

        if (response.isBreaking()) {
            this.plana.persistData();
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Platform.exit();
        }
    }
}
