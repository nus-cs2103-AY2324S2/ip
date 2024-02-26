package liv;

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
import liv.container.Storage;
import liv.container.TaskList;
import liv.exception.LivException;
import liv.processor.Command;
import liv.processor.Parser;
import liv.ui.DialogBox;
import liv.ui.Ui;

/**
 * The main class to run the chatbot.
 * <p>
 * This class initializes the user interface, manages the task list, and handles user input.
 * It also sets up the stage and scene for the application, including the layout and images.
 */
public class Liv extends Application {
    /**
     * The storage instance for managing data persistence.
     */
    private Storage storage;
    /**
     * The task list that stores and manages tasks.
     */
    private TaskList tasks;
    /**
     * The user interface component for displaying and interacting with the chatbot.
     */
    private Ui ui;
    /**
     * The scroll pane for the chat dialog container.
     */
    private ScrollPane scrollPane;
    /**
     * The container for chat dialogs.
     */
    private VBox dialogContainer;
    /**
     * The text field for user input.
     */
    private TextField userInput;
    /**
     * The button for sending user input.
     */
    private Button sendButton;
    /**
     * The main scene of the application.
     */
    private Scene scene;
    /**
     * The image for the user icon.
     */
    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/user.png"));
    /**
     * The image for the Liv chatbot icon.
     */
    private Image livImage = new Image(this.getClass().getResourceAsStream("/images/liv.png"));
    /**
     * The application icon.
     */
    private Image iconImage = new Image(this.getClass().getResourceAsStream("/images/icon.png"));

    public Liv() {
        //...
    }

    /**
     * Constructor for the Liv class with a specified data file path.
     *
     * @param filePath The path to the data file where tasks data is stored.
     */
    public Liv(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        tasks = new TaskList();
        storage.loadDataFile();
    }

    /**
     * Initializes the application's stage and scene.
     *
     * @param stage The primary stage for this application.
     */
    @Override
    public void start(Stage stage) {
        //The container for the content of the chat to scroll.
        scrollPane = new ScrollPane();
        dialogContainer = new VBox();
        dialogContainer.setPrefHeight(Region.USE_COMPUTED_SIZE);
        dialogContainer.setFillWidth(true);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
        scrollPane.setContent(dialogContainer);

        userInput = new TextField();
        sendButton = new Button("Send");

        AnchorPane mainLayout = new AnchorPane();
        mainLayout.getChildren().addAll(scrollPane, userInput, sendButton);

        scene = new Scene(mainLayout);

        stage.getIcons().add(iconImage);
        stage.setScene(scene);
        stage.show();

        stage.setTitle("Liv");
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

        sendButton.setOnMouseClicked((event) -> {
            dialogContainer.getChildren().add(getDialogLabel(userInput.getText()));
            userInput.clear();
        });

        userInput.setOnAction((event) -> {
            dialogContainer.getChildren().add(getDialogLabel(userInput.getText()));
            userInput.clear();
        });

        sendButton.setOnMouseClicked((event) -> {
            handleUserInput();
        });

        userInput.setOnAction((event) -> {
            handleUserInput();
        });
    }

    /**
     * Creates a label for the chat dialog with the given text.
     *
     * @param text The text to display in the dialog.
     * @return A label configured for chat dialogs.
     */
    private Label getDialogLabel(String text) {
        Label textToAdd = new Label(text);
        textToAdd.setWrapText(true);

        return textToAdd;
    }

    /**
     * Handles user input by parsing it, executing the corresponding command, and displaying the response.
     */
    private void handleUserInput() {
        String input = userInput.getText();
        String response = null;

        try {
            response = getResponse(input);
        } catch (LivException e) {
            throw new RuntimeException(e);
        }

        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getLivDialog(response, livImage)
        );
        userInput.clear();
    }

    /**
     * Parses and executes a command from the user input, returning the response.
     *
     * @param input The user input to process.
     * @return The response generated by the command execution.
     * @throws LivException if there is an error during command execution.
     */
    public String getResponse(String input) throws LivException {
        try {
            Command command = Parser.parse(input);
            String response = command.execute(tasks, ui, storage);
            return response;
        } catch (LivException e) {
            return e.getMessage();
        }
    }
}
