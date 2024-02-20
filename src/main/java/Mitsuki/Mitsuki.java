package Mitsuki;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;


/**
 * The main class of Mitsuki the Chat bot.
 *
 * @author Tee Chu Jie
 */
public class Mitsuki {
    /**
     * The user's todo list.
     */
    protected static TaskList toDoList;
    private final Ui ui;
    private final Storage storage;
    private final Parser parser;

    /**
     * A constructor for a Mitsuki object
     *
     * @param filePath the file where the saved list can be found, if it exists.
     */
    public Mitsuki(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        parser = new Parser();
        try {
            toDoList = new TaskList(Storage.load());
            ui.loadList();
        } catch(FileNotFoundException ex) {
            ui.loadError();
            File saveFile = new File("list.txt");
            try {
                saveFile.createNewFile();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    /**
     * Runs the Mitsuki chatbot and takes in user commands.
     */
    public void run() {
        // Greeting the user.
        ui.greet();

        // Initiating the 'command' variable.
        String command = "nil";
        parser.parse(command);
    }

    public static void main(String[] args) {
        new Mitsuki("list.txt").run();
    }




}
