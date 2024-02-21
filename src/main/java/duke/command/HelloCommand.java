package duke.command;

import duke.task.TaskList;
import duke.util.*;

/**
 * The class representing the hello command.
 * */
public class HelloCommand extends Command {
    /* The first line of response to the user. */
    public static final String RESPONSE = "Hello, I am theGiantPeach! How may I help you today?";

    public String execute(TaskList taskList, Ui ui, Storage storage) {
        return RESPONSE;
    }

    public boolean isExit() {
        return true;
    }
}