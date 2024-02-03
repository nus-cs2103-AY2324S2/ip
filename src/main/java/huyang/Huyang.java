package huyang;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import javafx.animation.PauseTransition;
import javafx.application.Application;
import javafx.application.Platform;
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
import javafx.util.Duration;

/**
 * The main class for the Huyang CLI chatbot that allows user
 * input to keep track of tasks.
 */
public class Huyang extends Application {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private Parser parser;

    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;
    private Image user = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image duke = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));

    /**
     * Constructor for the Huyang class.
     * Initializes the user interface, parser, and storage, and loads tasks from a file.
     */
    public Huyang() {
        this.ui = new Ui();
        this.parser = new Parser();
        this.storage = new Storage("./data/huyang_tasks.txt");
        try {
            this.tasks = new TaskList(storage.loadTasks());
        } catch (IOException | TaskException e) {
            ui.print(ui.getErrorMessage("Error initializing tasks: " + e.getMessage()));
            this.tasks = new TaskList(new ArrayList<>());
        }
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

        AnchorPane.setLeftAnchor(userInput , 1.0);
        AnchorPane.setBottomAnchor(userInput, 1.0);
        //More code to be added here later

        //Step 3. Add functionality to handle user input.
        sendButton.setOnMouseClicked((event) -> {
            handleUserInput();
        });

        userInput.setOnAction((event) -> {
            handleUserInput();
        });

        dialogContainer.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));
    }

    private Label getDialogLabel(String text) {
        // You will need to import `javafx.scene.control.Label`.
        Label textToAdd = new Label(text);
        textToAdd.setWrapText(true);

        return textToAdd;
    }

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
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    private String getResponse(String input) {
        Parser.CommandType command = parser.parseCommand(input);
        try {
            switch (command) {
            case LIST:
                return ui.getTasksMessage(tasks.getTasks());
            case MARK:
                Task markedTask = tasks.markOrUnmarkTask(input, true);
                storage.saveTasks(tasks.getTasks());
                return ui.getMarkOrUnmarkMessage(markedTask, true);
            case UNMARK:
                Task unmarkedTask = tasks.markOrUnmarkTask(input, false);
                storage.saveTasks(tasks.getTasks());
                return ui.getMarkOrUnmarkMessage(unmarkedTask, false);
            case TODO:
            case DEADLINE:
            case EVENT:
                Task addedTask = tasks.addTask(input, command);
                storage.saveTasks(tasks.getTasks());
                return ui.getAddTaskMessage(addedTask, tasks.getSize());
            case DELETE:
                Task deletedTask = tasks.deleteTask(input);
                storage.saveTasks(tasks.getTasks());
                return ui.getDeleteTaskMessage(deletedTask, tasks.getSize());
            case FIND:
                String keyword = input.substring(5).trim().toLowerCase();
                ArrayList<Task> foundTasks = tasks.findTasks(keyword);
                return ui.getFoundTasksMessage(foundTasks);
            case BYE:
                PauseTransition delay = new PauseTransition(Duration.seconds(1));
                delay.setOnFinished(event -> Platform.exit());
                delay.play();
                return ui.getFarewellMessage();
            case UNKNOWN:
            default:
                return ui.getUnknownCommandMessage();
            }
        } catch (TaskException | IOException e) {
            return ui.getErrorMessage(e.getMessage());
        }
    }

    /**
     * Starts the Huyang chatbot.
     * Displays a greeting, processes user commands, and saves tasks to a file.
     * Supports other features such as list, mark, unmark and delete.
     */
    public void runCLI() {
        ui.print(ui.getGreetingMessage());
        Scanner scanner = new Scanner(System.in);
        boolean isExit = false;

        while (!isExit) {
            String input = scanner.nextLine();
            Parser.CommandType command = parser.parseCommand(input);

            try {
                switch (command) {
                case LIST:
                    ui.print(ui.getTasksMessage(tasks.getTasks()));
                    break;
                case MARK:
                    Task markedTask = tasks.markOrUnmarkTask(input, true);
                    ui.print(ui.getMarkOrUnmarkMessage(markedTask, true));
                    storage.saveTasks(tasks.getTasks());
                    break;
                case UNMARK:
                    Task unmarkedTask = tasks.markOrUnmarkTask(input, false);
                    ui.print(ui.getMarkOrUnmarkMessage(unmarkedTask, false));
                    storage.saveTasks(tasks.getTasks());
                    break;
                case TODO:
                case DEADLINE:
                case EVENT:
                    Task addedTask = tasks.addTask(input, command);
                    ui.print(ui.getAddTaskMessage(addedTask, tasks.getSize()));
                    storage.saveTasks(tasks.getTasks());
                    break;
                case DELETE:
                    Task deletedTask = tasks.deleteTask(input);
                    ui.print(ui.getDeleteTaskMessage(deletedTask, tasks.getSize()));
                    storage.saveTasks(tasks.getTasks());
                    break;
                case FIND:
                    String keyword = input.substring(5).trim().toLowerCase();
                    ArrayList<Task> foundTasks = tasks.findTasks(keyword);
                    ui.print(ui.getFoundTasksMessage(foundTasks));
                    break;
                case BYE:
                    isExit = true;
                    break;
                case UNKNOWN:
                default:
                    ui.print(ui.getUnknownCommandMessage());
                    break;
                }
            } catch (TaskException | IOException e) {
                ui.print(ui.getErrorMessage(e.getMessage()));
            }
        }
        scanner.close();
        ui.print(ui.getFarewellMessage());
        ui.print("Success");
    }

    /**
     * Main method to start the Huyang chatbot.
     *
     * @param args Command-line arguments (not used).
     */
    public static void main(String[] args) {
        new Huyang().runCLI();
        System.exit(0);
    }
}