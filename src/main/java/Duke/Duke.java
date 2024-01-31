package Duke;

import Duke.DukeException.DukeException;
import Duke.Parser.Parser;
import Duke.Storage.Storage;
import Duke.TaskList.TaskList;
import Duke.Task.Task;
import Duke.Ui.Ui;

import java.io.IOException;
import java.util.ArrayList;

/**
 * This class is the main class that navigates aids in the navigation of the program.
 * @author Tang Hao Liang
 */

public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private Parser parser;

    /**
     * Constructor creates new Ui, Storage, Parser and loads the storage into the list.
     *
     * @param filePath Path to file.
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        parser = new Parser();
        try {
            tasks = new TaskList(storage.load());
        }  catch (IOException e) {
            Ui.showLoadingError();
        }
    }

    /**
     * Runs the code to get users input and path the input to its respective functions.
     */
    public void run() {
        Ui.showWelcome();
        boolean finished = false;

        while(!finished) {
            try{
                String inputs = ui.readCommand();
                String[] parse = parser.parse(inputs);
                if (parse[0].equalsIgnoreCase("bye")) {
                    parser.bye();
                } else if (parse[0].equalsIgnoreCase("list")) {
                    Ui.printList(tasks.getList());
                } else if (parse[0].equalsIgnoreCase("mark")) {
                    int num = parser.stringToNum(parse[1]);
                    tasks.mark(num);
                } else if (parse[0].equalsIgnoreCase("unmark")) {
                    int num = parser.stringToNum(parse[1]);
                    tasks.unmark(num);
                } else if (parse[0].equalsIgnoreCase("todo")) {
                    String description = parser.toDo(parse[1]);
                    tasks.toDo(description);
                } else if (parse[0].equalsIgnoreCase("deadline")) {
                    String[] split = parser.deadline(parse[1]);
                    tasks.deadline(split[0], parser.stringToDate(split[1]));
                } else if (parse[0].equalsIgnoreCase("event")) {
                    String[] split = parser.event(parse[1]);
                    tasks.event(split[0], split[1], split[2]);
                } else if (parse[0].equalsIgnoreCase("delete")) {
                    int num = parser.stringToNum(parse[1]);
                    tasks.delete(num);
                } else if (parse[0].equalsIgnoreCase("find")) {
                    ArrayList<Task> contains = tasks.findContains(parse[1]);
                    Ui.printList(contains);
                } else {
                    throw new DukeException("Please do enter a new proper command.\n");
                }
                finished = parser.isFinished();
                storage.arrayToFile(tasks.getList());
            } catch (DukeException e) {
                Ui.showError(e.toString());
            }
        }
        Ui.showEnd();
    }

    /**
     * Runs when the code starts.
     *
     * @param args Null.
     */
    public static void main(String[] args) {
        new Duke("data/tasks.txt").run();
    }
}
