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

    /**
     * Parses the input string to extract the action keyword.
     * The action keyword is the first word in the input string.
     *
     * @param input A string representing the input to be parsed.
     * @return The action keyword extracted from the input string.
     */
    public String parseAction(String input) {
        return input.split(" ")[0];
    }
}
