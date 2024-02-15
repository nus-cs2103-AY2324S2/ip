package duke;

import java.io.IOException;
import javafx.application.Platform;
import javafx.scene.text.Text;

import duke.command.Command;
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


/**
 * The Duke program implements an application that
 * manages a list of tasks. It allows adding, deleting,
 * and completing tasks, as well as listing all the current tasks.
 */
public class Duke extends Application {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private Parser parser;
    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;
    private final Image user = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private final Image duke = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));
    //private GuiObserver guiObserver;
    /**
     * Constructs a new Duke object.
     * Initializes the user interface, storage, and task list.
     */
    public Duke() {
        String filePath = "./data/jamie.txt";
        this.storage = new Storage(filePath);
        this.ui = new Ui();
        this.tasks = new TaskList();
    }

    /**
     * Constructs a new Duke object.
     * Initializes the user interface, storage, and task list.
     *
     * @param filePath The file path to load and save tasks.
     */
    public Duke(String filePath) {
        this.ui = new Ui();
        try {
            this.storage = new Storage(filePath);
            this.tasks = new TaskList(storage.load());
        } catch (IOException ie) {
            System.exit(0);
        } catch (JamieException e) {
            ui.showLoadingError();
            tasks = new TaskList();
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

        //
        // mainWindow.displayDukeMessage(ui.showWelcome());

        //Step 2. Formatting the window to look as expected
        stage.setTitle("Jamie");
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
     * Iteration 2:
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    private void handleUserInput() {
        String userMessage = userInput.getText(); // Retrieve user input text
        Label userTextLabel = new Label(userMessage); // Create a Label with user input text
        getResponse(userMessage);
        Label dukeTextLabel = new Label(getResponse(userMessage)); // Create a Label with Duke's response

        // Create DialogBox instances using the user input text and images
        DialogBox userDialogBox = DialogBox.getUserDialog(userTextLabel.getText(), user);
        DialogBox dukeDialogBox = DialogBox.getDukeDialog(dukeTextLabel.getText(), duke);

        // Add the DialogBox instances to the dialogContainer
        dialogContainer.getChildren().addAll(userDialogBox, dukeDialogBox);

        // Clear the user input TextField
        userInput.clear();
    }

    public String getResponse(String input) {
        try {
            parser = new Parser(input);
            Command command = parser.parse(input);
            return command.execute(tasks, ui, storage);
            //return this.lastMessage = ui.getLastMessage();
        } catch (JamieException e) {
            return "Invalid input! Please only use the available functions.";
        }
    }

}