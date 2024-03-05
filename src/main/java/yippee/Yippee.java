package yippee;

import GUI.DialogBox;
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
import yippee.commands.Command;
import yippee.exceptions.InvalidCommandException;
import yippee.exceptions.YippeeException;

/**
 * Implements the Yippee chatbot that records and store tasks from the user.
 * It is able to add, delete, and mark tasks as complete/incomplete
 */
public class Yippee extends Application {
    private static final String FILE_PATH = "./data/storage.txt";
    private Storage storage;
    private TaskList taskList;
    private Ui ui;
    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;

    private Image user = new Image(getClass().getResourceAsStream("/images/Duck.jpg"));
    private Image yippee = new Image(getClass().getResourceAsStream("/images/Yippee.jpg"));

    /**
     * Instantiates the Yippee bot instance.
     *
     * @param filePath Path where data is stored.
     */
    public Yippee(String filePath) {

    }

    public Yippee() {

    }

    /**
     * Greets the user.
     */
    public void greet() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Hello! I'm Yippee! \n");
        stringBuilder.append("What can I do for you?");
        dialogContainer.getChildren().addAll(
                DialogBox.getDukeDialog(stringBuilder.toString(), yippee)
        );
    }

    /**
     * Runs bot instance and processes any inputs from user.
     */
    public void run() {
        greet();
        boolean isExit = false;

        while (!isExit) {
            try {
                String commandString = this.ui.readCommand();
                ui.showLine();
                Command command = new Parser().parseCommand(commandString);
                command.execute(taskList, ui, storage);
                isExit = command.isExit();
            } catch (YippeeException e) {
                ui.printError(e);
            } finally {
                if (!isExit) {
                    ui.showLine();
                }
            }
        }
        this.ui.endCommands();
    }

    /**
     * {@inheritDoc}
     * @param stage the primary stage for this application, onto which
     * the application scene can be set.
     * Applications may create other stages, if needed, but they will not be
     * primary stages.
     */
    @Override
    public void start(Stage stage) {
        this.storage = new Storage(FILE_PATH);
        this.ui = new Ui();
        try {
            this.taskList = storage.load();
        } catch (YippeeException e) {
            ui.printError(e);
            this.taskList = new TaskList();
        }
        AnchorPane mainLayout = setUpComponents(stage);
        setDimensions(stage, mainLayout);
        setUserInputDim();

        dialogContainer.setPrefHeight(Region.USE_COMPUTED_SIZE);

        setAnchor();

        setInputHandler();

        greet();
    }

    /**
     * Set up components in the form of class variables for the GUI.
     * @param stage Stage where application scene will be set.
     * @return AnchorPane for the main layout.
     */
    private AnchorPane setUpComponents(Stage stage) {
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

        return mainLayout;
    }

    /**
     * Set up dimensions for each component of the scene.
     * @param stage Stage where application scene will be set.
     * @param mainLayout AnchorPane for the main layout.
     */
    private void setDimensions(Stage stage, AnchorPane mainLayout) {
        stage.setTitle("Yippee");
        stage.setResizable(false);
        stage.setMinHeight(600.0);
        stage.setMinWidth(400.0);

        mainLayout.setPrefSize(400.0, 600.0);

        scrollPane.setPrefSize(385, 535);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);

        scrollPane.setVvalue(1.0);
        scrollPane.setFitToWidth(true);
    }

    /**
     * Sets the dimensions for the input area.
     */
    private void setUserInputDim() {
        userInput.setPrefWidth(325.0);
        sendButton.setPrefWidth(55.0);
    }

    private void setAnchor() {
        AnchorPane.setTopAnchor(scrollPane, 1.0);

        AnchorPane.setBottomAnchor(sendButton, 1.0);
        AnchorPane.setRightAnchor(sendButton, 1.0);

        AnchorPane.setLeftAnchor(userInput, 1.0);
        AnchorPane.setBottomAnchor(userInput, 1.0);
    }

    private void setInputHandler() {
        sendButton.setOnMouseClicked((event) -> {
            handleUserInput();
        });

        userInput.setOnAction((event) -> {
            handleUserInput();
        });

        dialogContainer.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));
    }

    /**
     * Handles user input and generates the response to be displayed.
     */
    private void handleUserInput() {
        String userText = userInput.getText();
        String yippeeText = getResponse(userInput.getText());
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(userText, user),
                DialogBox.getDukeDialog(yippeeText, yippee)
        );
        userInput.clear();
    }

    /**
     * Generates response for the input from the user.
     * @param input String input parsed from the user.
     * @return String representation for the command reply.
     */
    public String getResponse(String input) {
        try {
            Command command = new Parser().parseCommand(input);
            return command.execute(taskList, ui, storage);
        } catch (InvalidCommandException e) {
            return ui.printError(e);
        }
    }

    public static void main(String[] args) {
        new Yippee("yippee/data/storage.txt").run();
    }
}
