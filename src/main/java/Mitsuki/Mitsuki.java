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
    protected final Ui ui;

    /**
     * A constructor for a Mitsuki object.
     */
    public Mitsuki() {
        ui = new Ui();
        Parser parser = new Parser();

        Storage storage = new Storage("list.txt");
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
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    public String getResponse(String input) {
        Parser parser = new Parser();
        return parser.parse(input);
    }



}
