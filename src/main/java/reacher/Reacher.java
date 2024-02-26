package reacher;

import reacher.command.Command;
import java.time.format.DateTimeParseException;

public class Reacher {
    private final Storage storage;
    private final TaskList tasks;

    public Reacher(String filePath) {
        storage = new Storage(filePath);
        tasks = new TaskList(storage.loadList());
    }

    /**
     * Gets command from input, executes command and returns response as a string.
     * @param input Text from user.
     */
    public String getResponse(String input) {
        try {
            Command command = Parser.getCommand(input);
            return command.execute(input, tasks, storage);
        } catch (ReacherException e) {
            return (e.getMessage());
        } catch (DateTimeParseException e) {
            return("Pls provide correct format: yyyy-mm-dd");
        }
    }

}
