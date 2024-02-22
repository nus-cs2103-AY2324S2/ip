package duke;

import duke.parsers.FileParser;
import duke.tasks.Task;
import duke.tasks.TaskList;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.util.Pair;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Main class of duke chatbot project. Upon running, creates a new ui to handle interactions with user and a parser to parse instructions
 * from the user. Stores a txt file containing consequences of the instructions using Storage and parses the txt file for future
 * use using the FileParser class.
 */
public class Toothless {
    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;

    private TaskList taskList;
    private Ui ui;
    private Parser parser;
    private String currentCommand;


    /**
     * Method to instantiate the Toothless class. Toothless is instantiated when Launcher is run, which will run the main class and call this method.
     */
    public Toothless() {
        this.ui = new Ui();
        this.currentCommand = "";
        String filePath = "data/toothless.txt";
        this.parser = new Parser();
        File f = new File(filePath);
        try {
            boolean fileCreated = f.createNewFile();

        } catch (IOException e) {
            System.err.println("Error creating the file: " + e.getMessage());
            e.printStackTrace();
        }


        FileParser fileParser = new FileParser(f);
        try {
            fileParser.parseFile(f);
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        this.taskList = fileParser.getTaskList();

    }

    /**
     * Collects the user input and returns a string as response
     * @param input Command that user inputs
     * @return String response
     */
    public String getResponse(String input) {
        this.currentCommand = input;
        Pair<TaskList, String> output = parser.parse(this.taskList, input);
        this.taskList = output.getKey();
        return output.getValue();
    }

    public static void main(String[] args) {
        ClassLoader.getSystemClassLoader().setDefaultAssertionStatus(true);
        new Toothless().run();
    }


    public void run() {
        ui.greet();
        boolean isExit = false;
        String message = "";
        ui.bye();
        Storage storage = new Storage(this.taskList);

        try {
            storage.store();
        } catch (IOException e) {
            System.err.println("Error writing to the file: " + e.getMessage());
            e.printStackTrace();
        }

    }

    /**
     * Prints out task list
     * @param tasksList task list to be printed out
     */
    static void printTasks(ArrayList<Task> tasksList) {
        int taskCount = 1;
        for (Task t : tasksList) {
            System.out.println(taskCount + ". " + t.toString());
            taskCount++;
        }
    }

    /**
     * Prints out line for formatting purposes when not running GUI
     */
    static void printLines() {
        System.out.println("____________________________________________________________");
    }

}
