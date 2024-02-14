package duke;

import duke.tasks.TaskList;
import duke.commands.Command;
import duke.commands.Parser;

import java.util.List;
import java.util.Scanner;

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

public class Duke extends Application {

    private static final String TASKS_CACHE_PATH = ".duke-cache";
    private static final String HORIZONTAL_LINE = "---------------------------------\n";

    private static TaskList tasks;
    private static Storage storage;

    private final Image user = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private final Image duke = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));

    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;

    @Override
    public void start (Stage stage) {

        //Step 1. Setting up required components
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
        stage.setTitle("Dino");
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

        dialogContainer.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));

        userInput.setPrefWidth(325.0);

        sendButton.setPrefWidth(55.0);

        AnchorPane.setTopAnchor(scrollPane, 1.0);

        AnchorPane.setBottomAnchor(sendButton, 1.0);
        AnchorPane.setRightAnchor(sendButton, 1.0);

        AnchorPane.setLeftAnchor(userInput, 1.0);
        AnchorPane.setBottomAnchor(userInput, 1.0);

        sendButton.setOnMouseClicked((event) -> handleUserInput());
        userInput.setOnAction((event) -> handleUserInput());
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
                new DialogBox(userText, new ImageView(user)),
                new DialogBox(dukeText, new ImageView(duke))
        );
        userInput.clear();
    }


    public static void main(String[] args) {

        storage = new Storage(TASKS_CACHE_PATH);
        try {
            tasks = storage.load();
        } catch (Exception e) {
            System.out.println("Issues occurred while loading tasks: " + e.getMessage());
        }

        greet();

        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();

        while(!input.equalsIgnoreCase("bye")) {
            System.out.println(HORIZONTAL_LINE);
            String messages = Duke.getResponse(input);
            System.out.println(messages);
            storage.save(tasks);
            System.out.println(HORIZONTAL_LINE);
            input = scanner.nextLine();
        }
        System.out.println("Bye. Hope to see you again soon!");
        scanner.close();
    }

    private static void greet() {
        String greet = "Hello! I'm Dino\n"
                + "What can I do for you?\n"
                + HORIZONTAL_LINE;
        System.out.println(greet);
    }

    /**
     * The function takes an input, processes it using a parser, executes a command on a list of tasks, saves the
     * tasks to storage, and returns a string representation of any messages generated during the process.
     * 
     * @param input A string representing the user's input command.
     * @return string message to be print out later.
     */
    public static String getResponse(String input) {
        try {
            Command command = Parser.processInput(input);
            List<String> messages = command.execute(tasks);
            storage.save(tasks);
            return String.join("\n", messages);
        } catch (Exception e) {
            return e.getMessage();
        }
    }
}
