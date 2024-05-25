package seedu.mamta;
import java.io.FileNotFoundException;
import java.util.Objects;

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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.Node;
import java.util.List;
import java.util.ArrayList;


/**
 * Main class for the Mamta task manager application with UPDATED readme.
 */
public class Mamta extends Application  {
    private final Image user = new Image(Objects.requireNonNull(this.getClass().getResourceAsStream("/images/DaUser.png")));
    private final Image mamta = new Image(Objects.requireNonNull(this.getClass().getResourceAsStream("/images/DaMamta.png")));

    private final Image backgroundMamta = new Image(Objects.requireNonNull(this.getClass().getResourceAsStream("/images/mamtaBackground.jpeg")));

    /**
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
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    private void handleUserInput(TextField userInput, VBox dialogContainer) {
        Label userText = new Label(userInput.getText());
        Label dukeText = new Label(getResponse(userInput.getText()));
        List<Node> nodesToAdd = new ArrayList<>();
        nodesToAdd.add(DialogBox.getUserDialogs(userText, new ImageView(user)));
        nodesToAdd.add(DialogBox.getMamtaDialog(dukeText, new ImageView(mamta)));

        dialogContainer.getChildren().addAll(nodesToAdd);
        userInput.clear();
    }

    @Override
    public void start(Stage stage) {
        assert stage != null : "Stage is not initialized";
        //Step 1. Setting up required components

        //The container for the content of the chat to scroll.
        ScrollPane scrollPane = new ScrollPane();
        VBox dialogContainer = new VBox();
        scrollPane.setContent(dialogContainer);

        TextField userInput = new TextField();
        Button sendButton = new Button("Send");

        ImageView backgroundImageView = new ImageView(backgroundMamta);

        backgroundImageView.fitWidthProperty().bind(stage.widthProperty());
        backgroundImageView.fitHeightProperty().bind(stage.heightProperty());


        AnchorPane mainLayout = new AnchorPane();
        mainLayout.setStyle("-fx-background-color: lightblue;");

        mainLayout.getChildren().addAll(scrollPane, userInput, sendButton);

        Scene scene = new Scene(mainLayout);
        assert scene != null : "Scene is not initialized";

        stage.setTitle("Mamta Dum Dum");
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
        dialogContainer.getChildren().add(0, backgroundImageView);
        userInput.setPrefWidth(325.0);

        sendButton.setPrefWidth(55.0);

        AnchorPane.setTopAnchor(scrollPane, 1.0);

        AnchorPane.setBottomAnchor(sendButton, 1.0);
        AnchorPane.setRightAnchor(sendButton, 1.0);

        AnchorPane.setLeftAnchor(userInput , 1.0);
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

        dialogContainer.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));

        sendButton.setOnMouseClicked((event) -> {
            handleUserInput(userInput, dialogContainer);
        });

        userInput.setOnAction((event) -> {
            handleUserInput(userInput, dialogContainer);
        });


        stage.setScene(scene);
        stage.show();

        //More code to be added here later
    }




    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    String getResponse(String input) {
        return Parser.transformText(input);
    }

    public Mamta() {}


    /**
     * Generates a greeting message.
     * @return A greeting message.
     */
    public static String greet() {
        return "Hello! I'm Mamta\nWhat can I do for you?";
    }

    /**
     * Generates a farewell message.
     * @return A farewell message.
     */
    public static String exit() {
        return "Bye. Hope to see you again soon!";
    }

