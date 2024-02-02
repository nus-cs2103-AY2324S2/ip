package duke.command;

import duke.util.Parser;
import duke.util.TaskList;
import duke.util.Ui;

public class ListTask extends Command{
    public ListTask(Parser.Cmd type) {
        super(type);
    }
    @Override
    public void run(TaskList taskList){
        Ui.displayFullList(taskList);
    }
}
