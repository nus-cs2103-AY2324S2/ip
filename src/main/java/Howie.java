import commands.Command;
import exceptions.DukeException;
import parser.Parser;
import storage.Storage;
import tasklists.TaskList;
import ui.Ui;

/**
 * The chat-bot To-Do program named Howie which keeps tracks of major major tasks such as
 * todo, event and deadlines.
 * @author Koo Zhuo Hui
 */
public class Howie {
    private static Ui ui;
    private static TaskList taskLs;
    private static Storage storage;

    /**
     * Prints a message when an invalid format has been entered.
     */
    public static void invalidFormat() throws DukeException {
        Ui.printVLine();
        throw new DukeException("I see you've entered an invalid format. Type 'help' if you're unsure :)");
    }

    /**
     * Initialises the program.
     * @param args Input arguments.
     * @throws Exception Throws DukeException and IOException when invalid commands are entered
     * or input exception occurs.
     */
    public static void main(String[] args) throws Exception {
        ui = new Ui();
        storage = new Storage();
        taskLs = storage.readFile();
        while (true) {
            String[] input = ui.getUserCommand();

            Command command = new Parser().parseCommand(input);
            try {
                command.setData(taskLs);
                command.execute();
            } catch (DukeException e) {
                System.out.println(e.toString());
                Ui.printVLine();
            }
        }
    }
}
