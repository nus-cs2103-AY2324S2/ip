package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

public class MarkCommand extends Command {
    private boolean mark;
    private int index;

    public MarkCommand(int index, boolean mark) {
        this.mark = mark;
        this.index = index;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.mark(index, mark);
        ui.say("Ok! I've " + (mark ? "marked " : "unmarked ") + "this task:\n"
            + tasks.getTaskByIndex(index).toString());
    }
}
