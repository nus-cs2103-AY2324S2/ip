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
        assert storage != null : "Storage object must not be null";
        assert tasks != null : "TaskList object must not be null";
    }


    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    String getResponse(String input) throws DukeException {
        assert input != null : "Input string cannot be null";
        assert parser != null : "Parser object must be initialized";
        assert tasks != null : "TaskList object must be initialized";
        assert storage != null : "Storage object must be initialized";

        try {
            Command com = this.parser.parse(input);
            String res = com.execute(this.tasks, this.storage);
            assert res != null : "Response string cannot be null";
            return res;
        } catch (Exception e) {
            String response = e.getMessage();
            assert response != null : "Error message string cannot be null";
            return response;
        }
    }

}
