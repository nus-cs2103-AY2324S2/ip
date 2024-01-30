package Ui;

import java.util.List;
import java.util.Scanner;

import Tasks.Task;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.stage.Stage;

public class Ui {

    final static String HORIZONTAL_LINE = "____________________________________________________________";

    private Scanner reader;
    private Label textLabel;
    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;
    private Image user;
    private Image duke;


    public Ui(Stage stage) {
        this.reader = new Scanner(System.in);
        initScene(stage);
    }

    public void initScene(Stage stage) {
        scrollPane = new ScrollPane();
        dialogContainer = new VBox();
        scrollPane.setContent(dialogContainer);

        System.out.println(this.getClass().getResource("/").getPath());

        user = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
        duke = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));

        userInput = new TextField();
        sendButton = new Button("Send");
        textLabel = new Label("");

        AnchorPane mainLayout = new AnchorPane();
        mainLayout.getChildren().addAll(scrollPane, userInput, sendButton, textLabel);

        scene = new Scene(mainLayout);

        stage.setScene(scene);
        stage.show();

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

    public String[] getUserCommand() {
        return reader.nextLine().split(" ", 2);
    }

    public void printTaskKeyword(List<Task> userTaskList, String keyword) {
        System.out.println(HORIZONTAL_LINE);
        System.out.println("Here are the matching tasks in your list:");
        for (int i = 0; i < userTaskList.size(); i++) {
            if (userTaskList.get(i).getDescription().contains(keyword)) {
                System.out.println(i + ": " + userTaskList.get(i));
            }
        }
        System.out.println(HORIZONTAL_LINE);
    }

    public void printGoodBye() {
        this.printSingleLine("Bye! Hope to see you again soon!");

        reader.close();
    }

    public void printUnknownCommand() {
        this.printSingleLine("Unknown Command!");
    }

    public void printDeleteTask(Task task, int taskSize) {
        System.out.println(HORIZONTAL_LINE);
        System.out.println("Noted. I've removed this task:");
        System.out.println(task);
        System.out.println("Now you have " + taskSize + " tasks in your list.");
        System.out.println(HORIZONTAL_LINE);
    }

    public void printAddTask(Task task, int taskSize) {
        System.out.println(HORIZONTAL_LINE);
        System.out.println("Got it. I've added this task to your list.");
        System.out.println(task);
        System.out.println("Now you have " + taskSize + " tasks in your list.");
        System.out.println(HORIZONTAL_LINE);
    }

    public void printError(Exception e) {
        System.out.println(HORIZONTAL_LINE);
        System.out.println(e.getMessage());
        System.out.println(HORIZONTAL_LINE);
    }

    public void markTask(List<Task> userTaskList, int taskInt) {
        System.out.println(HORIZONTAL_LINE);
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(userTaskList.get(taskInt).toString());
        System.out.println(HORIZONTAL_LINE);
    }

    public void printList(List<Task> taskList) {
        System.out.println(HORIZONTAL_LINE);
        System.out.println("Here are the tasks in your list: ");
        for (int i = 1; i < taskList.size() + 1; i++) {
            System.out.println(i + ": " + taskList.get(i - 1));
        }
        System.out.println(HORIZONTAL_LINE);
    }

    public void printSingleLine(String msg) {
        System.out.println(HORIZONTAL_LINE);
        System.out.println(msg);
        System.out.println(HORIZONTAL_LINE);
    }

    public String formatSingleLine(String msg) {
        return HORIZONTAL_LINE + "\n" + msg + "\n" + HORIZONTAL_LINE;
    }

    public void printIntro() {
        // textLabel.setText(this.formatSingleLine("Hello from Kewgy!\nWhat can I do for you?\nType \"bye\" to exit!"));
    }
}
