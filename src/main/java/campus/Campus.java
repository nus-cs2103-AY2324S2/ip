package campus;

import campus.infrastructure.Parser;
import campus.infrastructure.Storage;
import campus.infrastructure.TaskList;
import campus.infrastructure.Ui;

/**
 * Contains the logic for the ChatBot named 'Campus'.
 */
public class Campus {
    private final Ui ui;
    private final Parser parser;

    /**
     * Creation of a Campus instance with hardcoded destination for textfile.
     */
    @SuppressWarnings("checkstyle:AbbreviationAsWordInName")
    public Campus() {
        final String FILE_PATH = "/src/main/java/campus/data.txt";
        this.ui = new Ui();
        TaskList taskList = new TaskList();
        Storage storage = new Storage(FILE_PATH);
        this.parser = new Parser(this.ui, taskList, storage);
    }

    /**
     * Creates a new instance of Campus.
     * @param args main args.
     */
    public static void main(String[] args) {
        new Campus();
    }

    /**
     * Hands off the string input to the parser class in which the parse message method is invoked to handle
     * how to process the command.
     * @param input the user's message in a string format.
     * @return the appropriate response of type string.
     */
    public String parseMessage(String input) {
        String message = this.parser.parseMessage(input);
        assert (message != null);
        return message;
    }

    public String printGreeting() {
        String message = this.ui.printGreeting();
        assert (message != null);
        return message;
    }

    public String printExiting() {
        String message = this.ui.printExiting();
        assert (message != null);
        return message;
    }
}
