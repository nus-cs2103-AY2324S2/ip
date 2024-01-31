package duke;

import duke.exceptions.DukeException;

public class Command {
    private String command;
    private String[] arguments;
    private boolean isExit = false;

    /**
     * Constructs a valid <code>Command</code> for execution. It however, does not take into account
     * the validity of arguments specified by the user.
     *
     * @param cmd Main command.
     * @param args Supplied arguments.
     */
    protected Command(String cmd, String[] args) {
        this.command = cmd;
        this.arguments = args;
    }

    /**
     * Executes the command attached to the <code>Command</code> instance.
     *
     * @param tl <code>TaskList</code> instance to update tasks.
     * @param ui <code>Ui</code> instance for CLI prints.
     * @param st <code>Storage</code> instance to update file for persistence of task data.
     * @throws DukeException If command fails.
     */
    protected void execute(TaskList tl, Ui ui, Storage st) throws DukeException {
        if (this.command.equals("bye")) {
            ui.echo("Bye. Hope to see you again soon!");
            this.isExit = true;
        } else if (this.command.equals("list")) {
            tl.list(ui);
        } else if (this.command.equals("unmark")) {
            tl.unmark(ui, st, this.arguments);
        } else if (this.command.equals("mark")) {
            tl.mark(ui, st, this.arguments);
        } else if (this.command.equals("delete")) {
            tl.deleteTask(ui, st, this.arguments);
        } else {
            tl.addTask(ui, st, this.command, this.arguments);
        }
    }

    public boolean isExit() {
        return this.isExit;
    }
}
