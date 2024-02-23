package duke;

import java.util.ArrayList;
import java.util.Scanner;
import duke.Command.Command;
import duke.Task.Task;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.layout.Region;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;




/**
 * The Duke class represents a task management application.
 * It allows users to add, delete, and mark tasks as done.
 */
public class Duke extends Application{
    private TaskList taskList;
    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;
    private Image user = new Image(this.getClass().getResourceAsStream("/images/User.jpg"));
    private Image duke = new Image(this.getClass().getResourceAsStream("/images/Duke.jpg"));


    /**
     * Constructs a Duke object.
     * Loads tasks from file and initializes the task list.
     */
    public Duke() {
        ArrayList<Task> tasks = Storage.loadTasksFromFile();
        taskList = new TaskList(tasks);
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

        //More code to be added here later
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
        String userText = userInput.getText();
        String dukeText = getResponse(userInput.getText());
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(userText, user),
                DialogBox.getDukeDialog(dukeText, duke)
        );
        userInput.clear();
    }



    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    String getResponse(String input) {
        if (input.equalsIgnoreCase("bye")) {
            Storage.saveTasksToFile(taskList.getTasks());
            return "Goodbye!";
        }
        try {
            Command cmd = Parser.parseCommand(input);
            return cmd.execute(taskList, input);
        } catch (DukeException e) {
            return "OOPS!!! " + e.getMessage();
        }
    }



    /**
     * Runs the Duke application.
     * Prompts the user for commands and executes them until the user enters "bye".
     * Saves tasks to file before exiting.
     */
    void run() {
        Ui.printLogo();
        Ui.printWelcomeMessage();

        Scanner scanner = new Scanner(System.in);
        String command = Ui.getUserCommand(scanner);

        while (!command.equals("bye")) {
            try {
                Command cmd = Parser.parseCommand(command);
                Ui.printMessage(cmd.execute(taskList, command));
            } catch (DukeException e) {
                Ui.printMessage("OOPS!!! " + e.getMessage());
            }
            command = Ui.getUserCommand(scanner);
        }

        Storage.saveTasksToFile(taskList.getTasks());
        Ui.printGoodbyeMessage();
    }

    /**
     * The entry point of the Duke application.
     * Creates a Duke object and runs the application.
     *
     * @param args The command-line arguments.
     */
    public static void main(String[] args) {
        new Duke().run();
    }
}