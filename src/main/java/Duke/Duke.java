package Duke;

import Duke.DukeException.DukeException;
import Duke.Parser.Parser;
import Duke.Storage.Storage;
import Duke.TaskList.TaskList;
import Duke.Ui.Ui;
import java.io.IOException;

public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private Parser parser;

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
                    Ui.printList(tasks);
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

    public static void main(String[] args) {
        new Duke("data/tasks.txt").run();
    }
}
