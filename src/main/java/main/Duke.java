package main;

import commands.Command;
import commands.Parser;

import exceptions.DukeException;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;


import tasks.TaskList;

import ui.Ui;
import ui.DialogBox;

/**
 * Duke is the main class for the task management application.
 * It initializes the application and starts the interaction loop with the user.
 */
public class Duke extends Application {

    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;

    private Ui ui;
    private Storage storage;
    private TaskList taskList;
    private Image user = new Image(this.getClass().getResourceAsStream("/perrytheplatypus.png"));
    private Image duke = new Image(this.getClass().getResourceAsStream("/drheinzdoofenshmirtz.png"));

    /**
     * Constructs a new Duke object.
     * Initializes the UI, storage, and task list components of the application.
     *
     * @param filePath The path to the file where tasks are saved and loaded from.
     */
    public Duke(String filePath) {
        ui = new Ui();
        ui.greet();
        storage = new Storage(filePath);
        taskList = new TaskList(storage.loadTasks());
        run();

    }

    public Duke() {}

    /**
     * Starts the application and enters the command processing loop.
     * The loop reads commands from the user, parses them, and executes them
     * until the user issues the bye command.
     */
    public void run() {
        boolean isRunning = true;
        while (isRunning) {
            String userInput = ui.readCommand();
            try {
                Command c = Parser.parse(userInput);
                c.execute(taskList, ui, storage, userInput);
                if (c.equals(Command.BYE)) {
                    break;
                }
            } catch (DukeException e) {
            System.out.println(e.getMessage());
            } catch (IndexOutOfBoundsException e) {
            System.out.println("Quit yappin, that task does not exist");
        }


        }
        ui.exit();
    }

    /**
     * The main entry point for the application.
     * Creates a new Duke instance and starts the application.
     *
     * @param args Command line arguments, not used in this application.
     */
    public static void main(String[] args) {
        new Duke("./data/taskyapper.txt");

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
        stage.setTitle("TaskYapper");
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

        //Step 3. Add functionality to handle user input.
        sendButton.setOnMouseClicked((event) -> {
            handleUserInput();
        });

        userInput.setOnAction((event) -> {
            handleUserInput();
        });

        dialogContainer.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));
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
        return "Duke heard: " + input;
    }
}
