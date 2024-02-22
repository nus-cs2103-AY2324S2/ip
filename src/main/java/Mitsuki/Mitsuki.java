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
    private final Parser parser;
    private final Storage storage;

//    /**
//     * A constructor for a Mitsuki object
//     *
//     * @param filePath the file where the saved list can be found, if it exists.
//     */
//    public Mitsuki(String filePath) {
//        ui = new Ui();
//        Storage storage = new Storage(filePath);
//        parser = new Parser();
//        try {
//            toDoList = new TaskList(Storage.load());
//            ui.loadList();
//        } catch(FileNotFoundException ex) {
//            ui.loadError();
//            File saveFile = new File("list.txt");
//            try {
//                saveFile.createNewFile();
//            } catch (IOException e) {
//                throw new RuntimeException(e);
//            }
//        }
//    }

    public Mitsuki() {
        ui = new Ui();
        parser = new Parser();
        storage = new Storage("list.txt");
    }

    /**
     * Runs the Mitsuki chatbot and takes in user commands.
     */
    public String run() {
        // Greeting the user.
        //return ui.greet();

        // Initiating the 'command' variable.
        String command = "nil";
        return parser.parse(command);
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
