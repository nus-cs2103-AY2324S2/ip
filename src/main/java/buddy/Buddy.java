package buddy;

import command.Command;
import exception.BuddyException;
import parser.Parser;
import storage.Storage;
import task.TaskList;

/**
 * Represents buddy.Buddy the chatbot!
 *
 * @author Mahadhir
 */
public class Buddy {
    private Storage storage;
    private TaskList tasks;
    private boolean isExit;

    /**
     * Creates a buddy.Buddy chatbot instance with specified file path.
     *
     * @param filePath Location of file storing task data.
     */
    public Buddy(String filePath) {
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (BuddyException e) {
            tasks = new TaskList();
        }
    }

    /**
     * Retrieves the response output based on given command.
     *
     * @param input user command given.
     * @return response output of the given command.
     */
    public String getResponse(String input) {
        String response;
        try {
            Command c = Parser.parse(input);
            response = c.execute(tasks, storage);
            isExit = c.isExit();
            assert response != null; // There should be a valid response;
            return response;
        } catch (BuddyException e) {
            return e.getMessage();
        }
    }

    /**
     * Retrieves boolean representing whether buddy.Buddy should exit.
     *
     * @return boolean representing whether buddy.Buddy should exit.
     */
    public boolean isExit() {
        return isExit;
    }
}
