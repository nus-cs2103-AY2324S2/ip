package command;

import duke.Storage;
import duke.TaskList;

public class ListCommand extends Command {

    @Override
    public void execute(Storage storage, TaskList taskList) {
        taskList.print();
    }
}
