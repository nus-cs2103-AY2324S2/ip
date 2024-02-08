package command;

import duke.Storage;
import duke.TaskList;

public class ListCommand extends Command {

    @Override
    public boolean equals(Object other) {
        return other instanceof ListCommand;
    }

    @Override
    public void execute(Storage storage, TaskList taskList) {
        taskList.print();
    }
}
