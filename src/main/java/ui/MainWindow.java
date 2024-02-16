package ui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import primary.William;

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

    private William william;

    // This one is given by Prof, which does not work for me 
    // private final Image USER_IMAGE = new Image(this.getClass().getResourceAsStream("/images/luffy.jpeg")); 
    // private final Image WILLIAM_IMAGE = new Image(this.getClass().getResourceAsStream("/images/chopper.jpeg"));"
    
    // This one is an absolute path, which works for me
    private final Image USER_IMAGE =
            new Image("file:/Users/khoonsun/ip/src/main/resources/images/luffy.jpeg");
    // This one is an absolute path, which works for me
    private final Image WILLIAM_IMAGE =
            new Image("file:/Users/khoonsun/ip/src/main/resources/images/chopper.jpeg");


    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    public void setWilliam(William william) {
        this.william = william;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and
     * then appends them to the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = william.getChatBotResponse(input);
        dialogContainer.getChildren().addAll(DialogBox.getUserDialog(input, USER_IMAGE),
                DialogBox.getWilliamDialog(response, WILLIAM_IMAGE));
        userInput.clear();
    }
}
