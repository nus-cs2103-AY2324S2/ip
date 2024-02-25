package bob;

import java.util.Scanner;

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

public class Bob extends Application{
    private final Storage storage;
    private final TaskList tasks;
    private final Ui ui;
    private final Parser parser;
    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;
    private Image user = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image duke = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));

    /**
     * A constructor for the chatbot Bob.
     */
    public Bob(String filePath) {
        this.ui = new Ui();
        this.storage = new Storage(filePath);
        this.tasks = storage.loadFile();
        this.parser = new Parser(ui);
    }

    /**
     * A no argument constructor for launcher.
     */
    public Bob() {
        this.ui = new Ui();
        this.storage = new Storage("./data/tasks.txt");
        this.tasks = storage.loadFile();
        this.parser = new Parser(ui);
    }

    /**
     * A method that signals the chatbot to start its processes.
     */
    public void run() {
        ui.showGreetMessage();

        Scanner scanner = new Scanner(System.in);
        TaskList taskList = tasks;

        while (true) {
            String input = scanner.nextLine();

            if (input.equals("bye")) {
                parser.parseExit(storage, tasks);
                break;
            } else if (input.equals("list")) {
                parser.parseList(taskList);
            } else if (input.equals("clear")) {
                parser.parseClear(taskList);
            } else if (input.trim().matches("mark|unmark|deadline|todo|event|delete")) {
                ui.showIncompleteEntryMessage();
            } else if (input.startsWith("mark ")) {
                parser.parseMark(input, taskList);
            } else if (input.startsWith("unmark ")) {
                parser.parseUnmark(input, taskList);
            } else if (input.startsWith("deadline ")) {
                parser.parseDeadline(input, taskList);
            } else if (input.startsWith("todo ")) {
                parser.parseTodo(input, taskList);
            } else if (input.startsWith("event ")) {
                parser.parseEvent(input, taskList);
            } else if (input.startsWith("delete ")) {
                parser.parseDelete(input, taskList);
            } else if (input.startsWith("find ")) {
                parser.parseFind(input, taskList);
            } else {
                ui.showUnknownCommandMessage();
            }
        }

        scanner.close();
    }

    public static void main(String[] args) {
        new Bob("./data/tasks.txt").run();
    }

    @Override
    public void start(Stage stage) {
        //Step 1. Setting up required components

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

        //You will need to import `javafx.scene.layout.Region` for this.
        dialogContainer.setPrefHeight(Region.USE_COMPUTED_SIZE);

        userInput.setPrefWidth(325.0);

        sendButton.setPrefWidth(55.0);

        AnchorPane.setTopAnchor(scrollPane, 1.0);

        AnchorPane.setBottomAnchor(sendButton, 1.0);
        AnchorPane.setRightAnchor(sendButton, 1.0);

        AnchorPane.setLeftAnchor(userInput , 1.0);
        AnchorPane.setBottomAnchor(userInput, 1.0);

        //Part 3. Add functionality to handle user input.
        sendButton.setOnMouseClicked((event) -> {
            handleUserInput();
        });

        userInput.setOnAction((event) -> {
            handleUserInput();
        });

        //Scroll down to the end every time dialogContainer's height changes.
        dialogContainer.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));
    }

    /**
     * Iteration 1:
     * Creates a label with the specified text and adds it to the dialog container.
     * @param text String containing text to add
     * @return a label with the specified text that has word wrap enabled.
     */
    private Label getDialogLabel(String text) {
        Label textToAdd = new Label(text);
        textToAdd.setWrapText(true);

        return textToAdd;
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
                DialogBox.getUserDialog(userText.getText(), new ImageView(user).getImage()),
                DialogBox.getDukeDialog(dukeText.getText(), new ImageView(duke).getImage())
        );
        userInput.clear();
    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    String getResponse(String input) {
        ui.showGreetMessage();
        TaskList taskList = tasks;

        if (input.equals("bye")) {
            return parser.parseExit(storage, tasks);
        } else if (input.equals("list")) {
            return parser.parseList(taskList);
        } else if (input.equals("clear")) {
            return parser.parseClear(taskList);
        } else if (input.trim().matches("mark|unmark|deadline|todo|event|delete")) {
            return ui.showIncompleteEntryMessage();
        } else if (input.startsWith("mark ")) {
            return parser.parseMark(input, taskList);
        } else if (input.startsWith("unmark ")) {
            return parser.parseUnmark(input, taskList);
        } else if (input.startsWith("deadline ")) {
            return parser.parseDeadline(input, taskList);
        } else if (input.startsWith("todo ")) {
            return parser.parseTodo(input, taskList);
        } else if (input.startsWith("event ")) {
            return parser.parseEvent(input, taskList);
        } else if (input.startsWith("delete ")) {
            return parser.parseDelete(input, taskList);
        } else if (input.startsWith("find ")) {
            return parser.parseFind(input, taskList);
        } else {
            return ui.showUnknownCommandMessage();
        }
    }
}