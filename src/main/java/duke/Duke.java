package duke;

import java.io.IOException;

import duke.command.Command;
import javafx.application.Application;
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
 * The Duke program implements an application that manages a list of tasks.
 * It allows adding, deleting, and completing tasks, as well as listing all the current tasks.
 */
public class Duke extends Application {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;

    private final Image userImage = new Image(this.getClass().getResourceAsStream("/images/UserPhoto.png"));
    private final Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/JamiePhoto.png"));
    public Duke(String filePath) {
        initializeComponents(filePath);
    }

    private void initializeComponents(String filePath) {
        ui = new Ui();
        try {
            storage = new Storage(filePath);
            tasks = new TaskList(storage.load());
        } catch (JamieException e) {
            tasks = new TaskList(); // Start with an empty task list if there's an error
        }
    }

    @Override
    public void start(Stage stage) {
        setupUiComponents(stage);
        setupEventHandlers();
    }

    private void setupUiComponents(Stage stage) {
        scrollPane = new ScrollPane();
        dialogContainer = new VBox();
        scrollPane.setContent(dialogContainer);

        userInput = new TextField();
        sendButton = new Button("Send");

        AnchorPane mainLayout = new AnchorPane(scrollPane, userInput, sendButton);
        Scene scene = new Scene(mainLayout);

        stage.setScene(scene);
        configureStage(stage);
        stage.show();
    }

    private void configureStage(Stage stage) {
        stage.setTitle("Jamie");
        stage.setResizable(false);
        stage.setMinHeight(600.0);
        stage.setMinWidth(400.0);

        setLayoutSizes();
    }

    private void setLayoutSizes() {
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
        AnchorPane.setLeftAnchor(userInput, 1.0);
        AnchorPane.setBottomAnchor(userInput, 1.0);
    }

    private void setupEventHandlers() {
        sendButton.setOnMouseClicked(event -> handleUserInput());
        userInput.setOnAction(event -> handleUserInput());
        dialogContainer.heightProperty().addListener(observable -> scrollPane.setVvalue(1.0));
    }
    private void handleUserInput() {
        String userMessage = userInput.getText();
        String response = getResponse(userMessage);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(userMessage, userImage),
                DialogBox.getDukeDialog(response, dukeImage));
        userInput.clear();
    }

    public String getResponse(String input) {
        try {
            Parser parser = new Parser();
            Command command = parser.parse(input);
            return command.execute(tasks, ui, storage);
        } catch (JamieException e) {
            return ui.showError(e.getMessage());
        } catch (IOException e) {
            return "Error getting response: " + e.getMessage();
        }
    }
}
