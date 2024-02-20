package tam;

import gui.DialogBox;
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
import logic.Parser;
import storage.Storage;
import tasks.TaskList;
import ui.Ui;

/**
 * The tam.Tam class is the main class from which tam.Tam the Task Manager is launched.
 */
public class Tam extends Application {
    private static TaskList taskListObj;
    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;
    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/person.jpg"));
    private Image tamImage = new Image(this.getClass().getResourceAsStream("/images/litten2.png"));

    public Tam() {
    }

    /**
     * Main method which is not in use.
     *
     * @param args String arguments
     */
    public static void main(String[] args) {
    }

    /**
     * Launches Tam GUI and repeatedly waits for and reads input commands until a
     * termination command ('bye') is read
     *
     * @param stage Stage argument to start the program
     */
    @Override
    public void start(Stage stage) {

        taskListObj = Storage.getSavedTasks();

        // Step 1. Setting up required components
        Label helloWorld = new Label("Hello World!"); // Creating a new Label control

        // The container for the content of the chat to scroll.
        scrollPane = new ScrollPane();
        dialogContainer = new VBox();
        scrollPane.setContent(dialogContainer);

        userInput = new TextField();
        sendButton = new Button("Send");

        AnchorPane mainLayout = new AnchorPane();
        mainLayout.getChildren().addAll(scrollPane, userInput, sendButton);

        scene = new Scene(mainLayout); // Setting the scene to be our Label

        stage.setScene(scene); // Setting the stage to show our screen
        stage.show(); // Render the stage.

        // Step 2. Formatting the window to look as expected
        stage.setTitle("Tam");
        stage.setResizable(false);
        stage.setMinHeight(600.0);
        stage.setMinWidth(400.0);
        stage.getIcons().add(tamImage);

        mainLayout.setPrefSize(400.0, 600.0);

        scrollPane.setPrefSize(385, 535);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);

        scrollPane.setVvalue(1.0);
        scrollPane.setFitToWidth(true);

        dialogContainer.setPrefHeight(Region.USE_COMPUTED_SIZE);

        userInput.setPrefWidth(325.0);

        sendButton.setPrefWidth(55.0);

        AnchorPane.setBottomAnchor(sendButton, 1.0);
        AnchorPane.setRightAnchor(sendButton, 1.0);

        AnchorPane.setLeftAnchor(userInput, 1.0);
        AnchorPane.setBottomAnchor(userInput, 1.0);

        // Step 3. Add functionality to handle user input.
        sendButton.setOnMouseClicked((event) -> { // event where mouse clicks 'send' button
            handleUserInput();
        });

        userInput.setOnAction((event) -> { // event where user presses 'enter' key
            handleUserInput();
        });

        // dialogContainer listener which automatically scrolls scrollPane down if new content is below
        // current viewing window
        dialogContainer.heightProperty().addListener((observable -> scrollPane.setVvalue(1.0)));

        greetUser();
    }

    private void greetUser() {
        Label tamText = new Label(Ui.greet());
        dialogContainer.getChildren().add(
                DialogBox.getTamDialog(tamText, new ImageView(tamImage))
        );
    }

    private void exitApplication() {
        Platform.exit(); // exit
    }

    /**
     * Iteration 2:
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    private void handleUserInput() {
        String inputCommand = userInput.getText();
        String outputResponse = getResponse(inputCommand);
        Label userText = new Label(inputCommand);
        Label tamText = new Label(outputResponse);
        boolean isTerminatingCommand = outputResponse.equals(Ui.exit());

        dialogContainer.getChildren().addAll(
            DialogBox.getUserDialog(userText, new ImageView(userImage)),
            DialogBox.getTamDialog(tamText, new ImageView(tamImage))
        );
        userInput.clear();

        // launch exit sequence if outputResponse matches terminate program response
        if (isTerminatingCommand) {
            exitApplication();
        }
    }

    /**
     * Returns the result from parsing and executing the user command
     *
     * @param input Input command from user
     * @return String result after parsing and executing input command from user
     */
    private String getResponse(String input) {
        String response = Parser.parseAndExecuteCommand(input, taskListObj).getValue();
        return response;
    }
}
