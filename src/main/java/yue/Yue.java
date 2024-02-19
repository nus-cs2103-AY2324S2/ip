package yue;

import yue.Command.Command;
import yue.Tasks.TaskList;
import java.util.ArrayList;

public class Yue {


    private Storage storage;
    private TaskList tasks;
    private Parser parser;


    public Yue() {
        String filePath = "./data/botYue.txt";
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (YueException e) {
            tasks = new TaskList(new ArrayList<>());
        }
        assert storage != null : "Storage object must not be null";
        assert tasks != null : "TaskList object must not be null";
    }


    /**
     * Retrieves a response based on the provided input string.
     * @param input The input string to be processed.
     * @return The response generated based on the input.
     * @throws YueException If there is an error in parsing or executing the command.
     */
    String getResponse(String input) throws YueException {
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
