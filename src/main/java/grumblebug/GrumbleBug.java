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
    private Parser parser;
    private Storage storage;
    private final int NUM_PARAMS_FOR_MARK = 2;
    private final int NUM_PARAMS_FOR_FIND = 2;
    private final int NUM_PARAMS_FOR_TODO = 2;
    private final int NUM_PARAMS_FOR_DEADLINE = 3;
    private final int NUM_PARAMS_FOR_EVENT = 4;
    private final int NUM_PARAMS_FOR_DELETE = 2;

    private final String filePath = "./tasks.txt";

    public GrumbleBug() {
        this.taskList = new TaskList();
        this.parser = new Parser("yyyy-MM-dd");
        this.storage = new Storage();
        this.storage.loadFromFile(filePath, taskList);
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
        } else if (input.equals("save")) {
            return storage.writeToFile(filePath, taskList);
        } else if (input.startsWith("mark")) {
            return parser.processMarkTaskInput(input, true, NUM_PARAMS_FOR_MARK, taskList);
        } else if (input.startsWith("unmark")) {
            return parser.processMarkTaskInput(input, false, NUM_PARAMS_FOR_MARK, taskList);
        } else if (input.startsWith("find")) {
            return parser.processFindTasksInput(input, NUM_PARAMS_FOR_FIND, taskList);
        } else if (input.startsWith("todo")) { // add to list
            return parser.processTodoInput(input, NUM_PARAMS_FOR_TODO, taskList);
        } else if (input.startsWith("deadline")) { // add to list
            return parser.processDeadlineInput(input, NUM_PARAMS_FOR_DEADLINE, taskList);
        } else if (input.startsWith("event")) { // add to list
            return parser.processEventInput(input, NUM_PARAMS_FOR_EVENT, taskList);
        } else if (input.startsWith("delete")) {
            return parser.processDeleteInput(input, NUM_PARAMS_FOR_DELETE, taskList);
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
        Label textToAdd = new Label(text);
        textToAdd.setWrapText(true);

        return textToAdd;
    }
}