package duke.command;

import duke.exception.SaveStorageException;
import duke.task.TaskList;
import duke.util.Storage;
import duke.util.Ui;

public class HelpCommand extends Command {
    /* The help cheatsheet header. */
    public static final String CHEATSHEET_TITLE = "Here are a list of commands and their respective syntaxes:\n\n";
    /* The help cheatsheet todo instruction. */
    public static final String CHEATSHEET_TODO = "todo <description>\nAdds a new todo to the list.\n\n";
    /* The help cheatsheet deadline instruction. */
    public static final String CHEATSHEET_DEADLINE = "deadline <description> /by<deadline>\nAdds a new deadline to the list.\n\n";
    /* The help cheatsheet event instruction. */
    public static final String CHEATSHEET_EVENT = "event <description> /from<start> /to<end>\nAdds a new event to the list.\n\n";
    /* The help cheatsheet delete instruction. */
    public static final String CHEATSHEET_DELETE = "delete <task number>\nDeletes a task from the list.\n\n";
    /* The help cheatsheet mark instruction. */
    public static final String CHEATSHEET_MARK = "mark <task number>\nMarks a task from the list as complete.\n\n";
    /* The help cheatsheet unmark instruction. */
    public static final String CHEATSHEET_UNMARK = "unmark <task number>\nMarks a task from the list as incomplete.\n\n";
    /* The help cheatsheet list instruction. */
    public static final String CHEATSHEET_LIST = "list\nDisplays all current tasks in the list.\n\n";
    /* The help cheatsheet find instruction. */
    public static final String CHEATSHEET_FIND = "find <search>\nReturns all the tasks in the list with description matching the search field.\n\n";
    /* The help cheatsheet bye instruction. */
    public static final String CHEATSHEET_BYE = "bye\nExits the application.\n\n";

    public String execute(TaskList taskList, Ui ui, Storage storage) {
        return CHEATSHEET_TITLE
                + CHEATSHEET_TODO
                + CHEATSHEET_DEADLINE
                + CHEATSHEET_EVENT
                + CHEATSHEET_DELETE
                + CHEATSHEET_MARK
                + CHEATSHEET_UNMARK
                + CHEATSHEET_LIST
                + CHEATSHEET_FIND
                + CHEATSHEET_BYE;
    }

    public boolean isExit() {
        return true;
    }
}
