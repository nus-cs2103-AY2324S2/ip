package academicweapon;

import academicweapon.action.Action;
import academicweapon.exceptions.DukeExceptions;
import academicweapon.parser.Parser;
import academicweapon.storage.Storage;
import academicweapon.task.Deadline;
import academicweapon.task.Event;
import academicweapon.task.Task;
import academicweapon.task.TaskList;
import academicweapon.task.Todo;
import academicweapon.ui.Ui;

import java.io.IOException;
import java.util.ArrayList;
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
 * Duke is a task management application that allows users to interact with tasks through a command-line interface.
 * It supports various command for managing tasks such as adding, listing, marking and deleting tasks.
 */
public class Duke extends Application {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;
    private Image user = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image duke = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));

    /**
     * Constructs a Duke object with the specified file path for storage
     */
    public Duke() {
        String filePath = "./src/main/data/acadList.txt";
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeExceptions e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    @Override
    public void start(Stage stage) {
        //Step 1. Setting up required components

        // The container for the content of the chat to scroll.
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

        //Step 2. Formatting the window to look as expected
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

        AnchorPane.setLeftAnchor(userInput, 1.0);
        AnchorPane.setBottomAnchor(userInput, 1.0);

        //Step 3. Add functionality to handle user input.
        sendButton.setOnMouseClicked((event) -> {
            dialogContainer.getChildren().add(getDialogLabel(userInput.getText()));
            userInput.clear();
        });

        userInput.setOnAction((event) -> {
            dialogContainer.getChildren().add(getDialogLabel(userInput.getText()));
            userInput.clear();
        });

        //Scroll down to the end every time dialogContainer's height changes.
        dialogContainer.heightProperty().addListener((observable -> scrollPane.setVvalue(1.0)));

        //Part 3. Add functionality to handle user input.
        sendButton.setOnMouseClicked((event) -> {
            handleUserInput();
        });

        userInput.setOnAction((event) -> {
            handleUserInput();
        });
    }

    /**
     * Iteration 2:
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    private void handleUserInput() {
        Label userText = new Label(userInput.getText());
        Label dukeText = new Label(getResponse(userInput.getText()));
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(userText, new ImageView(user)),
                DialogBox.getDukeDialog(dukeText, new ImageView(duke))
        );
        userInput.clear();
    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    private String getResponse(String input) {
        return "Duke heard: " + input;
    }

    /**
     * Iteration 1:
     * Creates a label with the specified text and adds it to the dialog container.
     * @param text String containing text to add
     * @return a label with the specified text that has word wrap enabled
     */
    private Label getDialogLabel(String text) {
        Label textToAdd = new Label(text);
        textToAdd.setWrapText(true);

        return textToAdd;
    }

    /**
     * Runs the duke application, handling user interactions through a command-line interface.
     * It processes user commands, updates tasks, and performs necessary  actions based on the commands.
     */
    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            String fullCommand = "";
            try {
                fullCommand = ui.readCommand();
            } catch (IOException e) {
                System.out.println(e.getMessage());
                continue;
            } catch (DukeExceptions e) {
                System.out.println(e.getMessage());
                continue;
            }

            ui.showLine();
            ArrayList<String> actions = new ArrayList<>();

            try {
                actions = Parser.parse(fullCommand);
            } catch (DukeExceptions e) {
                System.out.println(e.getMessage());
                continue;
            }

            Action action = Action.valueOf(actions.get(0));

            switch (action) {
            case FIND:
                ArrayList<String> strLst = this.tasks.findKeyword(actions.get(1));
                if (strLst.isEmpty()) {
                    System.out.println("No tasks containing this keyword");
                } else {
                    for (String str: strLst) {
                        System.out.println(str);
                    }
                }
                break;
            case LIST:
                this.tasks.showList();
                break;
            case MARK:
                int indexToMark = Integer.parseInt(actions.get(1));
                Task taskToMark = this.tasks.getTask(indexToMark);
                taskToMark.markAsDone();
                this.ui.printMarkDone(taskToMark);
                break;
            case UNMARK:
                int indexToUnmark = Integer.parseInt(actions.get(1));
                Task taskToUnmark = this.tasks.getTask(indexToUnmark);
                taskToUnmark.markAsNotDone();
                this.ui.printUnmark(taskToUnmark);
                break;
            case TODO:
                Todo addTodo = new Todo(actions.get(1));
                this.tasks.addTask(addTodo);
                this.ui.printAddSuccessful(addTodo, this.tasks.getSize());
                break;
            case DEADLINE:
                Deadline addDeadline;
                try {
                    addDeadline = new Deadline(actions.get(1), actions.get(2));
                } catch (DukeExceptions e) {
                    System.out.println(e.getMessage());
                    continue;
                }

                this.tasks.addTask(addDeadline);
                this.ui.printAddSuccessful(addDeadline, this.tasks.getSize());
                break;
            case EVENT:
                Event addEvent = new Event(actions.get(1), actions.get(2), actions.get(3));
                this.tasks.addTask(addEvent);
                this.ui.printAddSuccessful(addEvent, this.tasks.getSize());
                break;
            case DELETE:
                int index = Integer.parseInt(actions.get(1));
                Task toBeRemoved = this.tasks.removeTask(index);
                this.ui.removeSuccessful(toBeRemoved, this.tasks.getSize());
                break;
            case BYE:
                isExit = true;
                this.storage.saveFile(this.tasks.getList());
                this.ui.showBye();
                break;
            default:
                System.out.println("Sorry. I dont understand your command");
            }
        }
    }

//    /**
//     * Main method to start the Duke application
//     *
//     * @param args Command-line arguments (not used)
//     */
//    public static void main(String[] args) {
//        new Duke("./src/main/data/acadList.txt").run();
//    }
}
