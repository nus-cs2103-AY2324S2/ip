package duke.commands;

import duke.exceptions.*;
import duke.mainUtils.Parser;
import duke.mainUtils.Storage;
import duke.mainUtils.Ui;
import duke.tasks.TaskList;

public class FindTasks extends Command {

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws TaskNotFoundException, InvalidIndexException {
        taskList.findTasks(Parser.parseFindTask(ui.getCommand()));
    }
}
