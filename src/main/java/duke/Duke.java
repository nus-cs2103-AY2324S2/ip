package duke;

import ui.Ui;

import storage.Storage;

import task.TaskList;
import task.Task;
import task.Deadline;
import task.Event;
import task.Todo;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import exception.DukeException;

import command.Command;

import parser.Parser;

public class Duke extends Application {
    private Ui ui;
    private Storage storage;
    private TaskList taskList;
    private String botName = "Yube";
    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;
    private Image user = new Image(this.getClass().getResourceAsStream("/images/human.png"));
    private Image duke = new Image(this.getClass().getResourceAsStream("/images/dog.png"));

    public Duke(String filePath) {
        this.ui = new Ui();
        this.storage = new Storage(filePath);
        try {
            this.taskList = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showLoadingError(e);
            this.taskList = new TaskList();
        }
    }

    /**
     * Main method.
     * 
     * @param args Command-line arguments.
     */
    public static void main(String[] args) {
        new Duke("./yube.txt").run();
    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    public String getResponse(String input) {
        return "Duke heard: " + input;
    }

    /**
     * Runs the Duke application.
     */
    public void run() {
        ui.showWelcomeMessage(botName);
        boolean hasEnded = true;
        while (hasEnded) {
            Task newTask;
            String[] parts;
            try {
                String input = ui.readLine();
                Command command = Parser.parseCommand(input);
                switch (command.getInputType()) {
                    case BYE:
                        ui.showExitMessage();
                        storage.writeArrayListToFile(taskList);
                        hasEnded = false;
                        break;
                    case TODO:
                        newTask = new Todo(input.substring(5));
                        taskList.addTask(newTask);
                        ui.showRepeatFunction(newTask, taskList);
                        break;
                    case LIST:
                        ui.printList(taskList);
                        break;
                    case DEADLINE:
                        parts = input.substring(9).split(" /");
                        newTask = new Deadline(parts[0], parts[1].substring(3));
                        taskList.addTask(newTask);
                        ui.showRepeatFunction(newTask, taskList);
                        break;
                    case EVENT:
                        parts = input.substring(6).split(" /");
                        newTask = new Event(parts[0], parts[1].substring(5),
                                parts[2].substring(3));
                        taskList.addTask(newTask);
                        ui.showRepeatFunction(newTask, taskList);
                        break;
                    case MARK:
                        parts = input.split(" ");
                        int index = Integer.parseInt(parts[1]);
                        Task task = taskList.getTask(index - 1);
                        task.setDone();
                        ui.showMark(task);
                        break;
                    case UNMARK:
                        parts = input.split(" ");
                        Task unmarkTask = taskList.getTask(Integer.parseInt(parts[1]) - 1);
                        unmarkTask.setNotDone();
                        ui.showUnmark(unmarkTask);
                        break;
                    case DELETE:
                        parts = input.split(" ");
                        int deleteIndex = Integer.parseInt(parts[1]) - 1;
                        Task deletedTask = taskList.getTask(deleteIndex);
                        taskList.removeTask(deleteIndex);
                        ui.deleteTask(deletedTask, taskList);
                        break;
                    case FIND:
                        String stringToFind = input.substring(5);
                        ui.findTask(taskList, stringToFind);
                        break;
                    case UNKNOWN:
                        throw new DukeException("Unknown input");
                }
            } catch (DukeException e) {
                ui.showErrorMessage(e.toString());
                hasEnded = false;
                break;
            }
        }
    }

    public Duke() {

    }

    @Override
    public void start(Stage stage) {

        scrollPane = new ScrollPane();
        dialogContainer = new VBox();
        scrollPane.setContent(dialogContainer);

        userInput = new TextField();
        sendButton = new Button("Send");

        AnchorPane mainLayout = new AnchorPane();
        mainLayout.getChildren().addAll(scrollPane, userInput, sendButton);

        scene = new Scene(mainLayout);

        stage.setTitle("Duke");
        stage.setResizable(false);
        stage.setMinHeight(600.0);
        stage.setMinWidth(400.0);

        mainLayout.setPrefSize(400.0, 600.0);

        scrollPane.setPrefSize(385, 535);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);

        scrollPane.setVvalue(1.0);
        scrollPane.setFitToWidth(true);

        // You will need to import `javafx.scene.layout.Region` for this.
        dialogContainer.setPrefHeight(Region.USE_COMPUTED_SIZE);

        userInput.setPrefWidth(325.0);

        sendButton.setPrefWidth(55.0);

        AnchorPane.setTopAnchor(scrollPane, 1.0);

        AnchorPane.setBottomAnchor(sendButton, 1.0);
        AnchorPane.setRightAnchor(sendButton, 1.0);

        AnchorPane.setLeftAnchor(userInput, 1.0);
        AnchorPane.setBottomAnchor(userInput, 1.0);

        sendButton.setOnMouseClicked((event) -> {
            dialogContainer.getChildren().add(getDialogLabel(userInput.getText()));
            userInput.clear();
        });

        userInput.setOnAction((event) -> {
            dialogContainer.getChildren().add(getDialogLabel(userInput.getText()));
            userInput.clear();
        });

        dialogContainer.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));

        stage.setScene(scene);
        stage.show();

    }

    /**
     * Iteration 1:
     * Creates a label with the specified text and adds it to the dialog container.
     * 
     * @param text String containing text to add
     * @return a label with the specified text that has word wrap enabled.
     */
    private Label getDialogLabel(String text) {
        // You will need to import `javafx.scene.control.Label`.
        Label textToAdd = new Label(text);
        textToAdd.setWrapText(true);

        return textToAdd;
    }

    public enum CommandType {
        TODO, DEADLINE, EVENT, LIST, MARK, UNMARK, DELETE, BYE, UNKNOWN, FIND
    }
}