    /**
     * Echoes the input command based on its type and parameters. The type description follows.
     * @param taskType The type of the task.
     * @param command The command to be executed.
     * @param taskNum The task number (if applicable).
     * @param deadline The deadline (if applicable).
     * @param endTime The end time (if applicable).
     * @return A response based on the input command.
     */
    public static String echo(String taskType, String command, int taskNum, String deadline, String endTime) {
        String output = "";

        switch (command) {
        case "find":
            return String.format("------------------------------------------\nHere are the matching tasks in your list: \n%s\n------------------------------------------",  TaskList.searchOutput(taskType));
        case "bye":
            return String.format("------------------------------------------\n%s\n------------------------------------------", Mamta.exit());
        case "list":
            StringBuilder returnOutput = new StringBuilder();
            int count = 0;
            returnOutput.append("------------------------------------------\n");
            for (Task item : TaskList.getTasks()) {
                count += 1;
                returnOutput.append(String.format("%d. %s\n", count, TaskList.getTasks().get(count - 1)));
            }
            returnOutput.append("------------------------------------------");
            return returnOutput.toString();
        case "mark":
            TaskList.getTasks().set(taskNum - 1, TaskList.getTasks().get(taskNum - 1).markDone());
            return String.format("------------------------------------------\nNice! I've marked this task as done\n%s\n------------------------------------------", TaskList.getTasks().get(taskNum - 1));
        case "unmark":
            TaskList.getTasks().set(taskNum - 1, TaskList.getTasks().get(taskNum - 1).unmarkTask());
            return String.format("------------------------------------------\nOK, I've marked this task as not done yet:\n%s\n------------------------------------------", TaskList.getTasks().get(taskNum - 1));
        case "delete":
            Task objToRemove = TaskList.getTasks().get(taskNum - 1);
            TaskList.removeTask(objToRemove);
            return String.format("------------------------------------------\nNoted. I've removed this task:\n%s\nNow you have %d tasks in the list\n------------------------------------------", objToRemove, TaskList.getTasks().size());
        default:
            //handle case where there is no command
            if (taskType.equals("todo")) {
                Todo myTodo = new Todo(command);
                if (!command.isEmpty()) {
                    TaskList.addTask(myTodo);
                    output = String.format("------------------------------------------\nGot it. I've added this task: \n%s\nNow you have %d tasks in the list\n------------------------------------------", myTodo, TaskList.getTasks().size());
                } else {
                    output = String.valueOf(MamtaException.incompleteTaskDescription());
                }
            } else if (taskType.equals("deadline")) {
                Deadline myDead = new Deadline(command, deadline);
                if (!command.isEmpty()) {
                    TaskList.addTask(myDead);
                    output = String.format("------------------------------------------\nGot it. I've added this task: \n%s\nNow you have %d tasks in the list\n------------------------------------------", myDead, TaskList.getTasks().size());
                } else {
                    output = String.valueOf(MamtaException.incompleteTaskDescription());
                }
            } else if (taskType.equals("event")) {
                Event myEve = new Event(command, deadline, endTime);
                if (!command.isEmpty()) {
                    TaskList.addTask(myEve);
                    output = String.format("------------------------------------------\nGot it. I've added this task: \n%s\nNow you have %d tasks in the list\n------------------------------------------", myEve, TaskList.getTasks().size());
                } else {
                    assert MamtaException.incompleteTaskDescription() != null : "Incomplete task description message is empty";
                    output = String.valueOf(MamtaException.incompleteTaskDescription());
                }
            } else {
                //handling the default case where its not a tracked task type, throw an errors
                output = String.format("------------------------------------------\n%s\n------------------------------------------", MamtaException.invalidTaskType());
            }
            break;
        }
        return output;

    }

    /**
     * Runs the Mamta application bot. Handles file input logic and save to file.
     * @param loadTasksFile The file path for loading tasks.
     * @param inputNewTasksFile The file path for inputting new tasks.
     */
    public static void run(String loadTasksFile, String inputNewTasksFile) {
        System.out.println(Ui.printBotLogo());
        Ui.loadTasksFromFile(loadTasksFile);
        Ui.handleInputFiles(inputNewTasksFile);
        Ui.callSaveTasksIntoFile(loadTasksFile);

    }

    public static void main(String[] args) throws FileNotFoundException {
        Mamta.run("./data/mamtainput.txt", "./text-ui-test/input.txt");
    }
}
