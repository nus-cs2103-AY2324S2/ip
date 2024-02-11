package duke;

import duke.exceptions.DukeException;

/**
 * <code>Command</code> encapsulates the main command and the arguments needed for execution.
 */
public class Command {
    private String command;
    private String[] arguments;

    /**
     * Constructs a valid <code>Command</code> for execution. It however, does not take into account
     * the validity of arguments specified by the user.
     *
     * @param cmd Main command.
     * @param args Supplied arguments.
     */
    protected Command(String cmd, String... args) {
        command = cmd;
        arguments = args;
    }

    /**
     * Executes the command attached to the <code>Command</code> instance.
     *
     * @param tl <code>TaskList</code> instance to update tasks.
     * @param st <code>Storage</code> instance to update file for persistence of task data.
     * @throws DukeException If command fails.
     */
    protected String execute(TaskList tl, Storage st) throws DukeException {
        if (command.equals("list")) {
            return tl.list();
        } else if (command.equals("unmark")) {
            return tl.unmark(st, arguments);
        } else if (command.equals("mark")) {
            return tl.mark(st, arguments);
        } else if (command.equals("delete")) {
            return tl.deleteTask(st, arguments);
        } else if (command.equals("find")) {
            return tl.find(arguments[0]);
        } else {
            return tl.addTask(st, command, arguments);
        }
    }
}
