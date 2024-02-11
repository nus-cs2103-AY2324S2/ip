package duke.command;

import duke.Storage;
import duke.TaskList;

public class MarkCommand extends Command {
    private boolean mark;
    private int index;

    public MarkCommand(int index, boolean mark) {
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
