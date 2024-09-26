package dwight;

import dwight.exceptions.DwightException;
import dwight.task.Task;
import dwight.util.Parser;
import dwight.util.Storage;
import dwight.task.TaskList;
import dwight.util.Ui;

import java.time.format.DateTimeParseException;
import java.io.IOException;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
public class Dwight extends Application {

    private Storage storage;
    private TaskList todo;
    private final Ui ui;
    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;

    private Image user = new Image(this.getClass().getResourceAsStream("/images/Dwight_Schrute.jpeg"));
    private Image duke = new Image(this.getClass().getResourceAsStream("/images/Michael_Scott.png"));

    // Duke Constructor
    public Dwight() {
        ui = new Ui();
        storage = new Storage("./data/duke.txt");
        try {
            todo = new TaskList(storage.readFile());
        } catch (IOException e) {
            ui.showLoadingError();
            todo = new TaskList();
        }
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

        // Step 2.
        stage.setTitle("Dwight");
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

        // Step 3.
        sendButton.setOnMouseClicked((event) -> {
            handleUserInput();
        });

        userInput.setOnAction((event) -> {
            handleUserInput();
        });

        //Scroll down to the end every time dialogContainer's height changes.
        dialogContainer.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));
        stage.setScene(scene);
        stage.show();
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
     * Iteration 2:
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    private void handleUserInput() {
        Label userText = new Label(userInput.getText());
        String responseString = getResponse(userInput.getText());

        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(userText, new ImageView(user)),
                DialogBox.getDukeDialog(new Label(responseString), new ImageView(duke))
        );
        userInput.clear();
    }

    private StringBuilder handleMark(int idx) {
        Task task;
        StringBuilder responseString = new StringBuilder();
        task = todo.mark(idx);
        responseString.append("\tNice! I've marked this task as done:");
        responseString.append(String.format("\t\t%s\n", task));
        return responseString;
    }

    private StringBuilder handleUnmark(int idx) {
        Task task;
        StringBuilder responseString = new StringBuilder();
        task = todo.unmark(idx);
        responseString.append("\tI've unmarked this task as done:");
        responseString.append(String.format("\t\t%s\n", task));
        return responseString;
    }

    private StringBuilder handleTodo(Task task) {
        StringBuilder responseString = new StringBuilder();
        todo.addTask(task);
        responseString.append("\tGot it. I've added this task:");
        responseString.append(String.format("\t\t%s\n", task));
        responseString.append(String.format("\tNow you have %d tasks in the list.\n", todo.size()));
        return responseString;
    }

    private StringBuilder handleDeadline(Task task) {
        StringBuilder responseString = new StringBuilder();
        todo.addTask(task);
        responseString.append("\tGot it. I've added this task:");
        responseString.append(String.format("\t\t%s\n", task));
        responseString.append(String.format("\tNow you have %d tasks in the list.\n", todo.size()));
        return responseString;
    }

    private StringBuilder handleEvent(Task task) {
        StringBuilder responseString = new StringBuilder();
        todo.addTask(task);
        responseString.append("\tGot it. I've added this task:");
        responseString.append(String.format("\t\t%s\n", task));
        responseString.append(String.format("\tNow you have %d tasks in the list.\n", todo.size()));
        return responseString;
    }


    private StringBuilder handleDelete(int idx) {
        StringBuilder responseString = new StringBuilder();
        responseString.append("\tNoted. I've removed this task:");
        responseString.append(String.format("\t\t%s\n", todo.deleteTask(idx)));
        responseString.append(String.format("\tNow you have %d tasks in the list.\n", todo.size()));
        return responseString;
    }

    private StringBuilder handleFind(String keyword) {
        StringBuilder responseString = new StringBuilder();
        for (int i = 0; i < todo.size(); i++) {
            if (todo.getList().get(i).getName().contains(keyword)) {
                responseString.append(String.format("\t%d. %s", i + 1, todo.getList().get(i)));
            }
        }
        responseString.append("\tHere are the matching tasks in your list:");
        return responseString;
    }


    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    private String getResponse(String input) {
        StringBuilder responseString = new StringBuilder();
        String command;
        String keyword;
        Task task;
        int idx;
        try {
            command = Parser.parse(input);
            switch (command) {
                case ("bye"):
                    responseString.append("\tBye. Hope to see you again soon!");
                    Platform.exit();
                    System.exit(0);
                case ("list"):
                    for (int i = 0; i < this.todo.getList().size(); i++) {
                        responseString.append(String.format("\t%d. %s", i + 1, this.todo.getList().get(i)));
                    }
                    break;
                case("mark"):
                    idx = Parser.parseMark(input);
                    responseString = handleMark(idx);
                    break;
                case("unmark"):
                    idx = Parser.parseUnmark(input);
                    assert idx != 0;
                    responseString = handleUnmark(idx);
                    break;
                case ("todo"):
                    task = Parser.parseTodo(input);
                    responseString = handleTodo(task);
                    break;
                case ("deadline"):
                    try {
                        task = Parser.parseDeadline(input);
                        responseString = handleDeadline(task);
                        break;
                    } catch (DateTimeParseException err) {
                        responseString.append("\tPlease write your data in d/m/yyyy T format");
                        break;
                    }
                case ("event"):
                    task = Parser.parseEvent(input);
                    responseString = handleEvent(task);
                    break;


                case("delete"):

                    idx = Parser.parseDelete(input);
                    assert idx != 0;
                    responseString = handleDelete(idx);
                    break;

                case("find"):

                    keyword = Parser.parseFind(input);
                    responseString = handleFind(keyword);
                    break;


                default:
                    throw new DwightException("\tSorry, I did not understand the command!");

            }

            storage.writeFile(todo.getList());
        } catch (DwightException | IOException err) {
            responseString.append(err.getMessage());

        }
        return responseString.toString();
    }
}
