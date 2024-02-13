package yoda.gui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import yoda.Yoda;

public class MainWindow extends AnchorPane {
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private Yoda yoda;

    // Assuming images are stored under /resources/images/ within your project structure
    private Image yodaImage = new Image(getClass().getResourceAsStream("/images/DaYoda.png"));
    private Image userImage = new Image(getClass().getResourceAsStream("/images/DaDarthVader.png"));

    @FXML
    public void initialize() {
        configureScrollPane();
        setupInputHandlers();
    }

    private void configureScrollPane() {
        scrollPane.setFitToWidth(true);
        // This ensures the scrollPane always scrolls to the bottom when new content is added
        dialogContainer.heightProperty().addListener((observable) -> scrollPane.setVvalue(1D));
    }

    private void setupInputHandlers() {
        // Bind the handleUserInput method to the send button's click event
        sendButton.setOnMouseClicked(event -> handleUserInput());
        // Bind the handleUserInput method to the text field's action event (e.g., pressing Enter)
        userInput.setOnAction(event -> handleUserInput());
    }

    public void setYoda(Yoda y) {
        this.yoda = y;
        displayGreeting(); // Display the greeting message as soon as Yoda is set
    }

    public void displayGreeting() {
        String greeting = yoda.getGreeting();
        DialogBox greetingDialog = DialogBox.getYodaDialog(greeting, yodaImage);
        dialogContainer.getChildren().add(greetingDialog);
    }

    @FXML
    private void handleUserInput() {
        String input = userInput.getText().trim();
        if (!input.isEmpty()) {
            String response = yoda.getResponse(input);
            dialogContainer.getChildren().addAll(
                    DialogBox.getUserDialog(input, userImage),
                    DialogBox.getYodaDialog(response, yodaImage)
            );
            userInput.clear();
        }
    }
}
