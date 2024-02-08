package duke;

import duke.command.Parser;
import duke.command.Storage;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.TaskList;
import duke.task.Todo;
import duke.ui.Ui;

import java.util.Objects;
import java.util.Scanner;

import java.time.LocalDate;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.layout.Region;
import javafx.scene.control.Label;

/**
 * Represents the main Duke application class responsible for running the program.
 */
public class Duke extends Application {
    private Ui ui;
    private Storage storage;
    private TaskList tasks;

    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;

    /**
     * Constructs a Duke object with the specified file path.
     *
     * @param filePath The file path for storing task data.
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        tasks = new TaskList();
    }

    public Duke() {
        ui = new Ui();
        tasks = new TaskList();
    }

    @Override
    public void start(Stage stage) {

        //The container for the content of the chat to scroll.
        scrollPane = new ScrollPane();
        dialogContainer = new VBox();
        scrollPane.setContent(dialogContainer);

        userInput = new TextField();
        sendButton = new Button("Send");

        AnchorPane mainLayout = new AnchorPane();
        mainLayout.getChildren().addAll(scrollPane, userInput, sendButton);

        scene = new Scene(mainLayout);

        stage.setScene(scene);
        stage.show();

        // part 2
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

        dialogContainer.setPrefHeight(Region.USE_COMPUTED_SIZE);

        userInput.setPrefWidth(325.0);

        sendButton.setPrefWidth(55.0);

        AnchorPane.setTopAnchor(scrollPane, 1.0);

        AnchorPane.setBottomAnchor(sendButton, 1.0);
        AnchorPane.setRightAnchor(sendButton, 1.0);

        AnchorPane.setLeftAnchor(userInput , 1.0);
        AnchorPane.setBottomAnchor(userInput, 1.0);

        // part 3
        sendButton.setOnMouseClicked((event) -> {
            dialogContainer.getChildren().add(getDialogLabel(userInput.getText()));
            userInput.clear();
        });

        userInput.setOnAction((event) -> {
            dialogContainer.getChildren().add(getDialogLabel(userInput.getText()));
            userInput.clear();
        });

        dialogContainer.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));

    }

    /**
     * Iteration 1:
     * Creates a label with the specified text and adds it to the dialog container.
     * @param text String containing text to add
     * @return a label with the specified text that has word wrap enabled.
     */
    private Label getDialogLabel(String text) {
        // You will need to import `javafx.scene.control.Label`.
        Label textToAdd = new Label(text);
        textToAdd.setWrapText(true);

        return textToAdd;
    }

    /**
     * Runs the Duke application.
     */
    public void run() {

        storage.getFileContents(tasks);

        Scanner scanner = new Scanner(System.in);

        ui.showWelcomeMessage();

        while (true) {
            try {
                Task[] taskArr = tasks.getTasks();
                int counter = tasks.getCounter();

                String input = scanner.nextLine();

                ui.showMessage(input);

                Object[] parseRes = Parser.parseCommand(input);
                String type = (String) parseRes[0];

                if (parseRes.length == 1) {
                    if (Objects.equals(type, "bye")) {
                        ui.showByeMessage();
                        break;
                    }

                    if (Objects.equals(type, "list")) {
                        ui.showTaskList(taskArr, counter);
                        continue;
                    }
                }

                if (parseRes.length > 1) {
                    if (Objects.equals(type, "mark")) {
                        int taskNumber = (int) parseRes[1];
                        if (taskNumber >= 1 && taskNumber <= counter) {
                            tasks.markTaskAsDone(taskNumber);

                            ui.showMarkTaskDoneMessage(taskArr[taskNumber]);
                        } else {
                            ui.showMessage("UH OH! Invalid task number, " +
                                    "please provide a valid task number!");
                        }
                        continue;
                    }

                    if (Objects.equals(type, "delete")) {
                        int taskNumber = (int) parseRes[1];
                        if (taskNumber >= 1 && taskNumber <= counter) {
                            ui.showRemoveTaskMessage(taskArr[taskNumber - 1], counter);

                            tasks.deleteTask(counter - 1);
                        } else {
                            throw new DukeException("UH OH! Invalid task number, " +
                                    "please provide a valid task number!");
                        }
                        continue;
                    }

                    if (Objects.equals(type, "find")) {
                        String keyword = (String) parseRes[1];
                        Task[] tasksToPrint = tasks.searchKeyWord(keyword);
                        if (tasksToPrint[0] != null) {
                            ui.showTasksContainingKeyword(tasksToPrint);
                        } else {
                            throw new DukeException("UH OH! No tasks containing this keyword!");
                        }
                        continue;
                    }

                    if (Objects.equals(type, "todo")) {
                        String taskDesc = (String) parseRes[1];

                        // create new to do task and add to list of tasks
                        Task t = new Todo(taskDesc);
                        tasks.addTask(t);

                        ui.showAddTaskMessage(t, counter);

                    } else if (Objects.equals(type, "deadline")) {
                        String taskDesc = (String) parseRes[1];
                        LocalDate by = (LocalDate) parseRes[2];

                        // create new deadline task and add to list of tasks
                        Task t = new Deadline(taskDesc, by);
                        tasks.addTask(t);

                        ui.showAddTaskMessage(t, counter);

                    } else if (Objects.equals(type, "event")) {
                        String taskDesc = (String) parseRes[1];

                        LocalDate fromDate = (LocalDate) parseRes[2];
                        String fromTime = (String) parseRes[3];

                        LocalDate toDate = (LocalDate) parseRes[4];
                        String toTime = (String) parseRes[5];

                        // create new event task and add to list of tasks
                        Task t = new Event(taskDesc, fromDate, fromTime, toDate, toTime);
                        tasks.addTask(t);

                        ui.showAddTaskMessage(t, counter);

                    } else {
                        throw new DukeException("UH OH! I don't understand what you mean.. sorry D:");
                    }
                }

            } catch (DukeException e) {
                System.out.println(e.getMessage());
            }

        }

        // store tasks for this run into file in hard disk
        storage.writeToFile(tasks);
    }

    /**
     * The entry point for running the Duke application.
     *
     * @param args The command-line arguments.
     */
    public static void main(String[] args) {
        new Duke("./tasks.txt").run();
    }

}

