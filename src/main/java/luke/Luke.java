package luke;

import java.util.Objects;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * The main class for the Luke chatbot application.
 * <p>
 * This class extends the JavaFX {@code Application} class and serves as the entry point for the Luke chatbot GUI.
 * It initializes the UI components and handles user input.
 * </p>
 * <p>
 * The conversation between the user and Luke is displayed in a scrollable dialog container.
 * User input is processed, and Luke's response is displayed in dialog boxes within the container.
 * </p>
 * <p>
 * This class also loads task data from a storage file and initializes the {@code Ui} object to handle user
 * interactions.
 * </p>
 */
public class Luke extends Application {
    private Ui ui;

    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;

    private final Image userImage =
            new Image(Objects.requireNonNull(this.getClass().getResourceAsStream("/images/DaUser.png")));
    private final Image lukeImage =
            new Image(Objects.requireNonNull(this.getClass().getResourceAsStream("/images/DaLuke.png")));

    /**
     * Constructs a new instance of the Luke chatbot application.
     * <p>
     * Initializes the storage, task list, and user interface components.
     * If loading task data from storage fails, it initializes an empty task list and displays a loading error message.
     * </p>
     */
    public Luke() {
        Storage storage = new Storage("./tasks.txt");
        TaskList taskList;
        try {
            taskList = new TaskList(storage.loadFile());
            ui = new Ui(taskList);
        } catch (LukeException e) {
            ui = new Ui();
        }
    }

    @Override
    public void start(Stage stage) {
        //Step 1. Setting up required components

        //The container for the content of the chat to scroll.
        scrollPane = new ScrollPane();
        dialogContainer = new VBox();
        scrollPane.setContent(dialogContainer);

        userInput = new TextField();
        Button sendButton = new Button("Send");

        AnchorPane mainLayout = new AnchorPane();
        mainLayout.getChildren().addAll(scrollPane, userInput, sendButton);

        Scene scene = new Scene(mainLayout);

        stage.setScene(scene);
        stage.show();

        //Step 2. Formatting the window to look as expected
        stage.setTitle("Luke");
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

        //Scroll down to the end every time dialogContainer's height changes.
        dialogContainer.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));

        //Initialize the conversation.

        String lukeText = ui.welcome();
        dialogContainer.getChildren().addAll(
                DialogBox.getLukeDialog(lukeText, lukeImage)
        );
    }

    /**
     * Iteration 2:
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    private void handleUserInput() {
        String userText = userInput.getText();
        String lukeText = getResponse(userInput.getText());

        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(userText, userImage),
                DialogBox.getLukeDialog(lukeText, lukeImage)
        );

        userInput.clear();

        if (lukeText.equals("File saved. Hope to see you again soon!")) {
            Platform.exit();
        }
    }

    String getResponse(String input) {
        return ui.handleInput(input);
    }
}
