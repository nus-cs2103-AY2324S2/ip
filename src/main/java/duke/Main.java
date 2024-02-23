package duke;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import duke.command.CommandParser;
import duke.command.CommandType;
import duke.commons.exceptions.DukeException;
import duke.commons.utils.DateUtils;
import duke.controller.LogicController;
import duke.storage.PersistentStorageHandler;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.TaskList;
import duke.task.ToDo;
import duke.ui.DialogBox;
import duke.ui.UserInterface;
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

/**
 * The main entry point for the Duke application.
 * This class handles the initialization and main execution loop of the
 * application,
 * including loading tasks from disk, processing user commands, and exiting the
 * application.
 */
public class Main extends Application {

    private TaskList taskList = new TaskList();

    private PersistentStorageHandler persistentStorageHandler = new PersistentStorageHandler();

    private LogicController logicController;

    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInputField;
    private Button sendButton;
    private Scene scene;

    private Image user = new Image(this.getClass().getResourceAsStream("/images/User.png"));
    private Image derek = new Image(this.getClass().getResourceAsStream("/images/ChineseBeaver.png"));

    @Override
    public void start(Stage stage) {
        initGui(stage);
        initEventHandlers();
        initLoadStorage();
        initController();
    }

    private void initGui(Stage stage) {
        // Component Setup
        scrollPane = new ScrollPane();
        dialogContainer = new VBox();
        scrollPane.setContent(dialogContainer);

        userInputField = new TextField();
        sendButton = new Button("Send");

        AnchorPane mainLayout = new AnchorPane();
        mainLayout.getChildren().addAll(scrollPane, userInputField, sendButton);

        scene = new Scene(mainLayout);

        stage.setScene(scene);
        stage.show();

        // Formatting
        stage.setTitle("Derek");
        stage.setResizable(false);
        stage.setMinHeight(800.0);
        stage.setMinWidth(600.0);

        mainLayout.setPrefSize(600.0, 800.0);

        scrollPane.setPrefSize(585, 735);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);

        scrollPane.setVvalue(1.0);
        scrollPane.setFitToWidth(true);

        dialogContainer.setPrefHeight(Region.USE_COMPUTED_SIZE);

        userInputField.setPrefWidth(525.0);

        sendButton.setPrefWidth(70.0);

        AnchorPane.setTopAnchor(scrollPane, 1.0);

        AnchorPane.setBottomAnchor(sendButton, 1.0);
        AnchorPane.setRightAnchor(sendButton, 1.0);

        AnchorPane.setLeftAnchor(userInputField, 1.0);
        AnchorPane.setBottomAnchor(userInputField, 1.0);
    }

    private void initEventHandlers() {
        // Functionality
        sendButton.setOnMouseClicked((event) -> {
            handleUserInput();
        });

        userInputField.setOnAction((event) -> {
            handleUserInput();
        });

        // Scrolls to the bottom whenever height changes
        dialogContainer.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));
    }

    private void initLoadStorage() {
        try {
            if (persistentStorageHandler.ensureTaskFileExists()) {
                taskList = persistentStorageHandler.readTaskFileFromDisc();
                int numTasks = taskList.getNumberTasks();
                String response = "Read existing tasks (" + numTasks + ") from disc";
                createAgentDialog(response);
            }
        } catch (DukeException e) {
            createAgentDialog(e.getMessage());
        }
    }

    private void handleUserInput() {
        try {
            String userInput = userInputField.getText();
            createUserDialog(userInput);

            String response = logicController.processUserInput(userInput);
            createAgentDialog(response);

            persistentStorageHandler.writeTaskFileToDisc(taskList);

        } catch (DukeException e) {
            createAgentDialog(e.getMessage());
        } finally {
            userInputField.clear();
        }
    }

    private void createUserDialog(String text) {
        dialogContainer.getChildren().add(
                DialogBox.createUserDialog(new Label(text), new ImageView(user)));
    }

    private void createAgentDialog(String text) {
        dialogContainer.getChildren().add(
                DialogBox.createAgentDialog(new Label(text), new ImageView(derek)));
    }

    private void initController() {
        logicController = new LogicController(taskList);
    }

}
