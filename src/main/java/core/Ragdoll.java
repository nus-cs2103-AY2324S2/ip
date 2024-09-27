package core;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.nio.file.Paths;

import commands.Command;
import data.Storage;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import tasks.TaskList;

/**
 * The Ragdoll class is the main class that manages the execution of a command-line application.
 */
public class Ragdoll extends Application {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private final String filePath = Paths.get("data", "tasks.txt").toString();
    private Image user = new Image(this.getClass().getResourceAsStream("/images/user.jpg"));
    private Image ragdoll = new Image(this.getClass().getResourceAsStream("/images/ragdoll.jpg"));
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;
    private Scene scene;
    private CustomPrintStream customPrintStream;

    /**
     * Constructs a Ragdoll instance with the given file path for data storage.
     */
    public Ragdoll() {
        ui = new Ui();
        storage = new Storage(filePath, ui);
        tasks = new TaskList(storage.load());
        customPrintStream = new CustomPrintStream(new ByteArrayOutputStream());
    }

    /**
     * Initializes the application's command-line components and starts the GUI.
     *
     * @param stage The primary stage for this application, onto which the application scene can be set.
     */
    @Override
    public void start(Stage stage) {
        scrollPane = new ScrollPane();
        dialogContainer = new VBox();
        scrollPane.setContent(dialogContainer);
        assert scrollPane != null : "ScrollPane must not be null";
        assert dialogContainer != null : "dialogContainer must not be null";

        userInput = new TextField();
        sendButton = new Button("Send");
        assert userInput != null : "userInput must not be null";
        assert sendButton != null : "sendButton must not be null";

        AnchorPane mainLayout = new AnchorPane();
        mainLayout.getChildren().addAll(scrollPane, userInput, sendButton);

        scene = new Scene(mainLayout);

        stage.setScene(scene);
        stage.show();

        stage.setTitle("Ragdoll");
        stage.setResizable(true);
        stage.setMinHeight(800.0);
        stage.setMinWidth(650.0);

        mainLayout.setPrefSize(650.0, 800.0);

        scrollPane.setPrefSize(635, 735);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);

        scrollPane.setVvalue(1.0);
        scrollPane.setFitToWidth(false);

        dialogContainer.setPrefHeight(Region.USE_COMPUTED_SIZE);

        userInput.setPrefWidth(635.0);

        sendButton.setPrefWidth(55.0);

        AnchorPane.setTopAnchor(scrollPane, 1.0);

        AnchorPane.setBottomAnchor(sendButton, 1.0);
        AnchorPane.setRightAnchor(sendButton, 1.0);

        AnchorPane.setLeftAnchor(userInput , 1.0);
        AnchorPane.setBottomAnchor(userInput, 1.0);

        sendButton.setOnMouseClicked((event) -> {
            handleUserInput();
        });

        userInput.setOnAction((event) -> {
            handleUserInput();
        });

        dialogContainer.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, user),
                DialogBox.getRagdollDialog(response, ragdoll)
        );
        userInput.clear();
    }

    /**
     * Processes the given input by parsing it into a command and executing it.
     * It captures the execution's output by redirecting the System.out output
     * to a custom PrintStream, which allows the method to capture the output
     * generated during the command's execution. After execution, the original
     * System.out PrintStream is restored, and the captured output is returned.
     * The custom PrintStream is then cleared to prepare for the next input processing.
     *
     * @param input The user input string to be processed.
     * @return A string representing the response from executing the parsed command.
     */
    public String getResponse(String input) {
        Command c = Parser.parse(input);

        PrintStream originalOut = System.out;
        System.setOut(customPrintStream);
        c.execute(tasks, ui, storage);
        System.setOut(originalOut);

        String capturedOutput = customPrintStream.getCapturedOutput();
        customPrintStream.clear();
        return capturedOutput;
    }
}
