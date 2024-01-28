package duke.command;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.ToDo;

public class UnknownCommandException extends CommandException {
    public UnknownCommandException() {
        super("Error. Unable to recognize command. Commands are CASE SENSITIVE.\n"
                + "Supported List of Commands:\n"
                + "- " + ToDo.INPUT_TODO_FORMAT + ": Adds new ToDo task.\n"
                + "- " + Deadline.INPUT_DEADLINE_FORMAT + ": Adds new Deadline task.\n"
                + "- " + Event.INPUT_EVENT_FORMAT + ": Adds new Event task.\n"
                + "- mark <task-number>: Marks task at index <task-number> as done.\n"
                + "- unmark <task-number>: Marks task at index <task-number> as not done.\n"
                + "- delete <task-number>: Deletes task at index <task-number>.\n"
                + "- bye: exits the program.");
    }
}
