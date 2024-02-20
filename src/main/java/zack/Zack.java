package zack;

import java.io.IOException;

import javafx.application.Application;
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
import zack.commands.Command;
import zack.util.Parser;
import zack.util.Storage;
import zack.util.TaskList;
import zack.util.Ui;


/**
 * The main class of the Zack program that handles user interactions and task management.
 */
public class Zack extends Application {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;
    private Image user = new Image(this.getClass().getResourceAsStream("/images/user.png"));
    private Image zack = new Image(this.getClass().getResourceAsStream("/images/zack.jpg"));

    /**
     * Constructs a new Zack instance without specifying a custom file path for data storage.
     * This constructor initializes Zack with default data storage settings, loading tasks
     * from the default file path "data/tasks.txt" if available, or starting with an empty task list
     * if the file is not found or an error occurs during loading.
     *
     * @throws ZackException If there is an error during data loading, a ZackException is thrown
     *                      and an error message is displayed through the user interface (UI).
     */
    public Zack() {
        ui = new Ui();
        storage = new Storage("data/tasks.txt");
        tasks = new TaskList();
        try {
            tasks = new TaskList(storage.load());
        } catch (ZackException e) {
            ui.showLoadingError(e);
            tasks = new TaskList();
        }
    }

    public TaskList getTasks() {
        return this.tasks;
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
        stage.setTitle("Zack");
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
            handleUserInput();
        });

        userInput.setOnAction((event) -> {
            handleUserInput();
        });
    }

    // The following header documentation are written with the help of AI
    // A-AiAssisted

    /**
     * Creates a label with the specified text. The text in the label is set to wrap
     * if it's too long to fit on a single line. This method is useful for creating
     * labels that need to display variable text content in a UI, such as dialog messages.
     *
     * @param text The string to be displayed in the label. This text will wrap if it's too long.
     * @return A `Label` object containing the specified text with wrap text property enabled.
     */
    private Label getDialogLabel(String text) {
        Label textToAdd = new Label(text);
        textToAdd.setWrapText(true);
        return textToAdd;
    }

    /**
     * Handles the processing of user input from a text input component. It retrieves
     * the text entered by the user, generates a response using the `getResponse` method,
     * and then displays both the user's text and the response in the dialog container.
     * This method is essential for the interactive component of the UI, where user
     * inputs and system responses are displayed.
     *
     * Assumes `userInput` is a text input component, `dialogContainer` is a container
     * for displaying dialog boxes, and `user` and `zack` are predefined entities
     * representing the user and the system respectively.
     */
    private void handleUserInput() {
        String userText = userInput.getText();
        String zackText = getResponse(userInput.getText());
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(userText, user),
                DialogBox.getZackDialog(zackText, zack)
        );
        userInput.clear();
    }

    /**
     * Processes a given string input and returns a response. The method parses the
     * input to identify the command it represents, executes the command, and then
     * returns the result of the command execution. If an error occurs during parsing
     * or execution, an appropriate error message is returned. This method centralizes
     * the handling of all commands and their responses within the application.
     *
     * @param input The user input string to be processed.
     * @return A string representing the response to the input command. If the command
     *         execution is successful, the response of the command is returned. If an
     *         error occurs, an error message is returned.
     * @throws ZackException If an error specific to the application's logic occurs.
     * @throws RuntimeException If an I/O error occurs, encapsulated within a RuntimeException.
     */
    String getResponse(String input) {
        try {
            Command command = Parser.parse(input);
            return command.execute(tasks, ui, storage);
        } catch (ZackException e) {
            return e.getMessage();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    /**
     * Enumeration representing different types of tasks.
     */
    public enum TaskType {
        BYE, MARK, UNMARK, LIST, TODO, DEADLINE, EVENT, DELETE, DATE, FIND, SORT
    }
}
