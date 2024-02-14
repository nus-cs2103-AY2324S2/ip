package ken;

import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import ken.control.DialogBox;
import ken.control.MainWindow;
import ken.exception.KenException;
import ken.parser.Parser;
import ken.response.Response;
import ken.storage.Storage;
import ken.task.TaskList;
import ken.ui.Ui;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.layout.Region;
import javafx.scene.image.Image;
import javafx.stage.Stage;



/**
 * The main class representing the Ken application.
 * Ken is a task management Chat-Bot application that allows users to
 * manage their tasks through a command-line interface.
 */
public class Ken extends Application {
    private static final String FILE_PATH = "./data/ken.txt";
    private Image user = new Image(this.getClass().getResourceAsStream("/images/barbie.png"));
    private Image ken = new Image(this.getClass().getResourceAsStream("/images/ken.png"));
    private final Storage storage;
    private final Ui ui;
    private TaskList tasks;
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;
    private Scene scene;

    /**
     * Constructs a Ken object.
     *
     * //@param filePath The file path for storing tasks.
     */

//    public Ken(String filePath) {
//        ui = new Ui();
//        storage = new Storage(filePath);
//        try {
//            tasks = new TaskList(storage.loadTask());
//        } catch (KenException e) {
//            tasks = new TaskList();
//        }
//    }

    public Ken() {
        ui = new Ui();
        storage = new Storage(FILE_PATH);
        try {
            tasks = new TaskList(storage.loadTask());
        } catch (KenException e) {
            tasks = new TaskList();
        }
    }

    /**
     * Iteration 2:
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */

    @FXML
    private void handleUserInput() {
        Label userText = new Label(userInput.getText());
        Label dukeText = new Label(getResponse(userInput.getText()));
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(userText, new ImageView(user)),
                DialogBox.getKenDialog(dukeText, new ImageView(ken))
        );
        userInput.clear();
    }

    @FXML
    private void sayHi() {
        Label dukeText = new Label(ui.welcomeMessage().getMessage());
        dialogContainer.getChildren().addAll(
                DialogBox.getKenDialog(dukeText, new ImageView(ken))
        );
        userInput.clear();
    }


    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */

    @FXML
    public String getResponse(String input) {
        try {
            Parser parser = new Parser(tasks, storage);
            Response response = parser.processUserCommands(input);  // Call the processUserCommands method
            return response.getMessage();  // Return the response message
        } catch (KenException e) {
            return "Error processing user command: " + e.getMessage();
        }
    }


    /**
     * Runs the Ken application.
     * It displays a welcome message, initializes the parser, and
     * starts processing user commands until the user says goodbye.
     */
    public void run() {

    }

    @Override
    public void start(Stage stage) {
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

        stage.setTitle("Ken");
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

        sendButton.setOnMouseClicked((event) -> {
            handleUserInput();
        });

        userInput.setOnAction((event) -> {
            handleUserInput();
        });

        dialogContainer.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));

        sayHi();

    }

    /**
     * The entry point of the Ken application.
     *
     * @param args The command-line arguments.
     */
    public static void main(String[] args) {
        new Ken().run();
    }
}
