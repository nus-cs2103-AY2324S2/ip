package hammy.gui;

import hammy.task.*;
import hammy.storage.Storage;
import hammy.response.Ui;
import hammy.parser.Parser;

import java.io.*;
import java.util.ArrayList;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.layout.Region;
import javafx.scene.control.Label;
import javafx.scene.image.Image;

/**
 * The Duke class serves as the main class for the chatbot.
 * It manages the initialization of components and runs the main loop for user interaction.
 */
public class Hammy extends Application {
    /**
     * The storage handler for managing tasks.
     */
    private Storage storage;

    /**
     * The task list containing tasks managed by Duke.
     */
    private TaskList taskList;

    /**
     * The user interface for displaying messages to the user.
     */
    private Ui ui;

    /**
     * The parser for interpreting user commands and executing corresponding actions.
     */
    private Parser parser;

    /**
     * The Scroll Pane for scrolling purpose while using the app
     */
    private ScrollPane scrollPane;

    /**
     * A dialog container to contains the text from both user and corresponding responses
     */
    private VBox dialogContainer;

    /**
     * A text field for user to input their commands
     */
    private TextField userInput;

    /**
     * A button to send user's command
     */
    private Button sendButton;
    private Scene scene;

    /**
     * The profile image of user
     */
    private Image user = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));

    /**
     * The profile image of the bot
     */
    private Image bot = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));

    /**
     * Constructs a Duke object with the specified file path and bot name.
     *
     * @param filePath the file path where tasks are stored.
     * @param botName  the name of the Duke bot.
     */
    public Hammy(String filePath, String botName) {
        this.storage = new Storage(filePath);
        this.ui = new Ui(botName);
        ArrayList<Task> loadedTasks = storage.loadTasksFromFile();
        this.taskList = new TaskList(loadedTasks, this.ui);
        this.parser = new Parser(this.ui, this.storage, this.taskList);
    }

    /**
     * Constructs a Duke object with the specified file path and bot name.
     */
    public Hammy() {
        this.storage = new Storage("./data/tasklist.txt");
        this.ui = new Ui("Hammy");
        ArrayList<Task> loadedTasks = storage.loadTasksFromFile();
        this.taskList = new TaskList(loadedTasks, this.ui);
        this.parser = new Parser(this.ui, this.storage, this.taskList);
    }
    
    @Override
    public void start(Stage stage) {
        //Step 1. Setting up required components
        scrollPane = new ScrollPane();
        dialogContainer = new VBox();
        scrollPane.setContent(dialogContainer);
        userInput = new TextField();
        sendButton = new Button("Send");
        AnchorPane mainLayout = new AnchorPane();
        mainLayout.getChildren().addAll(scrollPane, userInput, sendButton);
        scene = new Scene(mainLayout);
        stage.setScene(scene);
        stage.show();

        //Step 2. Formatting the window to look as expected
        stage.setTitle("Hammy");
        stage.setResizable(false);
        stage.setMinHeight(600.0);
        stage.setMinWidth(400.0);
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

        //Step 3. Add functionality to handle user input.
        sendButton.setOnMouseClicked((event) -> {
            dialogContainer.getChildren().add(getDialogLabel(userInput.getText()));
            userInput.clear();
        });

        userInput.setOnAction((event) -> {
            dialogContainer.getChildren().add(getDialogLabel(userInput.getText()));
            userInput.clear();
        });

        //Scroll down to the end every time dialogContainer's height changes.
        dialogContainer.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));

        //Part 3. Add functionality to handle user input.
        sendButton.setOnMouseClicked((event) -> {
            try {
                handleUserInput();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

        userInput.setOnAction((event) -> {
            try {
                handleUserInput();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
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
        Label textToAdd = new Label(text);
        textToAdd.setWrapText(true);
        return textToAdd;
    }

    /**
     * Iteration 2:
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    private void handleUserInput() throws IOException {
        String userText = userInput.getText();
        String dukeText = getResponse(userText);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(userText, user),
                DialogBox.getDukeDialog(dukeText, bot)
        );
        userInput.clear();
    }

    protected String getResponse(String input) throws IOException {
        try {
            return parser.execute(input);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}