package ui;

import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;


/**
 * Controller for MainWindow. Provides the layout for the other controls.
 */
public class MainWindow extends AnchorPane {
    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private UserInterface ui;
    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));

    public MainWindow(UserInterface ui) {
        this.ui = ui;
        this.scrollPane = new ScrollPane();
        this.dialogContainer = new VBox();
        scrollPane.setContent(dialogContainer);

        userInput = new TextField();
        sendButton = new Button("Send");

        this.getChildren().addAll(scrollPane, userInput, sendButton);
        this.initialize();
    }

    private void handleUserInput() {
        String input = userInput.getText();
        dialogContainer.getChildren().add(
                DialogBox.getUserDialog(input, userImage)
        );
        userInput.clear();
        this.ui.runCommand(input);
    }

    public void displayText(String text) {
        dialogContainer.getChildren().add(
                DialogBox.getDukeDialog(text, dukeImage)
        );
    }

    public void initialize() {
        dialogContainer.setPrefHeight(Region.USE_COMPUTED_SIZE);

        scrollPane.setPrefSize(385, 535);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
        scrollPane.setVvalue(1.0);
        scrollPane.setFitToWidth(true);
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());

        userInput.setPrefWidth(325.0);

        sendButton.setPrefWidth(55.0);


        this.setPrefSize(400.0, 600.0);

        sendButton.setOnMouseClicked((event) -> {
            handleUserInput();
        });

        userInput.setOnAction((event) -> {
            handleUserInput();
        });

        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());

        AnchorPane.setTopAnchor(scrollPane, 1.0);

        AnchorPane.setBottomAnchor(sendButton, 1.0);
        AnchorPane.setRightAnchor(sendButton, 1.0);

        AnchorPane.setLeftAnchor(userInput , 1.0);
        AnchorPane.setBottomAnchor(userInput, 1.0);
    }
}

