package asher.Commands;

import asher.Ui.Ui;
import asher.Tasks.TaskList;

public class ListCommand extends Command {

    public ListCommand(String input) {
        super(input);
    }

    @Override
    public String execute(Ui ui, TaskList taskList, Storage storage) {
        return ui.showTaskList(taskList);
    }
}
