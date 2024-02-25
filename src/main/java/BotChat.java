import java.util.ArrayList;
import java.util.Scanner;

import exception.IncompleteCommandException;
import exception.InvalidCommandException;
import exception.InvalidTaskNumberException;
import gui.DialogBox;
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
import parser.Parser;
import task.Deadline;
import task.Event;
import task.Task;
import task.ToDo;

/**
 * This class is the chatbot.
 *
 */
public class BotChat extends Application {
    private static boolean isTerminate = false;
    private static final String FILEPATH = "./././data/botchat.txt";
    private static Storage storage;
    private static TaskList taskArrayList;
    private static Parser parser;
    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;
    private Image duke = new Image(this.getClass().getResourceAsStream("/images/tomnjerry1.png"));
    private Image user = new Image(this.getClass().getResourceAsStream("/images/tomnjerry2.png"));

    public BotChat() {
    }

    /**
     * Adds a new task based on the user input command.
     *
     * @param s The user input command containing the task details.
     * @throws Exception If the command is invalid or incomplete.
     */
    public static void addTask(String s) throws Exception {
        String taskDescription;
        switch (parser.extractCommand(s)) {
        case "todo":
            taskDescription = parser.extractDescription(s);
            Task newDescriptionTask = new ToDo(taskDescription);
            storage.addToDataStore(newDescriptionTask);
            taskArrayList.addTask(newDescriptionTask);
            break;
        case "deadline":
            taskDescription = parser.extractDescription(s);
            String[] deadlineStringParts = taskDescription.split("/by ");
            if (deadlineStringParts.length < 2) {
                throw new IncompleteCommandException("task.Deadline command incomplete. It should be in the "
                        + "form of deadline description /by datetime.");
            } else {
                Task newDeadlineTask = new Deadline(deadlineStringParts[0], deadlineStringParts[1]);
                storage.addToDataStore(newDeadlineTask);
                taskArrayList.addTask(newDeadlineTask);
            }
            break;
        case "event":
            taskDescription = parser.extractDescription(s);
            String[] eventStringParts = taskDescription.split("/from |/to ");
            if (eventStringParts.length < 3) {
                throw new IncompleteCommandException("task.Event command incomplete. It should be in the "
                        + "form of event description /from datetime /to datetime.");
            } else {
                Task newEventTask = new Event(eventStringParts[0], eventStringParts[1], eventStringParts[2]);
                storage.addToDataStore(newEventTask);
                taskArrayList.addTask(newEventTask);
            }
            break;
        default:
            throw new InvalidCommandException(s);
        }
    }

    /**
     * Generates a response based on the user input command.
     *
     * @param s The user input command.
     * @return The response message.
     */
    public static String response(String s) {
        String command = parser.extractCommand(s);
        switch (command) {
        case "bye":
            isTerminate = true;
            return Ui.byeMessage();
        case "list":
            StringBuilder listStringBuilder = new StringBuilder("Here are the tasks in your list: \n");
            for (int i = 1; i <= taskArrayList.getLastIdx(); i++) {
                listStringBuilder.append(i);
                listStringBuilder.append(". ");
                listStringBuilder.append(taskArrayList.getTaskByIdx(i - 1).toString());
                listStringBuilder.append("\n ");
            }
            return listStringBuilder.toString();
        case "find":
            try {
                ArrayList<Task> tasksWithKeyword = taskArrayList.getTasksWithKeyword(parser.extractDescription(s));
                StringBuilder findStringBuilder = new StringBuilder("Here are the matching tasks in your list: \n");
                for (int i = 0; i < tasksWithKeyword.size(); i++) {
                    findStringBuilder.append(i + 1);
                    findStringBuilder.append(". ");
                    findStringBuilder.append(tasksWithKeyword.get(i).toString());
                    findStringBuilder.append("\n ");
                }
                return findStringBuilder.toString();
            } catch (IncompleteCommandException e) {
                return e.toString();
            }
        case "mark":
            try {
                int taskNum = convertTaskNumStringToInt(s);
                storage.editDataStoreTaskAsDone(taskNum);
                return markTaskAsDone(taskNum);
            } catch (InvalidTaskNumberException e) {
                return e.toString();
            }
        case "unmark":
            try {
                int taskNum = convertTaskNumStringToInt(s);
                storage.editDataStoreTaskAsUndone(taskNum);
                return unmarkTaskAsDone(taskNum);
            } catch (InvalidTaskNumberException e) {
                return e.toString();
            }
        case "delete":
            String requestedDeletion = s.substring(7);
            try {
                return deleteTask(requestedDeletion);
            } catch (InvalidTaskNumberException e) {
                return e.toString();
            }
        default:
            try {
                addTask(s);
                return Ui.addTaskMessage(taskArrayList.getTaskByIdx(taskArrayList.getLastIdx() - 1).toString(),
                        taskArrayList.getLastIdx());
            } catch (Exception e) {
                return e.toString();
            }
        }
    }

    /**
     * Marks the specified task as done.
     *
     * @param taskNum The number of the task to mark as done.
     * @return The message indicating the task was marked as done.
     * @throws InvalidTaskNumberException If the task number is invalid.
     */
    public static String markTaskAsDone(int taskNum) throws InvalidTaskNumberException {
        taskArrayList.getTaskByIdx(taskNum - 1).markAsDone();
        return Ui.markTaskAsDoneMessage(taskArrayList.getTaskByIdx(taskNum - 1).toString());
    }

