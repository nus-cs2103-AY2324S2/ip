package duke;

import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
//import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * The Duke class is the main entry point for the bot application itself
 * It uses an Operator to connect the user to the bot
 * The operator is responsible for handling user input and bot output
 * It's the entry point for the bot
 */
public class Duke {
    // private ScrollPane scrollPane;
    // private VBox dialogContainer;
    // private TextField userInput;

    // private final Image user = new Image(
    // Objects.requireNonNull(this.getClass().getResourceAsStream("/images/user.jpg")));
    // private final Image bot = new Image(
    // Objects.requireNonNull(this.getClass().getResourceAsStream("/images/skynet.png")));
    /**
     * The main method to start the bot.
     * It initializes the bot, prints a greeting, and starts the operator to parse
     * user input
     */
    public static void main(String[] args) throws BotException, IOException {
        // Initial
        System.out.println(Ui.wrapWithSepLine(Bot.getGreeting()));

        // Starting parser to work with bot
        Parser operator = new Parser();
        operator.startOperator();

    }

    private TaskList taskList;
    private TaskRepository taskRepository;

    /*
     * Constructor for the Parser class It initializes the scanner and taskList
     * objects
     */
    public Duke() throws BotException {
        this.taskRepository = new TaskRepository();
        this.taskList = taskRepository.loadTasks();
    }

    // Handle various user inputs
    public String processInput(String userInput) {
        String[] userInputArray = userInput.split(" ");
        String command = userInputArray[0];

        switch (command) {
            case "bye":
                return Bot.botExitMsgGui();
            case "help":
                return Bot.botHelpMsgGui();
            case "list":
                return botListAllTasks(taskList);
            // case "mark":
            // try {
            // markTaskHandler(userInputArray);
            // // save to file
            // taskRepository.saveTasksToFile(taskList);
            // } catch (BotException e) {
            // System.out.println(e.getMessage());
            // }
            // break;
            // case "unmark":
            // try {
            // unmarkTaskHandler(userInputArray);
            // taskRepository.saveTasksToFile(taskList);
            // } catch (BotException e) {
            // System.out.println(e.getMessage());
            // }
            // break;
            // case "todo":
            // try {
            // handleTodoCommand(userInputArray);
            // taskRepository.saveTasksToFile(taskList);
            // } catch (BotException e) {
            // System.out.println(e.getMessage());
            // }
            // break;
            // case "deadline":
            // try {
            // handleDeadlineCommand(userInputArray);
            // taskRepository.saveTasksToFile(taskList);
            // } catch (BotException e) {
            // System.out.println(e.getMessage());
            // }
            // break;
            // case "event":
            // try {
            // handleEventCommand(userInputArray);
            // taskRepository.saveTasksToFile(taskList);
            // } catch (BotException e) {
            // System.out.println(e.getMessage());
            // }
            // break;
            // case "delete":
            // try {
            // handleDeleteCommand(userInputArray);
            // taskRepository.saveTasksToFile(taskList);
            // } catch (BotException e) {
            // System.out.println(e.getMessage());
            // }
            // break;
            // case "find":
            // handleFindCommand(userInputArray);
            // break;
            default:
                return Bot.invalidInputMsgGui();
        }
    }

    /**
     * Returns a string representing all tasks in the task list.
     *
     * @param taskList The task list to be listed.
     * @return A string representing all tasks in the task list.
     */
    private String botListAllTasks(TaskList taskList) {
        StringBuilder tasksMsg = new StringBuilder();
        tasksMsg.append(Bot.botListAllMsgGui()).append("\n");
        tasksMsg.append(listToString(taskList.listTasks())).append("\n");
        tasksMsg.append(TaskCountMsg()).append("\n");
        return tasksMsg.toString();
    }

    /**
     * Returns a multiline string of all items in the list
     * Each string in the list is placed on a new line
     *
     * @param list The list of strings to be converted
     * @return A multiline string of all items in the list
     */
    public static String listToString(List<String> list) {
        StringBuilder sb = new StringBuilder();
        for (String item : list) {
            sb.append(item).append("\n");
        }
        return sb.toString();
    }

    /**
     * Prints the number of tasks in the task list.
     */
    private String TaskCountMsg() {
        return "You have " + taskList.getTaskCount() + " tasks in your list.";
    }

}

