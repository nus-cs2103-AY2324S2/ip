package duck;

import java.io.FileNotFoundException;
import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


/**
 * The main class for the Duck chatbot application.
 * Duke extends the JavaFX Application class and provides the graphical user interface for the chatbot.
 */
public class Duck extends Application {
    private static final String FILE_PATH = "./data/duke.txt";
    private Image user = new Image(this.getClass().getResourceAsStream("/images/user.png"));
    private Image duke = new Image(this.getClass().getResourceAsStream("/images/bot.png"));
    private TaskList tasks;
    private Ui ui;
    private Storage storage;

    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;
    private Scene scene;

    public Duck() {

    }

    public static void main(String[] args) {
        launch(args);
    }

    /**
     * Initializes and sets up the GUI for the Duck chatbot application.
     * Overrides the start method of the Application class.
     *
     * @param primaryStage The primary stage for the application.
     */
    @Override
    public void start(Stage primaryStage) {
        ui = new Ui();
        storage = new Storage(FILE_PATH);

        try {
            storage.ensureDataFileExists();
            tasks = storage.loadTasksFromFile();
        } catch (DukeDataCorruptedException e) {
            ui.showDukeDataCorruptionMessage(e);
        } catch (FileNotFoundException e) {
            System.out.println("Data file not found. Creating a new one.");
            ui.showFileNotFoundExceptionMessage();
        } catch (IOException e) {
            ui.showIoExceptionMessage();
        } catch (NumberFormatException e) {
            ui.showNumberFormatExceptionMessage();
        }

        scrollPane = new ScrollPane();
        dialogContainer = new VBox();
        scrollPane.setContent(dialogContainer);

        userInput = new TextField();
        sendButton = new Button("Send");

        AnchorPane mainLayout = new AnchorPane();
        mainLayout.getChildren().addAll(scrollPane, userInput, sendButton);

        scene = new Scene(mainLayout);

        setupPrimaryStage(primaryStage);

        mainLayout.setPrefSize(400.0, 600.0);

        setupScrollPane(scrollPane);

        dialogContainer.setPrefHeight(Region.USE_COMPUTED_SIZE);

        setupAnchorPane(mainLayout);

        setupWelcomeMessage();

        handleSendButtonClick();

    }

    /**
     * Sets up the primary stage with specified configurations.
     *
     * @param primaryStage The primary stage of the JavaFX application.
     */
    private void setupPrimaryStage(Stage primaryStage) {
        primaryStage.setScene(scene);
        primaryStage.show();

        primaryStage.setTitle("Duck");
        primaryStage.setResizable(false);
        primaryStage.setMinHeight(600.0);
        primaryStage.setMinWidth(400.0);
        userInput.setPrefWidth(325.0);

        sendButton.setPrefWidth(55.0);
    }

    /**
     * Sets up the scroll pane with specified configurations.
     *
     * @param pane The scroll pane to be configured.
     */
    private void setupScrollPane(ScrollPane pane) {
        scrollPane.setPrefSize(385, 535);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);

        scrollPane.setVvalue(1.0);
        scrollPane.setFitToWidth(true);
    }

    /**
     * Sets up the anchor pane with specified configurations.
     *
     * @param pane The anchor pane to be configured.
     */
    private void setupAnchorPane(AnchorPane pane) {
        userInput.setPrefWidth(325.0);

        sendButton.setPrefWidth(55.0);
        AnchorPane.setTopAnchor(scrollPane, 1.0);

        AnchorPane.setBottomAnchor(sendButton, 1.0);
        AnchorPane.setRightAnchor(sendButton, 1.0);

        AnchorPane.setLeftAnchor(userInput, 1.0);
        AnchorPane.setBottomAnchor(userInput, 1.0);
    }

    /**
     * Displays a welcome message in the dialog container.
     */
    private void setupWelcomeMessage() {
        dialogContainer.getChildren().addAll(
                getDialogLabel("Welcome to Duck Chatbot! How can I assist you today? "
                        + "Use help to see the list of commands available")
        );
    }

    /**
     * Sets up event handling for the send button click.
     */
    private void handleSendButtonClick() {
        sendButton.setOnMouseClicked((event) -> {
            dialogContainer.getChildren().add(getDialogLabel(userInput.getText()));
            userInput.clear();
        });

        userInput.setOnAction((event) -> {
            dialogContainer.getChildren().add(getDialogLabel(userInput.getText()));
            userInput.clear();
        });

        dialogContainer.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));

        sendButton.setOnMouseClicked((event) -> {
            handleUserInput();
        });

        userInput.setOnAction((event) -> {
            handleUserInput();
        });
    }

    /**
     * Creates and returns a JavaFX Label with the specified text for use in a dialog.
     *
     * @param text The text to be displayed on the label.
     * @return The JavaFX Label configured with the provided text and wrap text enabled.
     */
    private Label getDialogLabel(String text) {
        // You will need to import `javafx.scene.control.Label`.
        Label textToAdd = new Label(text);
        textToAdd.setWrapText(true);

        return textToAdd;
    }


    /**
     * Handles user input submitted through the chat interface.
     * Displays the user input and Duck's response in the conversation area.
     * Executes commands based on user input and displays the result.
     * Clears the input field after processing.
     */
    @FXML
    private void handleUserInput() {
        String userInputText = userInput.getText();

        // Handle user input for the chat interface
        Label userTextLabel = new Label(userInputText);
        Label dukeTextLabel = new Label(getResponse(userInputText));
        ImageView userImageView = new ImageView(user);
        ImageView dukeImageView = new ImageView(duke);

        // Display user input and Duke's response in the conversation area
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(userTextLabel, userImageView),
                DialogBox.getDukeDialog(dukeTextLabel, dukeImageView)
        );

        // Handle user input for the command execution
        if (Parser.isExitCommand(userInputText)) {
            ui.showGoodbyeMessage();
            dialogContainer.getChildren().add(new Label("Goodbye! Hope to see you again :)"));
            // Save tasks and exit
            try {
                storage.saveTasksToFile(tasks);
            } catch (IOException e) {
                ui.showIoExceptionMessage();
            }
            System.exit(0);
        }

        if (userInputText.trim().isEmpty()) {
            dialogContainer.getChildren().add(new Label("Please enter an action and a task"));
            return;
        }

        try {
            Command command = Parser.parseCommand(userInputText);
            String commandResult = command.execute(tasks, ui, storage);

            dialogContainer.getChildren().add(new Label(commandResult));

            storage.saveTasksToFile(tasks);

        } catch (DukeException | IOException e) {
            dialogContainer.getChildren().add(new Label(e.getMessage()));
        }

        // Clear input field after processing
        userInput.clear();
    }

    /**
     * Gets a response from Duck chatbot based on user input.
     *
     * @param input The user input.
     * @return The response from Duck chatbot.
     */
    private String getResponse(String input) {
        return "Duck heard: " + input;
    }
}

/**
 * Exception class representing a corrupted data file in Duke.
 */
class DukeDataCorruptedException extends Exception {

    /**
     * Constructs a DukeDataCorruptedException with the specified error message.
     *
     * @param message The error message describing the data corruption issue.
     */
    public DukeDataCorruptedException(String message) {
        super(message);
    }
}

/**
 * Exception class representing a generic exception in Duke.
 */
class DukeException extends Exception {

    /**
     * Constructs a DukeException with the specified error message.
     *
     * @param message The error message describing the exception.
     */
    public DukeException(String message) {
        super(message);
    }
}


