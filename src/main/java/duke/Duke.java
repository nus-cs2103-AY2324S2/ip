package duke;

import java.io.IOException;
import java.util.Objects;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
//import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;



/**
 * The Duke class is the main entry point for the bot application itself
 * It uses an Operator to connect the user to the bot
 * The operator is responsible for handling user input and bot output
 * It's the entry point for the bot
 */
public class Duke extends Application {
    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;

    private final Image user = new Image(Objects.requireNonNull(this.getClass().getResourceAsStream("/images/user.jpg")));
    private final Image bot = new Image(Objects.requireNonNull(this.getClass().getResourceAsStream("/images/skynet.png")));

    /**
     * The main method to start the bot.
     * It initializes the bot, prints a greeting, and starts the operator to parse
     * user input
     */
    public static void main(String[] args) throws BotException, IOException {
        // Initial
        System.out.println(Ui.wrapWithSepLine(Bot.getGreeting()));

        // Starting parser to work with bot
        Parser operator = new Parser();
        operator.startOperator();

    }

    @Override
    public void start(Stage stage) {
        // Required components
        // The container for the content of the chat to scroll.
        scrollPane = new ScrollPane();
        dialogContainer = new VBox();
        scrollPane.setContent(dialogContainer);

        userInput = new TextField();
        Button sendButton = new Button("Send");

        AnchorPane mainLayout = new AnchorPane();
        mainLayout.getChildren().addAll(scrollPane, userInput, sendButton);

        Scene scene = new Scene(mainLayout);

        // Formatting the window to look as expected
        stage.setTitle("WannaBeSkynet");
        stage.setResizable(false);
        stage.setMinWidth(500.0);  // Increased from 400.0
        stage.setMinHeight(700.0); // Increased from 600.0

        // Set the background
//        mainLayout.setBackground(new Background(new BackgroundFill(Color.DARKGRAY, CornerRadii.EMPTY, Insets.EMPTY)));
//        scene.setFill(Color.DARKCYAN);

        mainLayout.setPrefSize(500.0, 700.0); // Increased from 400.0, 600.0

        scrollPane.setPrefSize(480, 635); // Increased from 385, 535
        userInput.setPrefWidth(418.0); // Increased from 325.0
        sendButton.setPrefWidth(65.0);  // Increased from 55.0
        sendButton.setPrefHeight(30.0);

        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);

        scrollPane.setVvalue(1.0);
        scrollPane.setFitToWidth(true);

        dialogContainer.setPrefHeight(Region.USE_COMPUTED_SIZE);

        AnchorPane.setTopAnchor(scrollPane, 1.0);

        AnchorPane.setBottomAnchor(sendButton, 1.0);
        AnchorPane.setRightAnchor(sendButton, 1.0);

        AnchorPane.setLeftAnchor(userInput, 1.0);
        AnchorPane.setBottomAnchor(userInput, 1.0);

        // Add padding between each DialogBox
//        dialogContainer.setSpacing(5);

        // Increase font size of the user input field
        userInput.setFont(new Font(12));

        /* functions */
        sendButton.setOnMouseClicked((event) -> {
            dialogContainer.getChildren().add(getDialogLabel(userInput.getText()));
            userInput.clear();
        });

        userInput.setOnAction((event) -> {
            dialogContainer.getChildren().add(getDialogLabel(userInput.getText()));
            userInput.clear();
        });

        // Scroll down to the end every time dialogContainer's height changes
        dialogContainer.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));

        // Functionality to handle user input.
        sendButton.setOnMouseClicked((event) -> {
            handleUserInput();
        });

        userInput.setOnAction((event) -> {
            handleUserInput();
        });

        stage.setScene(scene);
        stage.show();
    }

    /**
     * Iteration 1:
     * Creates a label with the specified text and adds it to the dialog container.
     *
     * @param text String containing text to add
     * @return a label with the specified text that has word wrap enabled.
     */
    private Label getDialogLabel(String text) {
        // You will need to import `javafx.scene.control.Label`.
        Label textToAdd = new Label(text);
//        textToAdd.setWrappingWidth(350.0);

        textToAdd.setWrapText(true);

        return textToAdd;
    }

    /**
     * Iteration 2:
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    private void handleUserInput() {
        String userText = String.valueOf(new Label(userInput.getText()));
        String botText = String.valueOf(new Label(getResponse(userInput.getText())));
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(userText, user),
                DialogBox.getDukeDialog(botText, bot));
        userInput.clear();
    }

    String getResponse(String input) {
        return "You said: " + input;
    }
}