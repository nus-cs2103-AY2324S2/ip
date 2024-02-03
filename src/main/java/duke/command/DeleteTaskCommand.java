package duke.command;

import duke.util.Parser;
import duke.util.TaskList;

public class DeleteTaskCommand extends Command {
    private int index;
    public DeleteTaskCommand(Parser.Cmd type, int index) {
        super(type);
        this.index = index;
    }
    @Override
    public void run(TaskList taskList) {
        taskList.deleteList(this.index);
    }
}
