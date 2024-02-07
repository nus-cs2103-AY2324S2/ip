package kervyn;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import kervyn.Tasks.TaskList;

import java.io.IOException;
import java.util.Objects;

import kervyn.FXControls.DialogBox;

/**
 * Main class for the Kervyn application.
 * This class initializes the application and starts the interaction with the user.
 */
public class Kervyn extends Application {

    private Storage storage = new Storage("data/tasks.txt");
    private TaskList taskList;
    private Ui ui;

    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;

    private Image user = new Image(this.getClass().getResourceAsStream("/images/user.png"));
    private Image kervyn = new Image(this.getClass().getResourceAsStream("/images/bot.png"));

    public Kervyn () {}
    /**
     * Constructs a new instance of the Kervyn application with the specified file path.
     *
     * @param filePath The path to the file where tasks are stored.
     * @throws RuntimeException If an I/O error occurs when reading tasks from storage.
     */
//    public Kervyn(String filePath) {
//        ui = new Ui();
//        storage = new Storage(filePath);
//        try {
//            taskList = new TaskList(storage.readTasks());
//        } catch (IOException e) {
//            taskList = new TaskList();
//            throw new RuntimeException(e);
//        }
//    }

    /**
     * Starts the Kervyn application. Initializes the chatbot interface and begins interaction.
     *
     * @throws IOException If an I/O error occurs during the interaction.
     */
//    public void run() throws IOException {
//        ui.startChatBot(this.taskList, this.storage);
//    }

    /**
     * The entry point for the Kervyn application.
     *
     * @param args Command-line arguments, not used in this application.
     * @throws IOException If an I/O error occurs when starting the application.
     */
//    public static void main(String[] args) throws IOException {
//        new Kervyn("data/tasks.txt").run();
//    }

    @Override
    public void start(Stage stage) throws Exception {

        try {
            taskList = new TaskList(storage.readTasks());
        } catch (IOException e) {
            taskList = new TaskList();
            throw new RuntimeException(e);
        }

        Parser parser = new Parser(storage);

        scrollPane = new ScrollPane();
        dialogContainer = new VBox();
        scrollPane.setContent(dialogContainer);

        userInput = new TextField();
        sendButton = new Button("Send");

        AnchorPane mainLayout = new AnchorPane();
        mainLayout.getChildren().addAll(scrollPane, userInput, sendButton);

        stage.setTitle("Kervyn");
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
        dialogContainer.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));

        userInput.setPrefWidth(325.0);

        sendButton.setPrefWidth(55.0);

        AnchorPane.setTopAnchor(scrollPane, 1.0);

        AnchorPane.setBottomAnchor(sendButton, 1.0);
        AnchorPane.setRightAnchor(sendButton, 1.0);

        AnchorPane.setLeftAnchor(userInput , 1.0);
        AnchorPane.setBottomAnchor(userInput, 1.0);


        scene = new Scene(mainLayout);

        stage.setScene(scene);
        stage.show();

        //Handle user input via the send button
        sendButton.setOnMouseClicked((event) -> {
            handleUserInput(parser);
        });

        userInput.setOnAction((event) -> {
            handleUserInput(parser);
        });
    }

    private void handleUserInput(Parser parser) {
        String userText = userInput.getText();
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(userText, user)
        );
        parser.deduceCommand(userInput.getText(), taskList, kervyn, dialogContainer);
//        Platform.exit();
        userInput.clear();
    }

    public String getResponse(String input) {
        return "Kervyn heard: " + input;
    }

}
