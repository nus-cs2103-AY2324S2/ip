package campus;

import campus.infrastructure.Parser;
import campus.infrastructure.Storage;
import campus.infrastructure.TaskList;
import campus.infrastructure.Ui;

/**
 * Contains the logic for the ChatBot named 'Campus'
 */
public class Campus {
    private final Ui ui;
    private final Parser parser;

    /**
     * Creation of a Campus instance
     */
    @SuppressWarnings("checkstyle:AbbreviationAsWordInName")
    public Campus() {
        String FILE_PATH = "src/main/java/campus/dataTest.txt";
        this.ui = new Ui();
        TaskList taskList = new TaskList();
        Storage storage = new Storage(FILE_PATH);
        this.parser = new Parser(this.ui, taskList, storage);
    }

    public static void main(String[] args) {
        new Campus();
    }

    /**
     * Hands off the string input to the parser class in which the parse message method is invoked to handle
     * how to process the command
     * @param input the user's message in a string format
     * @return the appropriate response of type string
     */
    public String parseMessage(String input) {
        return this.parser.parseMessage(input);
    }

    public String printGreeting() {
        return this.ui.printGreeting();
    }

    public String printExiting() {
        return this.ui.printExiting();
    }
}
