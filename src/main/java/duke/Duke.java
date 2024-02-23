package duke;

import java.util.List;
import java.util.Scanner;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;




/**
 * Aether is a task-management chatbot.
 * It allows users to add, delete, mark tasks as done, and view the task list.
 * It also supports todo, deadline and event tasks
 * Duke also supports saving and loading tasks from a file.
 */

public class Duke extends Application {


    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;
    private Ui ui;
    private Storage storage;
    private TaskList tasks;

    private Image user = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image duke = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));

    /**
     * Constructs a Duke object with the specified file path for task storage.
     *
     * @param filePath The file path for task storage.
     */
    public Duke(String filePath) {
        this.ui = new Ui();
        this.storage = new Storage(filePath);
        try {
            List<duke.Task> loadedTasks = storage.load();
            this.tasks = new TaskList(loadedTasks);
        } catch (DukeException e) {
            ui.showLoadingError();
            this.tasks = new TaskList(); // Ensure tasks is always initialized to prevent NullPointerException
        }
    }
    /**
     * Initializes a new Duke application instance.
     *
     * Sets up the user interface, storage system with a default file path, and an empty task list.
     */
    public Duke() {
        String defaultFilePath = "data/tasks.txt";
        this.ui = new Ui();
        this.storage = new Storage(defaultFilePath);
        this.tasks = new TaskList();

    }
    /**
     * Runs the Duke program, starting the chatbot interaction.
     */
    public void run() {
        ui.showSeparator();
        ui.showWelcome();
        Scanner scanner = new Scanner(System.in);
        String input;
        ui.showSeparator();

        do {
            input = scanner.nextLine();
            processCommand(input);
        } while (!input.equalsIgnoreCase("bye"));

        scanner.close();
    }

    /**
     * Processes the user command and executes the corresponding action.
     *
     * @param input The user's input command.
     */
    private void processCommand(String input) {
        try {
            Parser parser = new Parser(input);
            Command command = parser.parse();
            command.execute(tasks, ui, storage);
        } catch (DukeException e) {
            ui.showErrorMessage(e.getMessage());
        }
    }

    /**
     * The main method to start the Duke program.
     *
     * @param args The command-line arguments (not used in this program).
     */
    public static void main(String[] args) {
        new Duke("data/tasks.txt").run();
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
        //...

        //Step 2. Formatting the window to look as expected
        stage.setTitle("Aether");
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
        //More code to be added here later
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
        Label textToAdd = new Label(text);
        textToAdd.setWrapText(true);

        return textToAdd;
    }

    /**
     * Iteration 2:
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    private void handleUserInput() {
        String userMessage = userInput.getText(); // Retrieve user input text
        Label userTextLabel = new Label(userMessage); // Create a Label with user input text
        Label dukeTextLabel = new Label(getResponse(userMessage)); // Create a Label with Duke's response

        // Create DialogBox instances using the user input text and images
        DialogBox userDialogBox = DialogBox.getUserDialog(userMessage, user);
        DialogBox dukeDialogBox = DialogBox.getDukeDialog(dukeTextLabel.getText(), duke);

        // Add the DialogBox instances to the dialogContainer
        dialogContainer.getChildren().addAll(userDialogBox, dukeDialogBox);

        // Clear the user input TextField
        userInput.clear();
    }

    /**
    * Gets the response from the user input.
    *
    * @param input The user input
    * @return String representation of the response
    */
    public String getResponse(String input) {
        try {
            Parser parser = new Parser(input);
            Command command = parser.parse();
            if (command.getType() == Command.CommandType.BYE) {
                Task<Void> exitTask = new Task<Void>() {
                    @Override
                    protected Void call() throws Exception {
                        Thread.sleep(1000);
                        Platform.runLater(Platform::exit);
                        return null;
                    }
                };
                new Thread(exitTask).start();

                return "Goodbye! Hope to see you again soon!";
            }
            return command.execute(tasks, ui, storage);
        } catch (DukeException e) {
            return "Error: " + e.getMessage();
        }
    }
}

