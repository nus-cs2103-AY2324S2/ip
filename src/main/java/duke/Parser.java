package duke;

import java.util.Scanner;

/**
 * The Parser class handles parsing user input and executing corresponding actions.
 */
public class Parser {
    private Scanner sc;
    public Ui ui;
    public Storage storage;

    /**
     * Constructs a new Parser instance.
     *
     * @param store The storage object to interact with task storage.
     */
    public Parser(Storage store) {
        sc = new Scanner(System.in);
        ui = new Ui();
        storage = store;

    }

    public String parseAction(String input) {
        return input.split(" ")[0];
    }
}
