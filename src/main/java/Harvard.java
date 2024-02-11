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
     *
     * @param filePath the file path where task data is stored or to be stored
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
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    public String getResponse(String input) {
        Parser parser = new Parser(storage, tasks, ui);

        try {
            return parser.parse(input);
        } catch (HarvardException e) {
            return e.getMessage();
        }
    }
}
