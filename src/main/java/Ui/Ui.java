package ui;

import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

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
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import tasks.Task;

/**
 * UI class to handle JavaFX and output
 * TODO: Abstract down class further
 */
public class Ui {

    final static String KEWGY_IMG = "/images/kewgy.png";
    final static String USER_IMG = "/images/user.png";
    final static String NAME = "Kewgy";

    private Scanner reader;
    private Label textLabel;
    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private Scene scene;
    private Image userImg;
    private Image kewgyImg;

    public TextField userInput;
    public Button sendButton;

    public Ui(Stage stage) {
        this.reader = new Scanner(System.in);
        initScene(stage);
    }

    /**
     * Setting up JavaFX scene
     * @param stage
     */
    public void initScene(Stage stage) {
        scrollPane = new ScrollPane();
        dialogContainer = new VBox();
        scrollPane.setContent(dialogContainer);

        System.out.println(this.getClass().getResource("/").getPath());

        userImg = new Image(this.getClass().getResourceAsStream(USER_IMG));
        kewgyImg = new Image(this.getClass().getResourceAsStream(KEWGY_IMG));

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
        stage.setMinWidth(500.0);
        stage.getIcons().add(kewgyImg);

        mainLayout.setPrefSize(500.0, 600.0);

        scrollPane.setPrefSize(485, 535);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);

        scrollPane.setVvalue(1.0);
        scrollPane.setFitToWidth(true);

        dialogContainer.setPrefHeight(Region.USE_COMPUTED_SIZE);

        userInput.setPrefWidth(425.0);

        sendButton.setPrefWidth(55.0);

        AnchorPane.setTopAnchor(scrollPane, 1.0);

        AnchorPane.setBottomAnchor(sendButton, 1.0);
        AnchorPane.setRightAnchor(sendButton, 1.0);

        AnchorPane.setLeftAnchor(userInput , 1.0);
        AnchorPane.setBottomAnchor(userInput, 1.0);
    }
    
    /**
     * Handling input / output of chatbot
     */
    public void addConversation(Label userText, Label kewgyText) {
        dialogContainer.getChildren().addAll(
            DialogBox.getUserDialog(userText, new ImageView(userImg)),
            DialogBox.getKewgyDialog(kewgyText, new ImageView(kewgyImg))
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

    /**
     * Helper functions for chatbot
     */
    public String[] getUserCommand() {
        return reader.nextLine().split(" ", 2);
    }

    public Label printTaskKeyword(List<Task> userTaskList, String keyword) {
        String tasks = IntStream.range(0, userTaskList.size())
                                .filter(i -> userTaskList.get(i).getDescription().contains(keyword))
                                .mapToObj(i -> i + ": " + userTaskList.get(i))
                                .collect(Collectors.joining("\n"));
        
        return this.formatString("Here are the matching tasks in your list:\n" + tasks);
    }

    public Label printGoodBye() {
        reader.close();

        return this.formatString("Bye! Hope to see you again soon!");
    }

    public Label printDeleteTask(Task task, int taskSize) {
        return this.formatString(
            "Noted. I've removed this task:\n" + 
            task + "\n" +
            "Now you have " + taskSize + " tasks in your list."
        );
    }

    public Label printUpdateTime(Task task) {
        return this.formatString(
            "Nice! I've updated this task:\n" +
            task.toString()
        );
    }

    public Label printAddTask(Task task, int taskSize) {
        return this.formatString(
            "Got it. I've added this task to your list.\n" +
            task + "\n" +
            "Now you have " + taskSize + " tasks in your list."
        );
    }

    public Label printError(String e) {
        Label errorLabel = this.formatString("Exception occured!\n" + e);
        errorLabel.setTextFill(Color.color(1, 0, 0));

        return errorLabel;
    }

    public Label markTask(List<Task> userTaskList, int taskInt) {
        return this.formatString(
            "Nice! I've marked this task as done:\n" +
            userTaskList.get(taskInt).toString() + "\n"
        );
    }

    public Label printList(List<Task> taskList) {
        String taskListStr = IntStream.range(0, taskList.size())
                                      .mapToObj(i -> (i + 1) + ": " + taskList.get(i))
                                      .collect(Collectors.joining("\n"));

        return formatString("Here are the tasks in your list:\n" + taskListStr);
    }

    public Label formatString(String... msg) {
        String outputText = "";

        for (String s: msg) {
            outputText += s + "\n";
        }

        Label formattedLabel = new Label(outputText);

        return formattedLabel;
    }

    public void printIntro() {
        this.printKewgyText("Hello from Kewgy!\nWhat can I do for you?\nType \"bye\" to exit!");
    }

    public void printKewgyText(String msg) {
        Label kewgyText = this.formatString(msg); 
        dialogContainer.getChildren().addAll(
            DialogBox.getKewgyDialog(kewgyText, new ImageView(kewgyImg))
        );
    }
}
