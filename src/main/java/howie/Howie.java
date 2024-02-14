package howie;

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

    public Howie() {

    }
    /**
     * Initialises the program.
     * @param args Input arguments.
     * @throws Exception Throws DukeException and IOException when invalid commands are entered
     * or input exception occurs.
     */
    public static void main(String[] args) throws Exception {
        Ui ui = new Ui();
        Storage storage = new Storage();
        TaskList taskList = storage.readFile();
        while (true) {
            String[] input = ui.getUserCommand();
            try {
                Command command = new Parser().parseCommand(input);
                assert command != null : "Failed at Howie.java. Command variable is null";
                command.setData(taskList);
                command.execute();
            } catch (DukeException e) {
                System.out.println(e.getMessage());
                Ui.printVLine();
            }
        }
    }

}


