package duke.command;

import duke.util.Parser;
import duke.util.TaskList;

public class ToggleMarkTask extends Command{
    private int index;

    public ToggleMarkTask(Parser.Cmd type, int index){
        super(type);
        this.index = index;
    }
    @Override
    public void run(TaskList taskList){
        if (this.type == Parser.Cmd.mark){
            taskList.markList(this.index);
        } else {
            taskList.unmarkList(this.index);
        }
    }
}
