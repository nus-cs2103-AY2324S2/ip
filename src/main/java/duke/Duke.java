package duke;

import java.io.IOException;
import java.util.Objects;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * Represents the main class of the Duke program.
 */
public class Duke extends Application {
    private final Storage storage;
    private TaskList tasks;
    private final Ui ui;

    private final Image user =
            new Image(Objects.requireNonNull(this.getClass().getResourceAsStream("/images/DaUser.png")));
    private final Image duke =
            new Image(Objects.requireNonNull(this.getClass().getResourceAsStream("/images/DaDuke.png")));
    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;

    /**
     * Constructor for Duke.
     */
    public Duke() {
        ui = new Ui();
        storage = new Storage("./data/duke.txt");
        try {
            tasks = new TaskList(storage.loadTasks());
        } catch (IOException e) {
            Ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    /**
     * Runs the Duke program.
     * @throws DukeException If there is an error running the program.
     */
    public void run() throws DukeException {
        boolean isExit = false;
        while (!isExit) {
            String userInput = ui.readInput();
            assert userInput != null;
            String result = Parser.parse(userInput, tasks, ui);
            assert result != null;
            if (result.equals("1")) {
                isExit = true;
                ui.closeScanner();
            }
        }
    }

    /**
     * Main method to run the Duke program.
     * @param args The arguments to run the program.
     * @throws DukeException If there is an error running the program.
     */
    public static void main(String[] args) throws DukeException {
        new Duke().run();
    }

    /**
     * Starts the Duke program.
     * @param stage The stage to start the program.
     */
    @Override
    public void start(Stage stage) {
        initializeLayout(stage);
        configureScrollPane();
        configureUserInput();
        configureSendButton();
        greetUser();
        configureEventHandlers();
    }

    private void initializeLayout(Stage stage) {
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

        //Step 2. Formatting the window to look as expected
        stage.setTitle("Duke");
        stage.setResizable(false);
        stage.setMinHeight(600.0);
        stage.setMinWidth(400.0);

        mainLayout.setPrefSize(400.0, 600.0);
    }

    private void configureScrollPane() {
        scrollPane.setPrefSize(385, 535);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);

        scrollPane.setVvalue(1.0);
        scrollPane.setFitToWidth(true);

        dialogContainer.setPrefHeight(Region.USE_COMPUTED_SIZE);

        //Scroll down to the end every time dialogContainer's height changes.
        dialogContainer.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));
    }

    private void configureUserInput() {
        userInput.setPrefWidth(325.0);
    }

    private void configureSendButton() {
        sendButton.setPrefWidth(55.0);

        AnchorPane.setTopAnchor(scrollPane, 1.0);
        AnchorPane.setBottomAnchor(sendButton, 1.0);
        AnchorPane.setRightAnchor(sendButton, 1.0);
        AnchorPane.setLeftAnchor(userInput , 1.0);
        AnchorPane.setBottomAnchor(userInput, 1.0);
    }

    private void greetUser() {
        dialogContainer.getChildren().add(
                DialogBox.getDukeDialog(ui.welcomeMessage(), duke)
        );
    }

    private void configureEventHandlers() {
        //Step 3. Add functionality to handle user input.
        sendButton.setOnMouseClicked((event) -> handleUserInput());
        userInput.setOnAction((event) -> handleUserInput());
    }

    /**
     * Iteration 1:
     * Creates a label with the specified text and adds it to the dialog container.
     * @param text String containing text to add
     * @return a label with the specified text that has word wrap enabled.
     */
    private Label getDialogLabel(String text) {
        Label textToAdd = new Label(text);
        textToAdd.setWrapText(true);

        return textToAdd;
    }

    /**
     * Iteration 2:
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    private void handleUserInput() {
        String userText = userInput.getText();
        String dukeText = getResponse(userInput.getText());
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(userText, user),
                DialogBox.getDukeDialog(dukeText, duke)
        );
        userInput.clear();
    }

    /**
     * Gets the response from Duke based on the user input.
     * @param input User input.
     * @return Duke's response based on the user input.
     */
    String getResponse(String input) {
        try {
            String result = Parser.parse(input, tasks, ui);
            if (result != null) {
                if (result.equals("     Bye. Hope to see you again soon!")) {
                    storage.saveTasksToFile(TaskList.getTasks(), TaskList.getTaskNum());
                    ui.closeScanner();
                    // Wait for 1.5 seconds before exiting the platform
                    new Thread(() -> {
                        try {
                            Thread.sleep(1500);
                            Platform.exit();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }).start();
                }
                return result;
            }
        } catch (DukeException e) {
            return e.getMessage();
        }
        return null;
    }
}
