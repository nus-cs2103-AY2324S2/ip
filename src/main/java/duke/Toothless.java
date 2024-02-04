package duke;

import duke.Parsers.FileParser;
import duke.Tasks.Task;
import duke.Tasks.TaskList;
import duke.frontend.DialogBox;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

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
    //private Image userImage = new Image(this.getClass().getResourceAsStream("/images/bunny.jpeg"));
    //private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/wisdom.jpeg"));
    public Toothless() {

    }
    public String getResponse(String input) {
        return "Toothlesss heard: " + input;
    }

    public static void main(String[] args) {
        String filePath = "data/toothless.txt";
        Ui ui = new Ui();
        Parser parser = new Parser();
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
        TaskList currTaskList = fileParser.getTaskList();
        currTaskList = ui.run(parser, currTaskList);
        Storage storage = new Storage(currTaskList);
        try {
            storage.store();
        } catch (IOException e) {
            System.err.println("Error writing to the file: " + e.getMessage());
            e.printStackTrace();
        }
    }


    public void run() {

    }

    static void printTasks(ArrayList<Task> tasksList) {
        int taskCount = 1;
        for (Task t : tasksList) {
            System.out.println(taskCount + ". " + t.toString());
            taskCount++;
        }
    }

    static void printLines() {
        System.out.println("____________________________________________________________");
    }

}