// @Override
// public void start(Stage stage) {
// Required components
// The container for the content of the chat to scroll.
// scrollPane = new ScrollPane();
// dialogContainer = new VBox();
// scrollPane.setContent(dialogContainer);
//
// userInput = new TextField();
// Button sendButton = new Button("Send");
//
// AnchorPane mainLayout = new AnchorPane();
// mainLayout.getChildren().addAll(scrollPane, userInput, sendButton);
//
// Scene scene = new Scene(mainLayout);
//
// // Formatting the window to look as expected
// stage.setTitle("WannaBeSkynet");
// stage.setResizable(false);
// stage.setMinWidth(500.0); // Increased from 400.0
// stage.setMinHeight(700.0); // Increased from 600.0
//
// // Set the background
// // mainLayout.setBackground(new Background(new BackgroundFill(Color.DARKGRAY,
// // CornerRadii.EMPTY, Insets.EMPTY)));
// // scene.setFill(Color.DARKCYAN);
//
// mainLayout.setPrefSize(500.0, 700.0); // Increased from 400.0, 600.0
//
// scrollPane.setPrefSize(480, 635); // Increased from 385, 535
// userInput.setPrefWidth(418.0); // Increased from 325.0
// sendButton.setPrefWidth(65.0); // Increased from 55.0
// sendButton.setPrefHeight(30.0);
//
// scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
// scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
//
// scrollPane.setVvalue(1.0);
// scrollPane.setFitToWidth(true);
//
// dialogContainer.setPrefHeight(Region.USE_COMPUTED_SIZE);
//
// AnchorPane.setTopAnchor(scrollPane, 1.0);
//
// AnchorPane.setBottomAnchor(sendButton, 1.0);
// AnchorPane.setRightAnchor(sendButton, 1.0);
//
// AnchorPane.setLeftAnchor(userInput, 1.0);
// AnchorPane.setBottomAnchor(userInput, 1.0);
//
// // Add padding between each DialogBox
// // dialogContainer.setSpacing(5);
//
// // Increase font size of the user input field
// userInput.setFont(new Font(12));
//
// /* functions */
// sendButton.setOnMouseClicked((event) -> {
// dialogContainer.getChildren().add(getDialogLabel(userInput.getText()));
// userInput.clear();
// });
//
// userInput.setOnAction((event) -> {
// dialogContainer.getChildren().add(getDialogLabel(userInput.getText()));
// userInput.clear();
// });
//
// // Scroll down to the end every time dialogContainer's height changes
// dialogContainer.heightProperty().addListener((observable) ->
// scrollPane.setVvalue(1.0));
//
// // Display greeting on application start
// //
// dialogContainer.getChildren().add(DialogBox.getDukeDialog(Bot.getGreeting(),
// // bot));
//
// // Functionality to handle user input.
// sendButton.setOnMouseClicked((event) -> {
// handleUserInput();
// });
//
// userInput.setOnAction((event) -> {
// handleUserInput();
// });
//
// stage.setScene(scene);
// stage.show();
// }

/**
 * Iteration 1:
 * Creates a label with the specified text and adds it to the dialog container.
 *
 * @param text String containing text to add
 * @return a label with the specified text that has word wrap enabled.
 */
// private Label getDialogLabel(String text) {
// // You will need to import `javafx.scene.control.Label`.
// Label textToAdd = new Label(text);
// // textToAdd.setWrappingWidth(350.0);

// textToAdd.setWrapText(true);

// return textToAdd;
// }

/**
 * Handle user input
 */
// public void handleUserInput() {
// String input = String.valueOf(new Label(userInput.getText()));
// String response = String.valueOf(new
// Label(processInput(userInput.getText())));
// dialogContainer.getChildren().addAll(
// DialogBox.getUserDialog(input, user),
// DialogBox.getDukeDialog(response, bot));
// userInput.clear();
// }

/**
 * Iteration 2:
 * Creates two dialog boxes, one echoing user input and the other containing
 * Duke's reply and then appends them to
 * the dialog container. Clears the user input after processing.
 */
// private void echoUserInput() {
// String userText = String.valueOf(new Label(userInput.getText()));
// String botText = String.valueOf(new Label(getResponse(userInput.getText())));
// dialogContainer.getChildren().addAll(
// DialogBox.getUserDialog(userText, user),
// DialogBox.getDukeDialog(botText, bot));
// userInput.clear();
// }

// String getResponse(String input) {
// return "You said: " + input;
// }
// }
