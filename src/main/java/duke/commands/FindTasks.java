package duke.commands;

import duke.exceptions.*;
import duke.mainUtils.Parser;
import duke.mainUtils.Storage;
import duke.mainUtils.Ui;
import duke.tasks.Task;
import duke.tasks.TaskList;

public class FindTasks extends Command {
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) throws TaskNotFoundException, InvalidIndexException {
        StringBuilder result = taskList.findTasks(Parser.parseFindTask(ui.getCommand()));
        result.append(doneExecute(taskList, ui, storage));
        return result.toString();
    }

    @Override
    public String doneExecute(TaskList taskList, Ui ui, Storage storage) {
        return "Are these what you're looking for? I hope so. Don't blame me if it's wrong lol.";
    }
}
