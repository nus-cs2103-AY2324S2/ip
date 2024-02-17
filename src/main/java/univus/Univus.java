package univus;

import java.util.Scanner;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import univus.task.TaskList;

/**
 * The main class of Univus chatting interaction app.
 * User can organize and list out the schedule.
 */
public class Univus {

    private Storage storage;
    private TaskList taskList;
    private Ui ui;
    private Scanner scanner;
    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;
    private Image user = new Image(this.getClass().getResourceAsStream("/images/pikachu.png"));
    private Image univus = new Image(this.getClass().getResourceAsStream("/images/agumon.png"));

    /**
     * Constructs a new instance of the Univus application.
     *
     * @param filePath The file path for storing and loading task data.
     */
    public Univus(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        taskList = storage.loadFromFile();
        this.scanner = new Scanner(System.in);
    }

    /**
     * Initializes images for the user and Univus, and sets up other necessary components.
     * This constructor is used to prepare any preliminary data or resources required by the application.
     */
    public Univus() {
        // ...
    }

    /**
     * Runs the Univus application, initiating the interaction loop with the user.
     *
     * @throws UnivusException If an error occurs during the execution of the application.
     */
    public void run() throws UnivusException {
        System.out.println(ui.greet());
        while (true) {
            String message = scanner.nextLine();
            if (message.equals("bye")) {
                System.out.println(ui.bye());
                scanner.close();
                break;
            } else {
                System.out.println(Parser.parse(taskList, message));
            }
        }
        storage.saveToFile(taskList);
    }

    /**
     * The main method to start the Univus application.
     *
     * @param args Command-line arguments (not used in this application).
     * @throws UnivusException If an error occurs during the execution of the application.
     */
    public static void main(String[] args) throws UnivusException {
        new Univus("./data/Univus.txt").run();
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
    public String greet() {
        return ui.greet();
    }

    public String bye() {
        return ui.bye();
    }
    public void save() {
        storage.saveToFile(taskList);
    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    String getResponse(String input) {
        try {
            return Parser.parse(taskList, input);
        } catch (UnivusException e) {
            throw new RuntimeException(e);
        }
    }
}
