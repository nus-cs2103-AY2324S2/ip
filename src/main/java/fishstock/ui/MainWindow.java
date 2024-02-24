package fishstock.ui;

import fishstock.Command;
import fishstock.FishStock;
import javafx.fxml.FXML;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

/**
 * Controller for MainWindow. Provides the layout for the other controls.
 */
public class MainWindow extends AnchorPane {
    private static final String WELCOME_TEXT = "Hello, I'm FishStock.\nI might help if I feel like it.\n"
            + "\nHere's a list of available commands:\n\n" + Command.toList();
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;

    private FishStock fishstock;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/User.png"));
    private Image fishStockImage = new Image(this.getClass().getResourceAsStream("/images/FishStock.png"));

    @FXML
    private void initialize() {
        dialogContainer.getChildren().add(DialogBox.getFishStockDialog(WELCOME_TEXT, fishStockImage));
    }

    protected void setFishStock(FishStock f) {
        fishstock = f;
    }

    private void moveScrollbarToBottom() {
        // Manually set vertical scrollbar value. Fixes scrolling to bottom on window width size change issue.
        scrollPane.applyCss();
        scrollPane.layout();
        scrollPane.setVvalue(1.0);
    }
    /**
     * Creates two dialog boxes, one echoing user input and the other containing FishStock's reply
     * and then appends them to the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = fishstock.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getFishStockDialog(response, fishStockImage)
        );
        userInput.clear();
        moveScrollbarToBottom();
    }
}
