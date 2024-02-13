package grumblebug;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.layout.Region;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.time.format.DateTimeParseException;


/**
 * Represents the overall chatbot object, with text input/output capabilities.
 */
public class GrumbleBug extends Application {

    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Image user = new Image(this.getClass().getResourceAsStream("/images/nailong.jpg"));
    private Image duke = new Image(this.getClass().getResourceAsStream("/images/smiley.jpg"));
    private Scene scene;
    private Storage storage;
    private TaskList taskList;
    private static Parser parserInput;
    public static String filePath = "./tasks.txt";
    public GrumbleBug() {
        this.storage = new Storage();
        this.taskList = new TaskList();
        this.parserInput = new Parser("yyyy-MM-dd");
    }

    /**
     * Iteration 2:
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    private void handleUserInput() {
        Label userText = new Label(userInput.getText());
        Label bugText = new Label(getResponse(userInput.getText()));
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(userText, new ImageView(user)),
                DialogBox.getBugDialog(bugText, new ImageView(duke))
        );
        userInput.clear();
    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    private String getResponse(String input) {
            if (input.equals("list")) { // show the list!
                return taskList.getTasks();
            } else if (input.startsWith("mark")) {
                String[] words = input.split(" ", 2);
                assert words.length < 3;
                try {
                    int i = Integer.parseInt(words[1]);
                    taskList.mark(i);
                    return "Ok it's marked. What else do you want...";
                } catch (NumberFormatException e) {
                    return "That was not understood. Silly.";
                }
            } else if (input.startsWith("unmark")) {
                String[] words = input.split(" ", 2);
                assert words.length < 3;
                try {
                    int i = Integer.parseInt(words[1]);
                    taskList.unmark(i);
                    return "Okay, it has been unmarked. Go away now.";
                } catch (NumberFormatException e) {
                    return "That was not understood. Silly.";
                }
            } else if (input.startsWith("find")) {
                String[] words = input.split(" ", 2);
                assert words.length < 3;
                return taskList.findMatches(words[1]);
            } else if (input.startsWith("todo")) { // add to list
                String[] words = input.split(" ", 2);
                assert words.length < 3;
                taskList.addToDo(words[1]);
                return "k";

            } else if (input.startsWith("deadline")) { // add to list
                String[] words = input.split(" ", 3);
                assert words.length < 4;
                try {
                    taskList.addDeadline(words[1], parserInput.parse(words[2]));
                    return "k";
                } catch (DateTimeParseException e) {
                    return "I don't get it. Date should be in yyyy-MM-dd format...";
                }

            } else if (input.startsWith("event")) { // add to list
                String[] words = input.split(" ", 4);
                assert words.length < 5;
                try {
                    taskList.addEvent(words[1], parserInput.parse(words[2]), parserInput.parse(words[3]));
                    return "k";
                } catch (DateTimeParseException e) {
                    return "I don't get it. Date should be in yyyy-MM-dd format...";
                }
            } else if (input.startsWith("delete")) {
                String[] words = input.split(" ", 2);
                assert words.length < 3;
                try {
                    int i = Integer.parseInt(words[1]);
                    taskList.delete(i);
                    return "Ok it's gone. What else do you want...";
                } catch (NumberFormatException e) {
                    return "That was not understood. Silly.";
                }
            } else {
                return "I don't understand what you just said...";
            }
    }

    /**
     * Runs the GrumbleBug chatbot interactions with the user, in a loop until it is terminated by user.
     */
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
        Scene scene = new Scene(mainLayout);

        stage.setScene(scene);
        stage.show();

        sendButton.setOnMouseClicked((event) -> {
            dialogContainer.getChildren().add(getDialogLabel(userInput.getText()));
            userInput.clear();
        });

        userInput.setOnAction((event) -> {
            dialogContainer.getChildren().add(getDialogLabel(userInput.getText()));
            userInput.clear();
        });        stage.setTitle("Duke");
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

        dialogContainer.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));

    }

    private Label getDialogLabel(String text) {
        // You will need to import `javafx.scene.control.Label`.
        Label textToAdd = new Label(text);
        textToAdd.setWrapText(true);

        return textToAdd;
    }
}