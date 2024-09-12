package duke.command;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.ToDo;

/**
 * Class to raise errors for unknown Commands.
 *
 * @author KohGuanZeh
 */
public class UnknownCommandException extends CommandException {
    /**
     * Creates a new UnknownCommandException with a message regarding all commands and their format.
     */
    public UnknownCommandException() {
        super("Error. Unable to recognize command.\n"
                + "Note that commands are CASE SENSITIVE.\n"
                + "Supported List of Commands:\n"
                + "- " + ToDo.INPUT_TODO_FORMAT + ":\nAdds new ToDo task.\n"
                + "- " + Deadline.INPUT_DEADLINE_FORMAT + ":\nAdds new Deadline task.\n"
                + "- " + Event.INPUT_EVENT_FORMAT + ":\nAdds new Event task.\n"
                + "- mark <task-number>:\nMarks task at index <task-number> as done.\n"
                + "- unmark <task-number>:\nMarks task at index <task-number> as not done.\n"
                + "- delete <task-number>:\nDeletes task at index <task-number>.\n"
                + "- find <task-name>:\nFinds task with matching keywords of <task-name>.\n"
                + "- priority <task-number> <high/low/none>:\nModify priority of task at index <task-number>.\n"
                + "- bye:\n exits the program.");
    }
}
