package chatbot;

import chatbot.action.Action;
import chatbot.action.ByeAction;
import chatbot.action.exception.ActionException;
import chatbot.parse.InputParser;
import chatbot.storage.LocalStorage;
import chatbot.task.TaskList;
import chatbot.ui.DialogBox;
import chatbot.ui.PrintFormatter;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * This encapsulates the behaviour of a chatbot,
 * which is the handling of the message content and executing commands.
 *
 * @author Titus Chew
 */
public class ChatBot extends Application {
    /** Stores the name of this. */
    private static final String CHATBOT_NAME = "Stratify";

    /** Stores the user's tasks. */
    private static final TaskList USER_TASK_LIST = LocalStorage.loadTaskList();
    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private AnchorPane mainLayout;
    private Button sendButton;

    /**
     * Setups the JavaFX application.
     * <p>
     * Reused from https://se-education.org/guides/tutorials/javaFx.html,
     * with minor modifications.
     */
    @Override
    public void start(Stage stage) {
        setUpStage(stage);
        formatWindow(stage);
        handleUserUpdate();
        greetUser();
    }

    /**
     * Sets up the required components used in the stage.
     *
     * @param stage the stage that contains the components
     */
    private void setUpStage(Stage stage) {
        // the container for the content of the chat to scroll
        scrollPane = new ScrollPane();
        dialogContainer = new VBox();
        scrollPane.setContent(dialogContainer);

        userInput = new TextField();
        sendButton = new Button("Send");

        mainLayout = new AnchorPane();
        mainLayout.getChildren().addAll(scrollPane, userInput, sendButton);

        Scene scene = new Scene(mainLayout);

        stage.setScene(scene);
        stage.show();
    }

    /**
     * Formats the window to look as expected.
     *
     * @param stage the stage that contains the components
     */
    private void formatWindow(Stage stage) {
        stage.setTitle(CHATBOT_NAME);
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

    /**
     * Handles updates from the user.
     */
    private void handleUserUpdate() {
        // add functionality to handle user input.
        sendButton.setOnMouseClicked((event) -> handleUserInput());

        userInput.setOnAction((event) -> handleUserInput());

        // scroll down to the end every time dialogContainer's height changes.
        dialogContainer.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     * <p>
     * Reused from https://se-education.org/guides/tutorials/javaFx.html.
     */
    private void handleUserInput() {
        String userText = userInput.getText();
        String dukeText = getResponse(userInput.getText());

        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(userText),
                DialogBox.getDukeDialog(dukeText)
        );

        userInput.clear();
    }

    /**
     * Gets the response given an input string from the user.
     * This will close the application if the command is to end the chat.
     * <p>
     * Reused with changes from https://se-education.org/guides/tutorials/javaFx.html.
     *
     * @param input the command line input
     * @return the response message
     */
    private String getResponse(String input) {
        try {
            Action userAction = InputParser.getParsedInput(input);

            if (userAction instanceof ByeAction) {
                Platform.exit();
            }

            return userAction.execute(USER_TASK_LIST);
        } catch (ActionException e) {
            return PrintFormatter.formatMessages(e.getMessage());
        }
    }

    /**
     * Greets the user when entering a session with this {@link ChatBot}.
     */
    private void greetUser() {
        String dukeText = PrintFormatter.formatMessages(
                "Hello! I'm " + CHATBOT_NAME + "!",
                "What can I do for you?"
        );

        dialogContainer.getChildren().addAll(
                DialogBox.getDukeDialog(dukeText)
        );
    }
}
