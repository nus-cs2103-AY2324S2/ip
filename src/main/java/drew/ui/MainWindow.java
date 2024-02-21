package drew.ui;

import java.io.IOException;

import drew.Drew;
import drew.ui.controls.DialogBox;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
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

    private Drew drew;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/monke.jpg"));
    private Image drewImage = new Image(this.getClass().getResourceAsStream("/images/SunTzu.jpg"));

    /**
     * Constructor for the main window instance.
     * @param drew Chatbot object.
     */
    public MainWindow(Drew drew) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            this.drew = drew;
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Sets the properties of the window and greets the user.
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        dialogContainer.getChildren().addAll(
                DialogBox.getDrewDialog(Ui.greetUser(), drewImage),
                DialogBox.getDrewDialog(drew.getUpcoming(), drewImage));
    }


    /**
     * Creates two dialog boxes, one echoing user input and the other containing Drew's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = drew.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getDrewDialog(response, drewImage)
        );
        userInput.clear();
    }
}

