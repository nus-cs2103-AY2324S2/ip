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
    private Button buttonToSendMessage;
    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/nailong.jpg"));
    private Image grumbleBugImage = new Image(this.getClass().getResourceAsStream("/images/smiley.jpg"));
    private TaskList taskList;
    private static Parser parserForInput;
    private final int NUM_PARAMS_FOR_MARK = 2;
    private final int NUM_PARAMS_FOR_UNMARK = 2;
    private final int NUM_PARAMS_FOR_FIND = 2;
    private final int NUM_PARAMS_FOR_TODO = 2;
    private final int NUM_PARAMS_FOR_DEADLINE = 3;
    private final int NUM_PARAMS_FOR_EVENT = 4;
    public static String filePath = "./tasks.txt";
    public GrumbleBug() {
        this.taskList = new TaskList();
        this.parserForInput = new Parser("yyyy-MM-dd");
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
                DialogBox.getUserDialog(userText, new ImageView(userImage)),
                DialogBox.getBugDialog(bugText, new ImageView(grumbleBugImage))
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
                String[] words = input.split(" ", NUM_PARAMS_FOR_MARK);
                assert words.length <= NUM_PARAMS_FOR_MARK;
                try {
                    int i = Integer.parseInt(words[1]);
                    taskList.mark(i);
                    return "Ok it's marked. What else do you want...";
                } catch (NumberFormatException e) {
                    return "That was not understood. Silly.";
                }
            } else if (input.startsWith("unmark")) {
                String[] words = input.split(" ", NUM_PARAMS_FOR_UNMARK);
                assert words.length <= NUM_PARAMS_FOR_UNMARK;
                try {
                    int i = Integer.parseInt(words[1]);
                    taskList.unmark(i);
                    return "Okay, it has been unmarked. Go away now.";
                } catch (NumberFormatException e) {
                    return "That was not understood. Silly.";
                }
            } else if (input.startsWith("find")) {
                String[] words = input.split(" ", NUM_PARAMS_FOR_FIND);
                assert words.length <= NUM_PARAMS_FOR_FIND;
                return taskList.findMatches(words[1]);
            } else if (input.startsWith("todo")) { // add to list
                String[] words = input.split(" ", NUM_PARAMS_FOR_TODO);
                assert words.length <= NUM_PARAMS_FOR_TODO;
                taskList.addToDo(words[1]);
                return "k";

            } else if (input.startsWith("deadline")) { // add to list
                String[] words = input.split(" ", NUM_PARAMS_FOR_DEADLINE);
                assert words.length <= NUM_PARAMS_FOR_DEADLINE;
                try {
                    taskList.addDeadline(words[1], parserForInput.parse(words[2]));
                    return "k";
                } catch (DateTimeParseException e) {
                    return "I don't get it. Date should be in yyyy-MM-dd format...";
                }

            } else if (input.startsWith("event")) { // add to list
                String[] words = input.split(" ", NUM_PARAMS_FOR_EVENT);
                assert words.length <= NUM_PARAMS_FOR_EVENT;
                try {
                    taskList.addEvent(words[1], parserForInput.parse(words[2]), parserForInput.parse(words[3]));
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
        buttonToSendMessage = new Button("Send");

        AnchorPane mainLayout = new AnchorPane();
        mainLayout.getChildren().addAll(scrollPane, userInput, buttonToSendMessage);
        Scene scene = new Scene(mainLayout);

        stage.setScene(scene);
        stage.show();

        buttonToSendMessage.setOnMouseClicked((event) -> {
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

        buttonToSendMessage.setPrefWidth(55.0);

        AnchorPane.setTopAnchor(scrollPane, 1.0);

        AnchorPane.setBottomAnchor(buttonToSendMessage, 1.0);
        AnchorPane.setRightAnchor(buttonToSendMessage, 1.0);

        AnchorPane.setLeftAnchor(userInput , 1.0);
        AnchorPane.setBottomAnchor(userInput, 1.0);

        //Part 3. Add functionality to handle user input.
        buttonToSendMessage.setOnMouseClicked((event) -> {
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