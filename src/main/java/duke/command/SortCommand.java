package duke.command;

import duke.TaskList;

/**
 * A command that sorts all deadlines.
 */
public class SortCommand extends Command {

    /**
     * Sorts deadline tasks.
     *
     * @param tasks   The list of tasks.
     * @return Sorted list of deadlines..
     */
    @Override
    public String execute(TaskList tasks) {
        tasks.sort();
        return new ListCommand().execute(tasks);
    }

    @Override
    public boolean equals(Object other) {
        return other instanceof NothingCommand;
    }
}
