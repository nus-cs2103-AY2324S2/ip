package ezra;

import java.io.FileNotFoundException;
import java.util.Scanner;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * Main class for the Ezra chatbot.
 */
public class Ezra extends Application {

    private Storage storage;
    private TaskList tasks;

    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;

    /**
     * Constructs an Ezra object with the specified file path to load saved tasks from.
     *
     * @param filepath The file path for storing tasks.
     */
    public Ezra(String filepath) {
        this.storage = new Storage(filepath);
        try {
            this.tasks = new TaskList(storage.load());
        } catch (FileNotFoundException e) {
            this.tasks = new TaskList();
        }
    }

    /**
     * Runs the Ezra application, allowing the user to enter commands.
     */
    public void run() {
        Ui.greet();

        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextLine()) {
            String input = scanner.nextLine();
            Parser.read(input, storage, tasks);
            if (input.equals("bye")) {
                break;
            }
        }
        scanner.close();
    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    public String getResponse(String input) {
        return "Ezra heard: " + input;
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
    }

    /**
     * The main method to start the Ezra application.
     *
     * @param args Command line arguments (not used in this application).
     */
    public static void main(String[] args) {
        new Ezra("data/ezra.txt").run();
    }
}
