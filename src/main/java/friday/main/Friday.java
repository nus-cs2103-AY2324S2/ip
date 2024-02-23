package friday.main;

import java.io.IOException;

import friday.parser.Parser;
import friday.storage.Storage;
import friday.task.TaskList;
import friday.ui.Ui;
import javafx.application.Application;
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
import org.jetbrains.annotations.NotNull;


/**
 * The main class for the Friday task management application.
 * Friday manages tasks such as Todos, Deadlines, and Events, providing a simple graphical user interface for users.
 * Users can interact with Friday by entering commands in the text field and receiving responses in the chat area.
 */
public class Friday extends Application {

    /** The file path for storing task data. */
    private static final String DATA_FILE_PATH = "./src/main/java/data/Friday.txt";

//    private static final String DATA_FILE_PATH = "./data/Friday.txt";

    /** The user interface handler for displaying messages and interacting with users. */
    private final Ui ui;

    /** The storage handler for reading from and writing to the data file. */
    private final Storage storage;

    /** The list of tasks managed by Friday. */
    private TaskList tasks;

    /** The parser for interpreting user commands and generating responses. */
    private Parser parser;

    /** The scroll pane for containing the dialog container. */
    private ScrollPane scrollPane;

    /** The container for displaying the conversation dialog. */
    private VBox dialogContainer;

    /** The text field for user input. */
    private TextField userInput;

    /** The button for sending user input. */
    private Button sendButton;

    /** The scene for the graphical user interface. */
    private Scene scene;

    /** The image representing the user in the chat interface. */
    private Image user = new Image(this.getClass().getResourceAsStream("/images/Master.png"));

    /** The image representing Friday in the chat interface. */
    private Image duke = new Image(this.getClass().getResourceAsStream("/images/Friday.png"));

    /**
     * Constructs a Friday object with necessary components like UI, Storage, and Parser.
     * Initializes the task list by loading data from a file or creating a new one if the file does not exist.
     */
    public Friday() {
        ui = new Ui();
        assert ui != null : "UI must be initialized";
        storage = new Storage(DATA_FILE_PATH);
        assert storage != null : "Storage must be initialized";

        try {
            storage.checkFile();
        } catch (IOException e) {
            ui.displayMessage(e.getMessage());
        }

        try {
            tasks = storage.loadDataFromFile();
            if (tasks == null) {
                tasks = new TaskList(DATA_FILE_PATH); // Instantiate a new TaskList
            }
        } catch (IOException e) {
            ui.displayMessage(e.getMessage());
        }
    }

    /**
     * Initializes the graphical user interface and sets up the necessary components.
     * Called when the application is launched.
     * @param stage The primary stage for the application.
     */
    @Override
    public void start(Stage stage) {
        dialogContainer = new VBox();
        userInput = new TextField();
        scrollPane = new ScrollPane();
        assert dialogContainer != null : "Dialog container must be initialized";
        assert userInput != null : "User input field must be initialized";
        assert scrollPane != null : "Scroll pane must be initialized";

        scrollPane.setContent(dialogContainer);
        scrollPane.setPrefSize(400, 400);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
        scrollPane.setVvalue(1.0);
        scrollPane.setFitToWidth(true);

        sendButton = new Button("Send");
        assert sendButton != null : "Send button must be initialized";

        AnchorPane mainLayout = new AnchorPane();
        assert mainLayout != null : "Main layout must be initialized";
        mainLayout.getChildren().addAll(scrollPane, userInput, sendButton);

        scene = new Scene(mainLayout);
        assert scene != null : "Scene must be initialized";

        stage.setScene(scene);
        stage.show();

        stage.setTitle("Friday");
        stage.setResizable(false);
        stage.setMinHeight(600.0);
        stage.setMinWidth(400.0);

        mainLayout.setPrefSize(600, 800.0);

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
        assert userInput != null : "User input field must be initialized";
        assert dialogContainer != null : "Dialog container must be initialized";

        String userText = String.valueOf(new Label(userInput.getText()));
        String dukeText = String.valueOf(new Label(getResponse(userInput.getText())));
        assert userText != null : "User text must not be null";
        assert dukeText != null : "Duke text must not be null";

        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(userText, new ImageView(user).getImage()),
                DialogBox.getFridayDialog(dukeText, new ImageView(duke).getImage())
        );
        userInput.clear();
    }

    /**
     * Generates a response to the user input.
     * @param input The user input.
     * @return The response generated by Friday.
     */
    protected String getResponse(String input) {
        if (tasks == null) {
            tasks = new TaskList(DATA_FILE_PATH);
        }
        parser = new Parser(tasks, DATA_FILE_PATH);
        return parser.parseInput(input);
    }
}
