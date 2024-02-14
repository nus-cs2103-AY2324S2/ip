package duke;//package duke;

import duke.task.*;
import duke.storage.*;
import duke.ui.*;
import duke.parser.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
import javafx.application.Application;
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
import javafx.scene.image.ImageView;


/**
 * The Duke class serves as the main class for the chatbot.
 * It manages the initialization of components and runs the main loop for user interaction.
 */
public class Duke extends Application {

    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;
    private Image user = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image duke = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));

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
     * Constructs a Duke object with the specified file path and bot name.
     *
     * @param filePath the file path where tasks are stored
     * @param botName  the name of the Duke bot
     */
    public Duke(String filePath, String botName) {
        this.storage = new Storage(filePath);
        this.ui = new Ui(botName);
        ArrayList<Task> loadedTasks = storage.loadTasksFromFile();
        this.taskList = new TaskList(loadedTasks, this.ui);
        this.parser = new Parser(this.ui, this.storage, this.taskList);
    }

    public Duke() {
        this.storage = new Storage("./data/duke.txt");
        this.ui = new Ui("Hammy");
        ArrayList<Task> loadedTasks = storage.loadTasksFromFile();
        this.taskList = new TaskList(loadedTasks, this.ui);
        this.parser = new Parser(this.ui, this.storage, this.taskList);
    }

    /**
     * Runs the program.
     *
     * @throws IOException if an I/O error occurs during application execution
     */
    public void run() throws IOException {
        ui.sendWelcome();
        boolean isBye = false;
        while (true) {
            Scanner scanner = new Scanner(System.in);
            String userInput = scanner.nextLine();
            parser.execute(userInput);
            if (userInput.equals("bye")) {
                break;
            }
        }
    }

    /**
     * The main method for starting the Duke chatbot application.
     *
     * @param args the command-line arguments (not used)
     * @throws IOException if an I/O error occurs during application execution
     */
    public static void main(String[] args) throws IOException {
        new Duke("./data/duke.txt", "Hammy").run();
    }

    @Override
    public void start(Stage stage) {
        //Step 1. Setting up required components

        //The container for the content of the chat to scroll.
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
     * Iteration 2:
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    private void handleUserInput() throws IOException {
        String userText = userInput.getText();
        String dukeText = getResponse(userText);

        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(userText, user),
                DialogBox.getDukeDialog(dukeText, duke)
        );
        userInput.clear();
    }

    protected String getResponse(String input) throws IOException {
        try {
            String response = parser.execute(input);
            return response;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * While the GUI looks similar to the mockup, there are still parts that need to be refined. Try your hand at some of these tasks:
     *
     * Add padding between each DialogBox
     * Add padding between each ImageView and its Label
     * Clip the ImageView into a circle
     * Add background color to each dialog box
     * After attempting the changes, reflect critically on the following:
     *
     * What was the development workflow like?
     * Is the code base well-organized?
     */

}
