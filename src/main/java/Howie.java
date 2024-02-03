import commands.Command;
import exceptions.DukeException;
import parser.Parser;
import storage.Storage;
import task.Task;
import tasklists.TaskList;
import ui.Ui;

import java.util.List;

/**
 * The chat-bot To-Do program named Howie which keeps tracks of major major tasks such as
 * todo, event and deadlines.
 * @author Koo Zhuo Hui
 */
public class Howie {

    /**
     * Initialises the program.
     * @param args Input arguments.
     * @throws Exception Throws DukeException and IOException when invalid commands are entered
     * or input exception occurs.
     */
    public static void main(String[] args) throws Exception {
        Ui ui = new Ui();
        Storage storage = new Storage();
        TaskList taskLs = storage.readFile();
        while (true) {
            String[] input = ui.getUserCommand();
            try {
                Command command = new Parser().parseCommand(input);
                command.setData(taskLs);
                command.execute();
            } catch (DukeException e) {
                System.out.println(e.getMessage());
                Ui.printVLine();
            }
        }
    }
}