    /**
     * Converts the task number string to an integer.
     *
     * @param s The task number string.
     * @return The integer representation of the task number.
     * @throws InvalidTaskNumberException If the task number is invalid.
     */
    private static int convertTaskNumStringToInt(String s) throws InvalidTaskNumberException {
        String taskNumString = s.split("\\s+")[1];
        int taskNum = Integer.parseInt(taskNumString);
        if (taskNum > taskArrayList.getLastIdx()) {
            throw new InvalidTaskNumberException(taskNumString);
        }
        return taskNum;
    }

    /**
     * Marks the specified task as undone.
     *
     * @param taskNum The number of the task to mark as undone.
     * @return The message indicating the task was marked as undone.
     * @throws InvalidTaskNumberException If the task number is invalid.
     */
    public static String unmarkTaskAsDone(int taskNum) throws InvalidTaskNumberException {
        taskArrayList.getTaskByIdx(taskNum - 1).markAsUndone();
        return Ui.markTaskAsUndoneMessage(taskArrayList.getTaskByIdx(taskNum - 1).toString());
    }

    /**
     * Deletes the specified task.
     *
     * @param requestedDeletion The number of the task to delete.
     * @return The message indicating the task was deleted.
     * @throws InvalidTaskNumberException If the task number is invalid.
     */
    public static String deleteTask(String requestedDeletion) throws InvalidTaskNumberException {
        try {
            int taskNum = Integer.parseInt(requestedDeletion);
            if (taskNum > taskArrayList.getLastIdx()) {
                throw new InvalidTaskNumberException(requestedDeletion);
            }
            String deletedTaskString = taskArrayList.getTaskByIdx(taskNum - 1).toString();
            storage.removeFromDataStore(taskNum - 1);
            taskArrayList.removeTask(taskNum - 1);
            storage = new Storage(FILEPATH);
            return Ui.taskRemovalMessage(deletedTaskString, taskArrayList.getLastIdx());
        } catch (NumberFormatException e) {
            throw new InvalidTaskNumberException(requestedDeletion);
        }
    }

    @Override
    public void start(Stage stage) {
        parser = new Parser();
        storage = new Storage(FILEPATH);
        taskArrayList = new TaskList(storage.readDataStore());
        scrollPane = new ScrollPane();
        dialogContainer = new VBox();
        scrollPane.setContent(dialogContainer);

        userInput = new TextField();
        sendButton = new Button("Send");

        AnchorPane mainLayout = new AnchorPane();
        mainLayout.getChildren().addAll(scrollPane, userInput, sendButton);

        scene = new Scene(mainLayout);

        stage.setTitle("BotChat");
        stage.setResizable(false);
        stage.setMinHeight(600.0);
        stage.setMinWidth(400.0);

        scrollPane.setPrefSize(385, 535);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);

        scrollPane.setVvalue(1.0);
        scrollPane.setFitToWidth(true);

        dialogContainer.setPrefHeight(Region.USE_COMPUTED_SIZE);
        dialogContainer.getChildren().addAll(
                DialogBox.getDukeDialog(new Label(Ui.hiMessage()), new ImageView(duke)));

        userInput.setPrefWidth(325.0);

        sendButton.setPrefWidth(55.0);

        AnchorPane.setTopAnchor(scrollPane, 1.0);

        AnchorPane.setBottomAnchor(sendButton, 1.0);
        AnchorPane.setRightAnchor(sendButton, 1.0);

        AnchorPane.setLeftAnchor(userInput , 1.0);
        AnchorPane.setBottomAnchor(userInput, 1.0);

        stage.setScene(scene);
        stage.show();

        sendButton.setOnMouseClicked((event) -> {
            dialogContainer.getChildren().add(getDialogLabel(userInput.getText()));
            userInput.clear();
        });

        userInput.setOnAction((event) -> {
            dialogContainer.getChildren().add(getDialogLabel(userInput.getText()));
            userInput.clear();
        });

        dialogContainer.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));

        sendButton.setOnMouseClicked((event) -> {
            handleUserInput();
        });

        userInput.setOnAction((event) -> {
            handleUserInput();
        });
    }

    private Label getDialogLabel(String text) {
        Label textToAdd = new Label(text);
        textToAdd.setWrapText(true);

        return textToAdd;
    }

    private void handleUserInput() {
        Label userText = new Label(userInput.getText());
        Label dukeText = new Label(getResponse(userInput.getText()));
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(userText, new ImageView(user)),
                DialogBox.getDukeDialog(dukeText, new ImageView(duke))
        );
        userInput.clear();
    }

    private String getResponse(String input) {
        return response(input);
    }

    public static void main(String[] args) {
        parser = new Parser();
        storage = new Storage(FILEPATH);
        taskArrayList = new TaskList(storage.readDataStore());
        System.out.println(Ui.hiMessage());

        Scanner userInput = new Scanner(System.in);

        while (!isTerminate) {
            String command = userInput.nextLine();
            System.out.println(response(command));
        }
    }
}
