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

    /**
     * The main entry point for the application.
     * Launches the JavaFX application.
     *
     * @param args The command line arguments.
     */
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

        primaryStage.setScene(scene);
        primaryStage.show();

        primaryStage.setTitle("Duck");
        primaryStage.setResizable(false);
        primaryStage.setMinHeight(600.0);
        primaryStage.setMinWidth(400.0);

        mainLayout.setPrefSize(400.0, 600.0);

        scrollPane.setPrefSize(385, 535);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);

        scrollPane.setVvalue(1.0);
        scrollPane.setFitToWidth(true);

        //You will need to import `javafx.scene.layout.Region` for this.
        dialogContainer.setPrefHeight(Region.USE_COMPUTED_SIZE);

        userInput.setPrefWidth(325.0);

        sendButton.setPrefWidth(55.0);

        AnchorPane.setTopAnchor(scrollPane, 1.0);

        AnchorPane.setBottomAnchor(sendButton, 1.0);
        AnchorPane.setRightAnchor(sendButton, 1.0);

        AnchorPane.setLeftAnchor(userInput, 1.0);
        AnchorPane.setBottomAnchor(userInput, 1.0);

        dialogContainer.getChildren().addAll(
                getDialogLabel("Welcome to Duck Chatbot! How can I assist you today? " +
                        "Use help to see the list of commands available")
        );

        sendButton.setOnMouseClicked((event) -> {
            dialogContainer.getChildren().add(getDialogLabel(userInput.getText()));
            userInput.clear();
        });

        userInput.setOnAction((event) -> {
            dialogContainer.getChildren().add(getDialogLabel(userInput.getText()));
            userInput.clear();
        });

        dialogContainer.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));

        //Part 3. Add functionality to handle user input.
        sendButton.setOnMouseClicked((event) -> {
            handleUserInput();
        });

        userInput.setOnAction((event) -> {
            handleUserInput();
        });

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

            // Display the parsed command and its output in the conversation area
            //dialogContainer.getChildren().add(new Label("Command: " + userInputText));
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


