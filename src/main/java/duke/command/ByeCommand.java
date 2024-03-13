package duke.command;

import duke.task.TaskList;
import duke.util.*;
import duke.exception.SaveStorageException;

/**
 * The class representing the bye command.
 * */
public class ByeCommand extends Command {
    /* The first line of response to the user. */
    public static final String RESPONSE_ONE = "Bye. Hope to see you again soon!";
    /* The second line of response to the user. */
    public static final String RESPONSE_TWO = "Goodbye!";

    public String execute(TaskList taskList, Ui ui, Storage storage) {
        try {
            System.out.println(RESPONSE_ONE);
            storage.save(taskList);
        } catch (SaveStorageException e) {
            ui.showError(e.getMessage());
        }

        return RESPONSE_TWO;
    }

    public boolean isExit() {
        return true;
    }
}
