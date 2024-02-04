package kervyn;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import kervyn.Tasks.TaskList;

import java.io.IOException;

/**
 * Main class for the Kervyn application.
 * This class initializes the application and starts the interaction with the user.
 */
public class Kervyn extends Application {

    private Storage storage;
    private TaskList taskList;
    private Ui ui;

    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;


    public Kervyn () {}
    /**
     * Constructs a new instance of the Kervyn application with the specified file path.
     *
     * @param filePath The path to the file where tasks are stored.
     * @throws RuntimeException If an I/O error occurs when reading tasks from storage.
     */
    public Kervyn(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            taskList = new TaskList(storage.readTasks());
        } catch (IOException e) {
            taskList = new TaskList();
            throw new RuntimeException(e);
        }
    }

    /**
     * Starts the Kervyn application. Initializes the chatbot interface and begins interaction.
     *
     * @throws IOException If an I/O error occurs during the interaction.
     */
    public void run() throws IOException {
        ui.startChatBot(this.taskList, this.storage);
    }

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
    }
}
