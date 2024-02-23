package ui;

import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;


/**
 * Represents the layout for all UI elements to be displayed.
 */
public class MainWindow extends AnchorPane {
    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private UserInterface ui;
    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));

    /**
     * Instantiates MainWindow object belonging to ui.
     * Initialises its children.
     * @param ui UserInterface object where this object was created.
     */
    public MainWindow(UserInterface ui) {
        this.ui = ui;
        this.initialize();
    }

    /**
     * Reads the user input passes it to the UserInterface object for execution.
     * Updates the visual layout accordingly.
     *
     */
    private void handleUserInput() {
        String input = userInput.getText();
        dialogContainer.getChildren().add(
                DialogBox.getUserDialog(input, userImage)
        );
        userInput.clear();
        this.ui.runCommand(input);
    }

    /**
     * Adds a DialogBox containing message from the Chatbot.
     * @param text String to display.
     */
    public void displayText(String text) {
        dialogContainer.getChildren().add(
                DialogBox.getDukeDialog(text, dukeImage)
        );
    }

    private void initialize() {
        this.setPrefSize(400.0, 600.0);
        this.setStyle("-fx-background-color: grey;");

        setDialogContainer();
        setScrollPane();
        setSendButton();
        setTextField();

        this.getChildren().addAll(scrollPane, userInput, sendButton);
        setAnchors();
    }

    private void setDialogContainer() {
        this.dialogContainer = new VBox();
        dialogContainer.setPrefHeight(Region.USE_COMPUTED_SIZE);
    }

    private void setScrollPane() {
        this.scrollPane = new ScrollPane();
        scrollPane.setContent(dialogContainer);
        scrollPane.setPrefSize(400, 572);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
        scrollPane.setVvalue(1.0);
        scrollPane.setFitToWidth(true);
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    private void setSendButton() {
        this.sendButton = new Button("Send");
        sendButton.setPrefWidth(70.0);
        sendButton.setOnMouseClicked((event) -> {
            handleUserInput();
        });
    }

    private void setTextField() {
        this.userInput = new TextField();
        userInput.setPromptText("Input");
        userInput.setPrefWidth(325.0);
        userInput.setOnAction((event) -> {
            handleUserInput();
        });
    }

    private void setAnchors() {
        AnchorPane.setTopAnchor(scrollPane, 0.0);
        AnchorPane.setBottomAnchor(sendButton, 1.0);
        AnchorPane.setRightAnchor(sendButton, 1.0);
        AnchorPane.setLeftAnchor(userInput , 1.0);
        AnchorPane.setBottomAnchor(userInput, 1.0);
    }
}

