package howie;

import commands.Command;
import exceptions.HowieException;
import parser.Parser;
import storage.Storage;
import tasklists.TaskList;
import ui.Ui;

import java.io.IOException;

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
     * @throws Exception Throws HowieException and IOException when invalid commands are entered
     * or input exception occurs.
     */
    public static void main(String[] args) throws Exception {
        Ui ui = new Ui();
        Storage storage = new Storage();
        TaskList taskList = storage.readFile();
        while (true) {
            String[] input = ui.getUserCommand();
            try {
                parseAndExecute(input, taskList);
            } catch (HowieException e) {
                System.out.println(e.getMessage());
                Ui.printVLine();
            }
        }
    }

    private static void parseAndExecute(String[] input, TaskList taskList) throws HowieException, IOException {
        Command command = new Parser().parseCommand(input);
        command.setData(taskList);
        command.executeCommand();
    }

}


