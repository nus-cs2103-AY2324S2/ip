package Echo;

import Echo.Storage.Storage;
import Echo.Ui.Ui;

import java.util.stream.Stream;
import java.util.stream.Collectors;
import java.io.File;
import javafx.application.Application;
import javafx.scene.control.Label;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.animation.PauseTransition;
import javafx.util.Duration;

import static Echo.Ui.Ui.startConversation;

/**
 * The Echo class represents the main application for a chatbot with task management features.
 * It extends the JavaFX Application class and provides a graphical user interface for user interaction.
 */
public class Echo extends Application {
    private TaskManager taskManager;
    private Storage storage;
    private final String FILE_PATH = "." + File.separator + "data" + File.separator + "echo.txt";

    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private TextArea chatArea;

    private Scene scene;
    private AnchorPane mainLayout;
    private Image user = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image echo = new Image(this.getClass().getResourceAsStream("/images/DaEcho.png"));
    private String formattedBotResponse;

    /**
     * Constructor for the Echo class. Initializes the storage and task manager.
     */
    public Echo() {
        storage = new Storage(FILE_PATH);
        this.taskManager = new TaskManager(storage, this);
    }

    /**
     * The main method that launches the JavaFX application.
     *
     * @param args Command-line arguments passed to the application.
     */
    public static void main(String[] args) {
        launch(args);
    }

    /**
     * The start method is called when the application is launched.
     * It initializes UI components, event handlers, user interaction, and the main stage.
     *
     * @param stage The primary stage for the application.
     */
    public void start(Stage stage) {
        initializeUIComponents();

        setupEventHandlers();

        setupUiInteraction();

        setupScrollPaneAutoScroll();

        showStage(stage);

    }

    /**
     * Initializes the UI components such as scroll pane, dialog container, user input field, and buttons.
     * Sets up the layout and styling of the main user interface.
     */
    private void initializeUIComponents() {
        scrollPane = new ScrollPane();
        dialogContainer = new VBox();
        scrollPane.setContent(dialogContainer);

        userInput = new TextField();
        sendButton = new Button("Send");

        mainLayout = new AnchorPane();

        scrollPane.setPrefSize(600, 300);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);

        dialogContainer.prefHeightProperty().bind(scrollPane.heightProperty());
        dialogContainer.setStyle("-fx-background-color: #F4F4F4;");

        userInput.setPromptText("Type here...");

        AnchorPane.setTopAnchor(scrollPane, 10.0);
        AnchorPane.setRightAnchor(scrollPane, 10.0);
        AnchorPane.setBottomAnchor(scrollPane, 50.0);
        AnchorPane.setLeftAnchor(scrollPane, 10.0);

        AnchorPane.setBottomAnchor(userInput, 10.0);
        AnchorPane.setLeftAnchor(userInput, 10.0);
        AnchorPane.setRightAnchor(userInput, 150.0);

        AnchorPane.setBottomAnchor(sendButton, 10.0);
        AnchorPane.setRightAnchor(sendButton, 10.0);

        mainLayout.getChildren().addAll(scrollPane, userInput, sendButton);
        mainLayout.setPrefSize(800, 400);
    }



    /**
     * Sets up event handlers for user interactions, such as button clicks and user input.
     */
    private void setupEventHandlers() {
        sendButton.setOnAction(event -> handleUserInput());
        userInput.setOnAction(event -> handleUserInput());
    }

    /**
     * Sets up the interaction with the UI, including the task manager and initial greetings.
     */
    private void setupUiInteraction() {
        Ui.setEcho(this);
        greetUser();
    }

    /**
     * Greets the user with an initial message and displays it in the chat interface.
     */
    public void greetUser() {
        Label greetingLabel = new Label("Hello! I'm Echo.Echo\nWhat can I do for you?");
        DialogBox botDialog = DialogBox.getDukeDialog(greetingLabel, new ImageView(echo));
        dialogContainer.getChildren().add(botDialog);
    }

    /**
     * Ends the conversation by displaying a farewell message and closing the application after a delay.
     * The application will exit after a pause of 2 seconds.
     */
    public void endConversation() {
        Label farewellLabel = new Label("Bye. Hope to see you again soon!");
        DialogBox botDialog = DialogBox.getDukeDialog(farewellLabel, new ImageView(echo));
        dialogContainer.getChildren().add(botDialog);

        PauseTransition delay = new PauseTransition(Duration.seconds(2));
        delay.setOnFinished(event -> Platform.exit());
        delay.play();
    }


    /**
     * Configures auto-scrolling behavior for the scroll pane when new messages are added.
     */
    private void setupScrollPaneAutoScroll() {
        dialogContainer.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));
    }

    /**
     * Displays the main stage of the application.
     *
     * @param stage The primary stage for the application.
     */
    private void showStage(Stage stage) {
        scene = new Scene(mainLayout);
        stage.setScene(scene);
        stage.setTitle("Echo Chat");
        stage.show();
    }


    /**
     * Iteration 2:
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    private void handleUserInput() {
        String userInputText = userInput.getText();
        assert userInputText != null : "User input should not be null";

        Label userTextLabel = new Label(getResponse(userInputText));
        DialogBox userDialog = DialogBox.getUserDialog(userTextLabel, new ImageView(user));
        assert userTextLabel != null : "User label should not be null";
        assert userDialog != null : "User dialog should not be null";

        startConversation(userInputText, taskManager);

        Label botTextLabel = new Label(formattedBotResponse);
        assert botTextLabel != null : "Bot label should not be null";
        DialogBox botDialog = DialogBox.getDukeDialog(botTextLabel, new ImageView(echo));
        assert botDialog != null : "Bot dialog should not be null";

        Stream.of(userDialog, botDialog).forEach(dialogContainer.getChildren()::add);
        assert dialogContainer != null : "Dialog container should not be null";
        userInput.clear();
    }

    private String getResponse(String input) {
        return "You: " + input + "\n";
    }

    public void displayBotResponse(String response) {
        formattedBotResponse = "Bot: " + response + "\n";
    }
  
    public void echoCommand(String command) {
        Platform.runLater(() -> {
            chatArea.appendText(command + "\n");
        });
    }
}
