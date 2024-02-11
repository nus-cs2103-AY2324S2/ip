package duke;

import duke.Command.Command;
import duke.Tasks.TaskList;
import java.util.ArrayList;

public class Duke {


    private Storage storage;
    private TaskList tasks;
    private Parser parser;


    public Duke() {
        String filePath = "./data/botYue.txt";
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            tasks = new TaskList(new ArrayList<>());
        }
    }


    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    String getResponse(String input) throws DukeException {
        try {
            Command com = this.parser.parse(input);
            String res = com.execute(this.tasks, this.storage);
            return res;
        } catch (DukeException e) {
            String response = e.getMessage();
            return response;
        }
    }

}
