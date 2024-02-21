import harvard.Parser;
import harvard.Storage;
import harvard.TaskList;
import harvard.Ui;
import harvard.exceptions.HarvardException;

/**
 * The Harvard class represents a simple task management application
 * that allows users to interact with tasks through a command-line interface.
 * It utilizes a Parser to interpret user commands, a Storage to manage data persistence,
 * a TaskList to handle tasks, and a Ui to display messages to the user.
 */
public class Harvard {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Constructs a Harvard object with the specified file path.
     */
    public Harvard() {
        ui = new Ui();
        storage = new Storage("");
        try {
            tasks = new TaskList(storage.load());
        } catch (Exception e) {
            tasks = new TaskList();
        }
    }

    /**
     * Parses the input string using a Parser object and returns the response.
     *
     * @param input the input string to be parsed
     * @return the response generated by parsing the input
     */
    public String getResponse(String input) {
        Parser parser = new Parser(storage, tasks, ui);

        if (input.equals("bye")) {
            System.exit(0);
        }

        try {
            return parser.parse(input);
        } catch (HarvardException e) {
            return e.getMessage();
        }
    }
}
