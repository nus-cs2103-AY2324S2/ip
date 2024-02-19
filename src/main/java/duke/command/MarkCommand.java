package duke.command;

import duke.Storage;
import duke.TaskList;

/**
 * Class representing Mark commands.
 */
public class MarkCommand extends Command {
    private boolean mark;
    private int index;

    public MarkCommand(int index, boolean mark) {
        assert index >= 0 : "index should not be negative";
        this.mark = mark;
        this.index = index;
    }

    @Override
    public String execute(TaskList tasks, Storage storage) {
        tasks.mark(index, mark);
        return "Ok! I've " + (mark ? "marked " : "unmarked ") + "this task:\n"
            + tasks.getTaskByIndex(index).toString();
    }
}
