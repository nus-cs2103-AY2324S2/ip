package seedu.banter;


import seedu.banter.errors.BanterError;
import seedu.banter.ui.Ui;

/**
 * Represents the Banter program.
 */
public class Banter {
    private Storage storage;
    private Parser parser;

    /**
     * Initializes the Banter program.
     */
    public String initialize() {
        try {
            storage = new Storage();
            parser = new Parser(storage);
            return Ui.GREET_MESSAGE.getString();
        } catch (BanterError e) {
            return e.getMessage();
        }
    }

    /**
     * Returns the response to the user input.
     * @param input User input.
     * @return Response to the user input.
     */
    public String getResponse(String input) {
        return parser.respondToUser(input);
    }
}
