/*
 * Package: Echo
 * Module/Project Name: Echo
 * File: Echo.java
 *
 * Description:
 * This class serves as the entry point for the Echo application, creating instances of Ui, Storage, and TaskManager,
 * and orchestrating the start and end of user interactions.
 *
 */

package Echo;

import Echo.Storage.Storage;
import Echo.Ui.Ui;

import java.io.File;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.application.Platform;

public class Echo extends Application {
    private TaskManager taskManager;
    private Storage storage;
    private Ui ui;
    private final String FILE_PATH = "." + File.separator + "data" + File.separator + "echo.txt";

    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private TextArea chatArea;

    private Scene scene;

    /**
     * Constructor for the Echo class.
     * Initializes instances of Ui, Storage, and TaskManager.
     */
    public Echo() {
        ui = new Ui();
        storage = new Storage(FILE_PATH);
        //this.taskManager = new TaskManager(storage);
        this.taskManager = new TaskManager(storage, this);
    }

    /**
     * Main method for starting the Echo application.
     *
     * @param args Command-line arguments (not used in this implementation).
     */
    public static void main(String[] args) {
        launch(args);
    }

    /**
     * Initiates the conversation by starting the interaction with the user through the Ui class.
     */
    public void start(Stage stage) {
        // Setting up JavaFX components
        scrollPane = new ScrollPane();
        dialogContainer = new VBox();
        scrollPane.setContent(dialogContainer);

        userInput = new TextField();
        sendButton = new Button("Send");
        chatArea = new TextArea();

        AnchorPane mainLayout = new AnchorPane();
        mainLayout.getChildren().addAll(scrollPane, userInput, sendButton, chatArea);

        // Configuring the layout and scene
        AnchorPane.setTopAnchor(scrollPane, 0.0);
        AnchorPane.setRightAnchor(scrollPane, 0.0);
        AnchorPane.setBottomAnchor(scrollPane, 50.0);
        AnchorPane.setLeftAnchor(scrollPane, 0.0);

        AnchorPane.setBottomAnchor(userInput, 0.0);
        AnchorPane.setLeftAnchor(userInput, 0.0);
        AnchorPane.setRightAnchor(userInput, 0.0);  // Full width

        AnchorPane.setBottomAnchor(sendButton, 0.0);
        AnchorPane.setRightAnchor(sendButton, 0.0);

        AnchorPane.setTopAnchor(chatArea, 0.0);
        AnchorPane.setRightAnchor(chatArea, 0.0);
        AnchorPane.setBottomAnchor(chatArea, 50.0);
        AnchorPane.setLeftAnchor(chatArea, 0.0);

        mainLayout.setPrefSize(400, 400);

        scene = new Scene(mainLayout);

        stage.setScene(scene);
        stage.setTitle("Echo Chat");
        stage.show();

        // Setting up the interaction with Ui class
        Ui.setEcho(this);
        Ui.greetUser();

        // Event handler for the Send button
        sendButton.setOnAction(event -> {
            String userCommand = userInput.getText();
            displayUserInput(userCommand);
            Ui.startConversation(userCommand, this.taskManager);
            userInput.clear();
        });
    }

    /**
     * Prints the command received from the user.
     *
     * @param command The command entered by the user.
     */
    public void echoCommand(String command) {
        Platform.runLater(() -> {
            chatArea.appendText("____________________________________________________________\n");
            chatArea.appendText(command + "\n");
            chatArea.appendText("____________________________________________________________\n");
        });
    }

    /**
     * Displays the response from the bot in the GUI.
     *
     * @param response The response to display.
     */
    public void displayBotResponse(String response) {
        String formattedResponse = "Bot: " + response + "\n";
        Platform.runLater(() -> chatArea.appendText(formattedResponse));
    }

    public void displayUserInput(String userInput) {
        String formattedUserInput = "You: " + userInput + "\n";
        Platform.runLater(() -> chatArea.appendText(formattedUserInput));
    }
}