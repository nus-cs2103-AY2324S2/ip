package catchat;
import java.util.ArrayList;

import javafx.application.Application;
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
 * Main class
 */
public class CatChat extends Application {
    private final Storage storage;
    private final ArrayList<Task> taskList = new ArrayList<>();
    private final TaskList tasks;
    private final Parser parser;
    private final Ui ui;

    private ScrollPane scrollPane = new ScrollPane();
    private VBox dialogContainer = new VBox();
    private TextField userInput = new TextField();
    private Button sendButton = new Button("Send");
    private Scene scene;
    private AnchorPane mainLayout = new AnchorPane();

    private final Image userImg = new Image(this.getClass().getResourceAsStream("/images/PopCat.jpg"));
    private final Image dukeImg = new Image(this.getClass().getResourceAsStream("/images/HuhCat.jpg"));
    /**
     * Constructor for Duke
     */
    public CatChat() {
        this.ui = new Ui();
        this.storage = new Storage(taskList);
        storage.loadTaskList();
        this.tasks = new TaskList(storage, taskList);
        this.parser = new Parser(tasks);
    }

    /**
     * Starts the program
     *
     * @param stage
     * @throws Exception
     */
    @Override
    public void start(Stage stage) {
        //Step 1. Setting up required components
        setUpComponents(stage);

        //Step 2. Formatting the window to look as expected
        setStage(stage);
        setMainLayout();
        setScrollPane();

        setDialogHeight();
        setInputWidth();
        setSendButtonWidth();
        setAnchorPane();

        //Part 3. Add functionality to handle user input.
        setInputFunctionality();

        //Scroll down to the end every time dialogContainer's height changes.
        setAutoScroll();
    }

    /**
     * Sets the stage parameters for the GUI
     *
     * @param stage
     */
    private void setStage(Stage stage) {
        stage.setTitle("CatChat!");
        stage.setResizable(false);
        stage.setMinHeight(600.0);
        stage.setMinWidth(400.0);
    }

    /**
     * Sets the main layout parameters for the GUI
     */
    private void setMainLayout() {
        mainLayout.setPrefSize(400.0, 600.0);
    }

    /**
     * Sets the scroll pane parameters for the GUI
     */
    private void setScrollPane() {
        scrollPane.setPrefSize(385, 535);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);

        scrollPane.setVvalue(1.0);
        scrollPane.setFitToWidth(true);
    }

    /**
     * Sets the anchor pane parameters for the GUI
     */
    private void setAnchorPane() {
        AnchorPane.setTopAnchor(scrollPane, 1.0);

        AnchorPane.setBottomAnchor(sendButton, 1.0);
        AnchorPane.setRightAnchor(sendButton, 1.0);

        AnchorPane.setLeftAnchor(userInput , 1.0);
        AnchorPane.setBottomAnchor(userInput, 1.0);
    }

    /**
     * Sets the dialog container height
     */
    private void setDialogHeight() {
        dialogContainer.setPrefHeight(Region.USE_COMPUTED_SIZE);
    }

    /**
     * Sets the input width
     */
    private void setInputWidth() {
        userInput.setPrefWidth(325.0);
    }

    /**
     * Sets the send button width
     */
    private void setSendButtonWidth() {
        sendButton.setPrefWidth(55.0);
    }

    /**
     * Sets the functionality for the user input
     */
    private void setInputFunctionality() {
        sendButton.setOnMouseClicked((event) -> {
            handleUserInput();
        });

        userInput.setOnAction((event) -> {
            handleUserInput();
        });
    }

    /**
     * Sets the auto scroll functionality
     */
    private void setAutoScroll() {
        dialogContainer.heightProperty().addListener((observable) ->
                scrollPane.setVvalue(1.0));
        dialogContainer.getChildren().addAll(
                DialogBox.getDukeDialog(ui.showGreeting(), dukeImg)
        );
    }

    /**
     * Sets up the components for the GUI
     *
     * @param stage
     */
    private void setUpComponents(Stage stage) {
        scrollPane.setContent(dialogContainer);

        mainLayout.getChildren().addAll(scrollPane, userInput, sendButton);

        scene = new Scene(mainLayout);

        stage.setScene(scene);
        stage.show();
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing
     * Duke's reply and then appends them to the dialog container.
     * Clears the user input after processing.
     */
    private void handleUserInput() {
        if (dialogContainer.getChildren().isEmpty()) {
            dialogContainer.getChildren().add(
                    DialogBox.getDukeDialog(ui.showGreeting(), dukeImg));
        }

        String input = ui.getUserInput();
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImg),
                DialogBox.getDukeDialog(getResponse(input), dukeImg)
        );
        userInput.clear();
    }

    /**
     * Gets the response from the parser
     *
     * @param input
     */
    protected String getResponse(String input) {
        return parser.executeUserInput(input);
    }

    /**
     * Main method
     *
     * @param args
     */
    public static void main(String[] args) {
        CatChat mainApp = new CatChat();
    }
}
