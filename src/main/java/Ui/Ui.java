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
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Ui {

    final static String HORIZONTAL_LINE = "____________________________________________________________";
    final static String NAME = "Kewgy";

    private Scanner reader;
    private Label textLabel;
    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private Scene scene;
    private Image user;
    private Image duke;

    public TextField userInput;
    public Button sendButton;

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

        stage.setTitle(NAME);
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
    }
    
    public void addConversation(Label userText, Label dukeText) {
        dialogContainer.getChildren().addAll(
            DialogBox.getUserDialog(userText, new ImageView(user)),
            DialogBox.getDukeDialog(dukeText, new ImageView(duke))
        );
        userInput.clear();
    }

    public Scene getScene() {
        return scene;
    }

    public TextField getUserInput() {
        return userInput;
    }

    public Button getSendButton() {
        return sendButton;
    }

    public String[] getUserCommand() {
        return reader.nextLine().split(" ", 2);
    }

    public String printTaskKeyword(List<Task> userTaskList, String keyword) {
        String tasks = "";

        for (int i = 0; i < userTaskList.size(); i++) {
            if (userTaskList.get(i).getDescription().contains(keyword)) {
                tasks += i + ": " + userTaskList.get(i) + "\n";
            }
        }
        
        return this.formatString(
            "Here are the matching tasks in your list:\n" + 
            tasks + "\n"
        );
    }

    public String printGoodBye() {
        reader.close();

        return this.formatString("Bye! Hope to see you again soon!");
    }

    public String printUnknownCommand() {
        return this.formatString("Unknown Command!");
    }

    public String printDeleteTask(Task task, int taskSize) {
        return this.formatString(
            "Noted. I've removed this task:\n" + 
            task + "\n" +
            "Now you have " + taskSize + " tasks in your list."
        );
    }

    public String printAddTask(Task task, int taskSize) {
        return this.formatString(
            "Got it. I've added this task to your list.\n" +
            task + "\n" +
            "Now you have " + taskSize + " tasks in your list."
        );
    }

    public String printError(Exception e) {
        return this.formatString(e.getMessage());
    }

    public String markTask(List<Task> userTaskList, int taskInt) {
        return this.formatString(
            "Nice! I've marked this task as done:\n" +
            userTaskList.get(taskInt).toString() + "\n"
        );
    }

    public String printList(List<Task> taskList) {
        String tastListStr = "";

        for (int i = 1; i < taskList.size() + 1; i++) {
            tastListStr += i + ": " + taskList.get(i - 1) + "\n";
        }

        return this.formatString(
            "Here are the tasks in your list:\n" +
            tastListStr
        );
    }

    public String formatString(String msg) {
        return HORIZONTAL_LINE + "\n" + msg + "\n" + HORIZONTAL_LINE;
    }

    public void printIntro() {
        this.printDukeText("Hello from Kewgy!\nWhat can I do for you?\nType \"bye\" to exit!");
    }

    public void printDukeText(String msg) {
        Label dukeText = new Label(this.formatString(msg)); 
        dialogContainer.getChildren().addAll(
            DialogBox.getDukeDialog(dukeText, new ImageView(duke))
        );
    }
}
