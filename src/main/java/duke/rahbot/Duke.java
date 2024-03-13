package duke.rahbot;

import java.io.IOException;

import duke.parser.Parser;
import duke.storage.Storage;
import duke.ui.Output;
import javafx.animation.PauseTransition;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * Main application class for Duke, a JavaFX application that facilitates interactive chat.
 * <p>
 * Duke is a GUI-based chatbot that allows users to interact with the application through
 * a graphical interface. It supports adding, deleting, and listing tasks, among other functionalities.
 * This class sets up the primary stage and scene for the application, initializing the chat interface.
 * </p>
 */
public class Duke extends Application {
    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;
    private Image user = new Image(this.getClass().getResourceAsStream("/images/me.jpg"));
    private Image duke = new Image(this.getClass().getResourceAsStream("/images/brothers.png"));
    private Stage stage;
    private AnchorPane mainLayout;
    private Storage storage = new Storage();

    public static void main(String[] args) {
        launch(args);
    }

    /**
     * Starts the main application stage, setting up the layout, scene, and event handlers.
     * This method initializes the graphical user interface, including input fields, buttons,
     * and display areas for messages.
     */
    @Override
    public void start(Stage primaryStage) {
        stage = primaryStage;
        setupComponents();
        formatWindow();
        initializeChatInterface();
        showWelcomeMessage();
        addInputHandlers();
    }

    private void setupComponents() {
        scrollPane = new ScrollPane();
        dialogContainer = new VBox();
        scrollPane.setContent(dialogContainer);

        userInput = new TextField();
        sendButton = new Button("Send");

        mainLayout = new AnchorPane();
        mainLayout.getChildren().addAll(scrollPane, userInput, sendButton);

        scene = new Scene(mainLayout);
    }

    private void formatWindow() {
        stage.setTitle("Duke");
        stage.setResizable(false);
        stage.setMinHeight(600.0);
        stage.setMinWidth(400.0);

        mainLayout.setPrefSize(400.0, 600.0);

        scrollPane.setPrefSize(385, 535);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);

        scrollPane.setVvalue(1.0);
        scrollPane.setFitToWidth(true);

        dialogContainer.setPrefHeight(Region.USE_COMPUTED_SIZE);

        userInput.setPrefWidth(325.0);

        sendButton.setPrefWidth(55.0);

        AnchorPane.setTopAnchor(scrollPane, 1.0);

        AnchorPane.setBottomAnchor(sendButton, 1.0);
        AnchorPane.setRightAnchor(sendButton, 1.0);

        AnchorPane.setLeftAnchor(userInput , 1.0);
        AnchorPane.setBottomAnchor(userInput, 1.0);
    }

    private void initializeChatInterface() {
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Adds event handlers for user input, including button clicks and text field submissions.
     * This method ensures that when the user interacts with the input controls, the appropriate
     * actions are taken to process and respond to the input.
     */
    private void addInputHandlers() {
        sendButton.setOnMouseClicked((event) -> {
            handleUserInput();
        });

        userInput.setOnAction((event) -> {
            handleUserInput();
        });

        dialogContainer.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));
    }

    /**
     * Displays a welcome message to the user upon application start.
     * This method is called during the initialization phase to greet the user.
     */
    private void showWelcomeMessage() {
        Output output = new Output(new Parser(new Storage()), new Storage());
        String welcomeMsg = output.welcome();
        HBox dialogHBox = createDialogHBox(welcomeMsg, duke);
        dialogContainer.getChildren().add(dialogHBox);
    }

    /**
     * Creates a horizontal box containing a message and an associated image.
     * This method is used to format the display of messages in the chat interface.
     * @param text The message text to be displayed.
     * @param image The image associated with the message.
     * @return An HBox containing the message and image.
     */
    private HBox createDialogHBox(String text, Image image) {
        Label textToAdd = new Label(text);
        textToAdd.setWrapText(true);
        ImageView imageView = new ImageView(image);
        imageView.setFitHeight(100);
        imageView.setFitWidth(100);
        imageView.setPreserveRatio(true);
        HBox hbox = new HBox(10);
        hbox.setAlignment(Pos.CENTER_LEFT);
        hbox.getChildren().addAll(imageView, textToAdd);
        return hbox;
    }

    /**
     * Processes user input, generates a response, and updates the chat interface.
     * This method handles the logic for receiving user input, generating a response
     * from Duke, and displaying both the user's message and Duke's reply in the chat.
     */
    private void handleUserInput() {
        Label userText = new Label(userInput.getText());
        Label dukeText = new Label(getResponse(userInput.getText()));
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(userText, new ImageView(user)),
                DialogBox.getDukeDialog(dukeText, new ImageView(duke))
        );
        userInput.clear();
    }

    /**
     * Generates a response to the user's input.
     * This method encapsulates the logic for processing user commands and generating
     * responses. It is a key part of the chatbot's functionality.
     * @param input The user's input as a String.
     * @return A String containing Duke's response to the input.
     */
    private String getResponse(String input) {
        Parser parser = new Parser(storage);
        Output output = new Output(parser, storage);
        String response = output.execute(input);
        try {
            storage.overWriteToFile(storage.load());
        } catch (IOException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }
        if (input.equalsIgnoreCase("bye")) {
            String byeMessage = output.execute(input);
            Platform.runLater(() -> {
                PauseTransition delay = new PauseTransition(Duration.seconds(1.5));
                delay.setOnFinished(e -> Platform.exit());
                delay.play();
            });
            return byeMessage;
        } else {
            return response;
        }
    }
}
