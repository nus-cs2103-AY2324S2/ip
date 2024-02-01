package seedu.banter;

public class Banter {
    private Storage storage = new Storage();
    private Parser parser = new Parser(storage);

    /**
     * Starts the Banter program.
     */
    public void start() {
        parser.printGreetMessage();
        parser.respondUntilExit();
    }
}
