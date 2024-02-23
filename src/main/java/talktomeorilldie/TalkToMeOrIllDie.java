package talktomeorilldie;

import java.io.IOException;
import java.util.Objects;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * Represents the main class of the TalkToMeOrIllDie program.
 */
public class TalkToMeOrIllDie extends Application {
    private final Storage storage;
    private TaskList tasks;
    private final Ui ui;

    private final Image user;
    private final Image talktomeorilldie;
    private ScrollPane chatScrollPane;
    private VBox dialogContainer;
    private TextField userInputField;
    private Button sendButton;

    /**
     * Constructor for TalkToMeOrIllDie.
     */
    public TalkToMeOrIllDie() throws IOException {
        ui = new Ui();
        storage = new Storage("./data/talktomeorilldie.txt");
        tasks = new TaskList(storage.loadTasks());
        user = new Image(Objects.requireNonNull(this.getClass().getResourceAsStream("/images/DaUser.png")));
        talktomeorilldie =
                new Image(Objects.requireNonNull(this.getClass().getResourceAsStream("/images/DaDuke.png")));
    }

    /**
     * Main method to start the TalkToMeOrIllDie program.
     * @param args The arguments to start the program.
     */
    public static void main(String[] args) {
        launch(args);
    }

    /**
     * Starts the TalkToMeOrIllDie program.
     * @param stage The stage to start the program.
     */
    @Override
    public void start(Stage stage) {
        initializeLayout(stage);
        configureScrollPane();
        configureUserInput();
        configureSendButton();
        greetUser();
        configureEventHandlers();
    }

    /**
     * Initializes the layout of the TalkToMeOrIllDie program.
     * @param stage The stage to initialize the layout.
     */
    private void initializeLayout(Stage stage) {
        chatScrollPane = new ScrollPane();
        dialogContainer = new VBox();
        chatScrollPane.setContent(dialogContainer);

        userInputField = new TextField();
        sendButton = new Button("Send");

        AnchorPane mainLayout = new AnchorPane();
        mainLayout.getChildren().addAll(chatScrollPane, userInputField, sendButton);

        Scene scene = new Scene(mainLayout);

        stage.setScene(scene);
        stage.show();

        //Step 2. Formatting the window to look as expected
        stage.setTitle("TalkToMeOrIllDie");
        stage.setResizable(false);
        stage.setMinHeight(600.0);
        stage.setMinWidth(400.0);

        mainLayout.setPrefSize(400.0, 600.0);
    }

    /**
     * Configures the scroll pane for the TalkToMeOrIllDie program.
     */
    private void configureScrollPane() {
        chatScrollPane.setPrefSize(385, 535);
        chatScrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        chatScrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);

        chatScrollPane.setVvalue(1.0);
        chatScrollPane.setFitToWidth(true);

        dialogContainer.setPrefHeight(Region.USE_COMPUTED_SIZE);

        //Scroll down to the end every time dialogContainer's height changes.
        dialogContainer.heightProperty().addListener((observable) -> chatScrollPane.setVvalue(1.0));
    }

    /**
     * Configures the user input field for the TalkToMeOrIllDie program.
     */
    private void configureUserInput() {
        userInputField.setPrefWidth(325.0);
    }

    /**
     * Configures the send button for the TalkToMeOrIllDie program.
     */
    private void configureSendButton() {
        sendButton.setPrefWidth(55.0);

        AnchorPane.setTopAnchor(chatScrollPane, 1.0);
        AnchorPane.setBottomAnchor(sendButton, 1.0);
        AnchorPane.setRightAnchor(sendButton, 1.0);
        AnchorPane.setLeftAnchor(userInputField, 1.0);
        AnchorPane.setBottomAnchor(userInputField, 1.0);
    }

    /**
     * Greets the user with a welcome message.
     */
    private void greetUser() {
        dialogContainer.getChildren().add(DialogBox.gettalktomeorilldieDialog(ui.welcomeMessage(), talktomeorilldie));
    }

    /**
     * Configures the event handlers for the TalkToMeOrIllDie program.
     */
    private void configureEventHandlers() {
        //Step 3. Add functionality to handle user input.
        sendButton.setOnMouseClicked((event) -> handleUserInput());
        userInputField.setOnAction((event) -> handleUserInput());
    }

    /**
     * Handles the user input and displays the appropriate response.
     */
    private void handleUserInput() {
        String userText = userInputField.getText();
        String talktomeorilldieText = getResponse(userInputField.getText());
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(userText, user),
                DialogBox.gettalktomeorilldieDialog(talktomeorilldieText, talktomeorilldie)
        );
        userInputField.clear();
    }

    /**
     * Gets the response from TalkToMeOrIllDie based on the user input.
     * @param input User input.
     * @return TalkToMeOrIllDie's response based on the user input.
     */
    String getResponse(String input) {
        try {
            String result = Parser.parse(input, tasks, ui);
            assert result != null;
            if (result.equals("     Bye. Hope to see you again soon!")) {
                storage.saveTasksToFile(TaskList.getTasks(), TaskList.getTaskNum());
                ui.closeScanner();
                // Wait for 1.5 seconds before exiting the platform
                new Thread(() -> {
                    try {
                        Thread.sleep(1500);
                        Platform.exit();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }).start();
            }
            return result;
        } catch (DukeException e) {
            return e.getMessage();
        }
    }
}
