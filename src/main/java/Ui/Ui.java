package Ui;

import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

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
    final static String DUKE_IMG = "/images/DaUser.png";
    final static String USER_IMG = "/images/DaDuke.png";
    final static String NAME = "Kewgy";

    private Scanner reader;
    private Label textLabel;
    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private Scene scene;
    private Image userImg;
    private Image dukeImg;

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

        userImg = new Image(this.getClass().getResourceAsStream(DUKE_IMG));
        dukeImg = new Image(this.getClass().getResourceAsStream(USER_IMG));

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
            DialogBox.getUserDialog(userText, new ImageView(userImg)),
            DialogBox.getDukeDialog(dukeText, new ImageView(dukeImg))
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
        String tasks = IntStream.range(0, userTaskList.size())
                                .filter(i -> userTaskList.get(i).getDescription().contains(keyword))
                                .mapToObj(i -> i + ": " + userTaskList.get(i))
                                .collect(Collectors.joining("\n"));
        
        return this.formatString("Here are the matching tasks in your list:\n" + tasks);
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

    public String printUpdateTime(Task task) {
        return this.formatString(
            "Nice! I've updated this task:\n" +
            task.toString()
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
        String taskListStr = IntStream.range(0, taskList.size())
                                      .mapToObj(i -> (i + 1) + ": " + taskList.get(i))
                                      .collect(Collectors.joining("\n"));

        return formatString("Here are the tasks in your list:\n" + taskListStr);
    }

    public String formatString(String... msg) {
        String outputText = "";

        for (String s: msg) {
            outputText += s + "\n";
        }

        return HORIZONTAL_LINE + "\n" + outputText + "\n" + HORIZONTAL_LINE;
    }

    public void printIntro() {
        this.printDukeText("Hello from Kewgy!\nWhat can I do for you?\nType \"bye\" to exit!");
    }

    public void printDukeText(String msg) {
        Label dukeText = new Label(this.formatString(msg)); 
        dialogContainer.getChildren().addAll(
            DialogBox.getDukeDialog(dukeText, new ImageView(dukeImg))
        );
    }
}
