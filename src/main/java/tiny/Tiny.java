package tiny;

import java.io.IOException;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.layout.Region;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import tiny.exceptions.TinyException;
import tiny.gui.DialogBox;

/**
 * Represents the class of our program.
 */
public class Tiny extends Application {
    private static final String FILE_PATH = "../../../data/tasks.txt";
    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private Parser parser = new Parser();
    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;
    private Image user = new Image(this.getClass().getResourceAsStream("/resources/images/duke.png"));
    private Image tiny = new Image(this.getClass().getResourceAsStream("/resources/images/tiny.jpeg"));

    /**
     * Initializes Tiny.
     */
    public Tiny() {
        ui = new Ui();
        storage = new Storage(FILE_PATH);
        try {
            tasks = new TaskList(storage.load());
        } catch (TinyException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    /**
     * Runs Tiny to receive user input and prints the appropriate output.
     *
     * @throws IOException If there are exception reading.
     */
    public void run() throws IOException {
        ui.start();
        boolean isExit = false;
        while (!isExit) {
            try {
                String input = ui.readCommand();
                printContent(parser.parse(input, tasks));
                storage.save(tasks.toSave());
                isExit = parser.isExit();
            } catch (TinyException e) {
                ui.showError(e.getMessage());
            }
        }
    }

    /**
     * Starts the program.
     *
     * @param args Arguments that the user pass in.
     */
    public static void main(String[] args) throws IOException {
        new Tiny().run();
    }

    @Override
    public void start(Stage stage) {
        // Step 1. Setting up required components

        // The container for the content of the chat to scroll.
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

        // Step 2. Formatting the window to look as expected
        stage.setTitle("Tiny");
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

        AnchorPane.setLeftAnchor(userInput, 1.0);
        AnchorPane.setBottomAnchor(userInput, 1.0);

        // Step 3. Add functionality to handle user input.
        sendButton.setOnMouseClicked((event) -> {
            handleUserInput();
        });

        userInput.setOnAction((event) -> {
            handleUserInput();
        });

        // Scroll down to the end every time dialogContainer's height changes.
        dialogContainer.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));
    }

    /**
     * Iteration 2:
     * Creates two dialog boxes, one echoing user input and the other containing
     * Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    private void handleUserInput() {
        Label userText = new Label(userInput.getText());
        Label tinyText = new Label(getResponse(userInput.getText()));
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(userText, new ImageView(user)),
                DialogBox.getDukeDialog(tinyText, new ImageView(tiny)));
        userInput.clear();
    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    private String getResponse(String input) {
        return "Tiny heard: " + input;
    }

    // Printing Methods
    private static void tabPrint(String input) {
        System.out.println("   " + input);
    }

    private static void printLine() {
        tabPrint("____________________________________________________________\n");
    }

    private static void printContent(String input) {
        printLine();
        tabPrint(input);
        printLine();
    }

}
