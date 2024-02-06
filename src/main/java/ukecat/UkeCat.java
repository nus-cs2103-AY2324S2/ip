package ukecat;

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

import java.util.Objects;

/**
 * The main class representing the UkeCat application.
 * UkeCat is a simple program that interacts with users to manage tasks.
 * This class initializes the user interface, loads tasks from storage,
 * and handles user input through the Parser class.
 */
public class UkeCat extends Application {
    private final Ui ui;

    private final Image user = new Image(Objects.requireNonNull(
            this.getClass().getResourceAsStream("/images/DaUser.png")));
    private final Image duke = new Image(Objects.requireNonNull(
            this.getClass().getResourceAsStream("/images/DaDuke.png")));

    public UkeCat() {
        ui = new Ui();
    }

    VBox dialogContainer = new VBox();
    TextField userInput = new TextField();

    /**
     * Runs the UkeCat application.
     * Displays a welcome message, loads tasks, and continuously
     * processes user input until the "bye" command is entered.
     */
    public void run() {
        ui.welcome();
        FileManager.loadTasks();
        try {
            // Read user input
            while (true) {
                Parser.parse();
                if (Storage.words[0].equals("bye")) {
                    ui.bye();
                    Parser.closeScanner();
                    System.exit(0);
                }
                ui.respond(Storage.words);
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    /**
     * The main method to start the UkeCat application.
     * Creates an instance of UkeCat and calls the run method.
     *
     * @param args The command-line arguments (not used for UkeCat)
     */
    public static void main(String[] args) {
        new UkeCat().run();
    }

    /**
     * The start() method is called when the application is launched.
     *
     * @param stage
     */
    @Override
    public void start(Stage stage) {

        // Step 1. Setting up required components
        ScrollPane scrollPane = new ScrollPane();

        scrollPane.setContent(dialogContainer);


        Button sendButton = new Button("Send");

        AnchorPane mainLayout = new AnchorPane();
        mainLayout.getChildren().addAll(scrollPane, userInput, sendButton);

        // Step 2. Formatting the window to look as expected
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

        dialogContainer.setPrefHeight(Region.USE_COMPUTED_SIZE);

        userInput.setPrefWidth(325.0);

        sendButton.setPrefWidth(55.0);

        AnchorPane.setTopAnchor(scrollPane, 1.0);

        AnchorPane.setBottomAnchor(sendButton, 1.0);
        AnchorPane.setRightAnchor(sendButton, 1.0);

        AnchorPane.setLeftAnchor(userInput , 1.0);
        AnchorPane.setBottomAnchor(userInput, 1.0);

        Scene scene = new Scene(mainLayout);
        stage.setScene(scene);
        stage.show();

        // Scroll down to the end every time dialogContainer's height changes.
        dialogContainer.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));

        //Part 3. Add functionality to handle user input.
        sendButton.setOnMouseClicked((event) -> {
            handleUserInput();
        });

        userInput.setOnAction((event) -> {
            handleUserInput();
        });

    }

    public void handleUserInput() {
        String userText = userInput.getText();
        String dukeText = userInput.getText();
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(userText, user),
                DialogBox.getDukeDialog(dukeText, duke)
        );
        userInput.clear();
    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    public String getResponse(String input) {
        return "Duke heard: " + input;
    }
}
