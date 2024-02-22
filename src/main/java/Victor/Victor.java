package victor;

import java.io.IOException;

import victor.parser.Parser;
import victor.storage.Storage;
import victor.tasklist.TaskList;
import victor.ui.Ui;


/**
 * The Victor class is the main part of the AI where
 * we start running the program from.
 *
 * @author Dominic Fu Ming Jun
 */
public class Victor {
    /**
     * Storage class that is used to load and store data into data file
     */
    private static Storage storage;
    /**
     * TaskList class that contains methods to change data to the ArrayList of Tasks
     */
    private static TaskList tasks;
    /**
     * Ui class that displays the Ui of the application
     */
    private static Ui ui;
    /**
     * Parser class that is used to determine what does the user command mean
     */
    private static Parser parser;

    /**
     * The Victor constructor is used to call in all the various parts
     * of the program in order to run it from this file. It would also
     * use the ui.showIntro() method to show the greeting of the ui.
     *
     */
    public Victor() {
        ui = new Ui();
        storage = new Storage("data/victor.txt");
        ui.showIntro();
        tasks = new TaskList(storage.load());
        parser = new Parser(ui, tasks);
    }

    /**
     * The run method is used to run the file.
     * It would continue to run the file until the user input 'bye',
     * then the file stops running and the victor.txt data file
     * is updated using the updateFile in the Storage class.
     *
     * @throws IOException If the storage.updateFile is unable to update the data file
     */
    public void run() throws IOException {
        String userInput = "";

        while (!userInput.equals("bye")) {
            userInput = ui.readCommand();
            assert userInput.isBlank(): "User input is empty. Need to check." ;
            parser.parse(userInput);
        }
        ui.showEnding();
        storage.updateFile(tasks.returnList());
    }

    public static void main(String[] args) throws IOException {
        new Victor().run();
    }
    public String getResponse(String input) throws IOException {
        return parser.parse(input);
    }
}
