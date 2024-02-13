package Martin;

import java.util.ArrayList;
import java.util.Scanner;
import java.io.IOException;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

/**
 * Represents the main class for the Martin application.
 * Martin is a chatbot that helps manage a todo list.
 */
public class Martin extends Application{
    protected static ArrayList<Task> todoList = new ArrayList<>();
    private static String FILEPATH = "./data/martin.txt";

    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private Parser parser;
    private Command command;

    /**
     * Constructs a new Martin object.
     * Initializes storage with a fixed file path, a new Ui object, and a new Parser
     * object.
     */
    public Martin() {
        this.storage = new Storage(FILEPATH); // fixed file path for now
        this.ui = new Ui();
        this.parser = new Parser();
    }

    @Override
    public void start(Stage stage) {
        Label helloWorld = new Label("Hello World!"); // Creating a new Label control
        Scene scene = new Scene(helloWorld); // Setting the scene to be our Label

        stage.setScene(scene); // Setting the stage to show our screen
        stage.show(); // Render the stage.
    }
    
    /**
     * Runs the chatbot application.
     * This method initializes the necessary components, such as the user interface,
     * storage, and parser.
     * It then enters a loop to continuously read user input and handle commands
     * until the user exits.
     */
    public void run() {
        ui.sayGreeting();
        Scanner sc = new Scanner(System.in);

        todoList = storage.startUpSequence();
        this.tasks = new TaskList(todoList);
        this.command = new Command(tasks, storage, ui, parser);
        while (sc.hasNextLine()) {
            String input = sc.nextLine().strip();
            ChatbotKeyword command = parser.parse(input);
            String remainingWords = parser.getRemainingWords(input);
            try {
                this.command.handleCommand(command, remainingWords);
            } catch (IOException e) {
                System.out.println("Error writing to file");
            }
        }

        ui.sayBye();
        sc.close();
    }

    /**
     * The entry point of the program.
     * Initializes an instance of the Martin class and calls its run method.
     *
     * @param args The command-line arguments passed to the program.
     */
    public static void main(String[] args) {
        Martin martin = new Martin();
        martin.run();
    }
}
