package destiny;

import commands.Command;
import gui.Main;
import javafx.application.Application;



/**
 * The main class that contains functions involved with running the chatbot.
 */
public class Destiny {

    public static final String CHATBOT_NAME = "Destiny";
    public static final String GREETING_MSG = "\nGreetings! I'm " + CHATBOT_NAME + "\nHow may I serve you?";
    public static final String GOODBYE_MSG = "\nIt's been nice chatting with you!.\nHope to see you again soon!";


    private Storage storage;
    private TaskList tasks;
    private Parser parser;

    /**
     * Initializes the instance variables of the Destiny class.
     */
    public Destiny() {
        storage = new Storage();
        tasks = new TaskList(storage.loadData());
        parser = new Parser();
    }

    /**
     * Main code for the running of the Destiny chatbot which interacts with the graphical user interface.
     *
     * @param input The input given by the user.
     * @return Appropriate string response from Destiny.
     */
    public String getResponse(String input) {
        String result;
        try {
            Command c = parser.parse(input);
            result = c.execute(tasks);
        } catch (DestinyException e) {
            return e.getMessage();
        }

        storage.saveData(tasks);

        return result;
    }

    public static void main(String[] args) {
        Application.launch(Main.class, args);
    }
}
