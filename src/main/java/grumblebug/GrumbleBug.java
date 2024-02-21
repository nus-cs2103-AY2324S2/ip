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
    private Image grumbleBugImage = new Image(this.getClass().getResourceAsStream("/images/nailong.jpg"));
    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/smiley.jpg"));
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
     * Creates two dialog boxes, one echoing user input and the other containing
     * GrumbleBug's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    private void handleUserInput() {
        Label userText = new Label(userInput.getText());
        Label bugText = new Label(getResponse(userInput.getText()));
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(userText, new ImageView(userImage)),
                DialogBox.getBugDialog(bugText, new ImageView(grumbleBugImage)));
        userInput.clear();
    }

    /**
     * Parses user input and tells taskList to mark or unmark the corresponding
     * task,
     * if the input format is legible.
     * 
     * @param input    The user input into the chat.
     * @param doneness Whether to mark or unmark the task.
     * @return Confirmation message if successful or a scolding if input format was
     *         poor.
     */
    private String processMarkTaskInput(String input, boolean doneness) {
        String[] words = input.split(" ", NUM_PARAMS_FOR_MARK);
        assert words.length <= NUM_PARAMS_FOR_MARK;
        try {
            int i = Integer.parseInt(words[1]);
            if (doneness) {
                taskList.mark(i);
            } else {
                taskList.unmark(i);
            }
            return "Ok it's done. What else do you want...";
        } catch (NumberFormatException e) {
            return "That was not understood. Silly.";
        }
    }

    /**
     * Parses user input and tells taskList to find the tasks using the keyword.
     * 
     * @param input The user input into the chat.
     * @return The list of matching tasks as found by taskList.
     */
    private String processFindTasksInput(String input) {
        String[] words = input.split(" ", NUM_PARAMS_FOR_FIND);
        assert words.length <= NUM_PARAMS_FOR_FIND;
        return taskList.findMatches(words[1]);
    }

    /**
     * Parses user input and tells taskList to add the To-do as requested.
     * 
     * @param input The user input into the chat.
     * @return A confirmation message of the successful action.
     */
    private String processTodoInput(String input) {
        String[] words = input.split(" ", NUM_PARAMS_FOR_TODO);
        assert words.length <= NUM_PARAMS_FOR_TODO;
        taskList.addToDo(words[1]);
        return "k";
    }

    /**
     * Parses user input and, if input is in acceptable format,
     * activates taskList to add the corresponding Deadline as requested.
     * 
     * @param input The user input into the chat.
     * @return A confirmation message of the addition, or a scolding if input is
     *         improper.
     */
    private String processDeadlineInput(String input) {
        String[] words = input.split(" ", NUM_PARAMS_FOR_DEADLINE);
        assert words.length <= NUM_PARAMS_FOR_DEADLINE;
        try {
            taskList.addDeadline(words[1], parserForInput.parse(words[2]));
            return "k";
        } catch (DateTimeParseException e) {
            return "Ugh, I don't get it. Date should be in yyyy-MM-dd format...";
        }
    }

    /**
     * Parses user input and, if input is in acceptable format,
     * activates taskList to add the corresponding Event as requested.
     * 
     * @param input The user input into the chat.
     * @return A confirmation message of the addition, or a scolding if input is
     *         improper.
     */
    private String processEventInput(String input) {
        String[] words = input.split(" ", NUM_PARAMS_FOR_EVENT);
        assert words.length <= NUM_PARAMS_FOR_EVENT;
        try {
            taskList.addEvent(words[1], parserForInput.parse(words[2]), parserForInput.parse(words[3]));
            return "k";
        } catch (DateTimeParseException e) {
            return "Ugh, I don't get it. Date should be in yyyy-MM-dd format...";
        }
    }

    /**
     * Parses user input and, if input is in acceptable format,
     * activates taskList to delete the corresponding task as requested.
     * 
     * @param input The user input into the chat.
     * @return A confirmation message of deletion, or a scolding if input is
     *         improper.
     */
    private String processDeleteInput(String input) {
        String[] words = input.split(" ", 2);
        assert words.length < 3;
        try {
            int i = Integer.parseInt(words[1]);
            taskList.delete(i);
            return "Ok it's gone. What else do you want...";
        } catch (NumberFormatException e) {
            return "That was not understood. Silly.";
        }
    }

    /**
     * Overall handler for all kinds of user input to GrumbleBug.
     * Redirects into different helper methods to deal with different cases,
     * each generating a String response to be displayed back to the user.
     * If the input does not satisfy the set formats of input types,
     * GrumbleBug scolds the user for wasting his time.
     * 
     * @param input The user that is to be parsed and understood by GrumbleBug.
     * @return GrumbleBug's response back to the user.
     */
    private String getResponse(String input) {
        if (input.equals("list")) { // show the list!
            return taskList.getTasks();
        } else if (input.startsWith("mark")) {
            return processMarkTaskInput(input, true);
        } else if (input.startsWith("unmark")) {
            return processMarkTaskInput(input, false);
        } else if (input.startsWith("find")) {
            return processFindTasksInput(input);
        } else if (input.startsWith("todo")) { // add to list
            return processTodoInput(input);
        } else if (input.startsWith("deadline")) { // add to list
            return processDeadlineInput(input);
        } else if (input.startsWith("event")) { // add to list
            return processEventInput(input);
        } else if (input.startsWith("delete")) {
            return processDeleteInput(input);
        } else {
            return "I don't understand what you just said, stupid...";
        }
    }

    /**
     * Runs the GrumbleBug chatbot interactions with the user, in a loop until it is
     * terminated by user.
     */
    @Override
    public void start(Stage stage) {
        // The container for the content of the chat to scroll.
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
        });
        stage.setTitle("GrumbleBug");
        stage.setResizable(false);
        stage.setMinHeight(600.0);
        stage.setMinWidth(400.0);

        mainLayout.setPrefSize(400.0, 600.0);

        scrollPane.setPrefSize(385, 535);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);

        scrollPane.setVvalue(1.0);
        scrollPane.setFitToWidth(true);

        // You will need to import `javafx.scene.layout.Region` for this.
        dialogContainer.setPrefHeight(Region.USE_COMPUTED_SIZE);

        userInput.setPrefWidth(325.0);

        buttonToSendMessage.setPrefWidth(55.0);

        AnchorPane.setTopAnchor(scrollPane, 1.0);

        AnchorPane.setBottomAnchor(buttonToSendMessage, 1.0);
        AnchorPane.setRightAnchor(buttonToSendMessage, 1.0);

        AnchorPane.setLeftAnchor(userInput, 1.0);
        AnchorPane.setBottomAnchor(userInput, 1.0);

        // Part 3. Add functionality to handle user input.
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