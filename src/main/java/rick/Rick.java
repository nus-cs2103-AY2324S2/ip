package rick;

import javafx.fxml.FXML;
import rick.logic.Executer;
import rick.logic.Parser;
import rick.logic.RickException;
import rick.ui.Ui;
import rick.util.Storage;
import rick.util.TaskList;






/**
 * A Rick chatbot.
 */
public class Rick {
    private Storage storage;
    private TaskList tasks;


    /**
     * Creates a new instance of the Rick chatbot with specified filePath to store data on hard drive.
     */

    public Rick() {
        storage = new Storage();
        try {
            tasks = new TaskList(storage.load());
        } catch (RickException e) {
            //to delete
            Ui.reply(e.getMessage());
            tasks = new TaskList();
        }
    }

    /**
     * Gets response for specified input
     * @param input user input
     * @return a string representing the response of the chatbot
     */
    @FXML
    public String getResponse(String input) {
        try {
            Parser parser = new Parser(input);
            Executer executer = new Executer(this.tasks, this.storage);
            return executer.execute(parser.parse());
        } catch (RickException e) {
            return e.getMessage();
        } catch (Exception e) {
            return "Aw, jeez, Morty, I did not see that coming. "
                    + "This is... this is bad. I need a drink. I need several drinks. "
                    + "Pass me the flask, Morty. "
                    + "I think I'm gonna need it. Oh, and uh, Morty, send me a report at uh, "
                    + "https://forms.gle/hnnDTA7qYMnhJvQ46, so I can try to fix this mess. "
                    + "Thanks, Morty. You're... you're a good kid..";
        }
    }

    /**
     * A main method that says hello.
     * @param args input.
     */
    public static void main(String[] args) {
        Ui.hello();
    }
}
