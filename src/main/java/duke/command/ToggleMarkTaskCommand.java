package duke.command;

import duke.util.Parser;
import duke.util.TaskList;

public class ToggleMarkTaskCommand extends Command {
    private int index;

    public ToggleMarkTaskCommand(Parser.Cmd type, int index) {
        super(type);
        this.index = index;
    }
    @Override
    public void run(TaskList taskList) {
        if (this.type == Parser.Cmd.mark) {
            taskList.markList(this.index);
        } else {
            taskList.unmarkList(this.index);
        }
    }
}
