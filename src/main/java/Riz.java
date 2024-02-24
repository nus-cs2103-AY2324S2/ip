import javafx.application.Application;
import riz.data.*;
import riz.io.*;

/**
 * This is the main class of the RizBot.
 * This class mainly consists of the interactions between the classes TaskList, Storage.
 * The filepath in which data is saved is also determined here as it is passed into the Storage class.
 * The input given by the user, the user's TaskList and Saved data are passed into a Parser which processes the input.
 */
public class Riz {
    private TaskList taskList;
    private Storage storage;

    /**
     * The constructor initialises a Storage object with the filepath given
     * and loads the TaskList with the tasks from memory using the Storage object.
     */
    public Riz() {
        this.storage = new Storage("riz/data/riz.txt");
        this.taskList = new TaskList(this.storage.loadFromFile());
    }
    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    public String getResponse (String input) {
        return " " + Parser.parse(this.taskList, this.storage, input);
    }
    public static void main(String[] args) {
        Application.launch(Main.class, args);
    }
}