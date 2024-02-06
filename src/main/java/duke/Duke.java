package duke;

import duke.parser.InputException;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

import java.io.FileNotFoundException;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * Represents task tracking bot.
 */
public class Duke extends Application {
    private Ui ui = null;
    private Parser parser = null;
    private TaskList taskList = null;
    private Storage storage = null;

    // for GUI
    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;


    private enum LivState {
        ACTIVE,
        INACTIVE
    }
    private static Duke instance = null;
    private LivState currentState = null;

    public Duke() {
        // break the initialisation into the initialization function of different classes
        currentState = LivState.INACTIVE;

        // additional line of code to accommodate the need to have a publicly available constructor
        instance = this;
    }

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

        scene = new Scene(mainLayout);

        stage.setScene(scene);
        stage.show();

        //Step 2. Formatting the window to look as expected
        stage.setTitle("Duke");
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

        //Step 3. Add functionality to handle user input.
        sendButton.setOnMouseClicked((event) -> {
            dialogContainer.getChildren().add(getDialogLabel(userInput.getText()));
            userInput.clear();
        });

        userInput.setOnAction((event) -> {
            dialogContainer.getChildren().add(getDialogLabel(userInput.getText()));
            userInput.clear();
        });

        //Scroll down to the end every time dialogContainer's height changes.
        dialogContainer.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));
    }

    /**
     * Iteration 1:
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
     * Starts Duke.
     * Initialises ui, parser, taskList and storage.
     * Loads taskList saved locally if any.
     * Creates a Data directory to host the data file if local taskList not found.
     * Starts listening to user input.
     */
    private void instanceStart() {
        // initialize duke.ui.Ui
        ui = Ui.getInstance();
        ui.initUi();

        // initialize parser
        parser = Parser.getInstance();
        parser.initParser();

        // initialize taskList
        taskList = TaskList.getInstance();
        taskList.initTaskList();

        // initialize storage
        storage = Storage.getInstance();
        storage.initStorage();

        try {
            storage.loadFromMemory();
        } catch (FileNotFoundException e) {
            System.out.println("No previous task file found");
            storage.createDataFile();
        }

        instance.ToggleActiveState();

        while (isActive()) {
            // should start the cycle talking
            String userInput = ui.StartListening();
            try{
                parser.ProcessInput(userInput);
            } catch (InputException e) {
                ui.speak(e.getMessage());
            }
        }
    }

    public boolean isActive() {
        return currentState == LivState.ACTIVE;
    }
    public static void main(String[] args) {
        getInstance().instanceStart();
    }

    /**
     * Toggles active state of Duke.
     */
    public void ToggleActiveState() {
        if (currentState != LivState.INACTIVE) {
            currentState = LivState.INACTIVE;
            return;
        }

        if (currentState == LivState.INACTIVE) {
            currentState = LivState.ACTIVE;
            return;
        }
    }

    public static Duke getInstance() {
        if (instance == null) {
            instance = new Duke();
        }
        return instance;
    }
}
