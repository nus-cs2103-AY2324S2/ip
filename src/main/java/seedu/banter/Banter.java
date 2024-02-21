package seedu.banter;


/**
 * Represents the Banter program.
 */
public class Banter {
    private Storage storage = new Storage();
    private Parser parser = new Parser(storage);

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    public String getResponse(String input) {
        return parser.respondToUser(input);
    }
}
