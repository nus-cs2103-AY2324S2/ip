package duke;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import duke.command.CommandParser;
import duke.command.CommandType;
import duke.commons.exceptions.DukeException;
import duke.commons.utils.DateUtils;
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

    private static TaskList taskList = new TaskList();

    private static PersistentStorageHandler persistentStorageHandler = new PersistentStorageHandler();

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

            String response = getResponse(userInput);
            createAgentDialog(response);

            persistentStorageHandler.writeTaskFileToDisc(taskList);

        } catch (DukeException e) {
            createAgentDialog(e.getMessage());
        } finally {
            userInputField.clear();
        }
    }

    private String getResponse(String userInput) throws DukeException {
        CommandType commandType = CommandParser.parseCommand(userInput);
        String response = "Invalid Command";
        try {
            switch (commandType) {
                case LIST:
                    response = handleList();
                    break;

                case MARK:
                    response = handleMark(userInput);
                    break;

                case UNMARK:
                    response = handleUnmark(userInput);
                    break;

                case DELETE:
                    response = handleDelete(userInput);
                    break;

                case FIND:
                    response = handleFind(userInput);
                    break;

                case TODO:
                    response = handleToDo(userInput);
                    break;

                case DEADLINE:
                    response = handleDeadline(userInput);
                    break;

                case EVENT:
                    response = handleEvent(userInput);
                    break;

                case BYE:
                    response = handleExit();
                    break;

                default:
                    throw new DukeException("Invalid Command" + commandType);
            }
        } catch (DukeException e) {
            response = e.getMessage();
        }
        return response;
    }

    private void createUserDialog(String text) {
        dialogContainer.getChildren().add(
                DialogBox.createUserDialog(new Label(text), new ImageView(user)));
    }

    private void createAgentDialog(String text) {
        dialogContainer.getChildren().add(
                DialogBox.createAgentDialog(new Label(text), new ImageView(derek)));
    }

    /**
     * Displays the list of tasks to the user.
     * 
     * @throws DukeException If an error during task listing.
     */
    private static String handleList() throws DukeException {
        String response = taskList.getFormattedTasks();
        return UserInterface.formatResponse(response);
    }

    /**
     * Marks a specified task as done.
     * 
     * @param userInput The user input containing the index of the task to mark as
     *                  done.
     * @throws DukeException If the task index is invalid.
     */
    private static String handleMark(String userInput) throws DukeException {
        int idx = CommandParser.parseTaskIndex(userInput);
        String response = taskList.markTaskDone(idx);
        return UserInterface.formatResponse(response);
    }

    /**
     * Marks a specified task as not done.
     * 
     * @param userInput The user input containing the index of the task to mark as
     *                  not done.
     * @throws DukeException If the task index is invalid.
     */
    private static String handleUnmark(String userInput) throws DukeException {
        int idx = CommandParser.parseTaskIndex(userInput);
        String response = taskList.markTaskUndone(idx);
        return UserInterface.formatResponse(response);
    }

    /**
     * Deletes a specified task from the task list.
     * 
     * @param userInput The user input containing the index of the task to delete.
     * @throws DukeException If the task index is invalid.
     */
    private static String handleDelete(String userInput) throws DukeException {
        int idx = CommandParser.parseTaskIndex(userInput);
        String response = taskList.deleteTask(idx);
        int totalTasks = taskList.getNumberTasks();
        return UserInterface.formatTaskDeletedResponse(response, totalTasks);
    }

    private static String handleFind(String userInput) throws DukeException {
        String[] keywords = CommandParser.parseFind(userInput);
        ArrayList<Integer> taskIndices = taskList.findTasksByKeywordsMatching(keywords);
        ArrayList<String> response = taskList.getTaskRepresentationsByIndices(taskIndices);
        return UserInterface.formatTasksByIndicesResponse(response);
    }

    /**
     * Adds a new ToDo task to the task list.
     * 
     * @param userInput The user input containing the description of the ToDo task.
     * @throws DukeException If the description is invalid or other errors occur.
     */
    private static String handleToDo(String userInput) throws DukeException {
        String description = CommandParser.parseToDo(userInput);
        String response = taskList.addTask(new ToDo(description));
        int totalTasks = taskList.getNumberTasks();
        return UserInterface.formatTaskAddedResponse(response, totalTasks);
    }

    /**
     * Adds a new Deadline task to the task list.
     * 
     * @param userInput The user input containing the description of the Deadline
     *                  task.
     * @throws DukeException If the description is invalid or other errors occur.
     */
    private static String handleDeadline(String userInput) throws DukeException {
        String[] deadlineDetails = CommandParser.parseDeadline(userInput);
        String description = deadlineDetails[0];
        LocalDate due = DateUtils.parseDateString(deadlineDetails[1]);
        String response = taskList.addTask(new Deadline(description, due));
        int totalTasks = taskList.getNumberTasks();
        return UserInterface.formatTaskAddedResponse(response, totalTasks);
    }

    /**
     * Adds a new Event task to the task list.
     * 
     * @param userInput The user input containing the description of the Event task.
     * @throws DukeException If the description is invalid or other errors occur.
     */
    private static String handleEvent(String userInput) throws DukeException {
        String[] eventDetails = CommandParser.parseEvent(userInput);
        String description = eventDetails[0];
        LocalDate start = DateUtils.parseDateString(eventDetails[1]);
        LocalDate end = DateUtils.parseDateString(eventDetails[2]);
        String response = taskList.addTask(new Event(description, start, end));
        int totalTasks = taskList.getNumberTasks();
        return UserInterface.formatTaskAddedResponse(response, totalTasks);
    }

    private static String handleExit() throws DukeException {
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                System.exit(0);
            }
        }, 1000);

        return UserInterface.formatExit();
    }

}
