package haro.command;

import haro.Storage;
import haro.TaskList;
import haro.Ui;
public class ListCommand extends Command {
    public ListCommand() {
        super(false);
    }
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        ui.printList(taskList);
    }
}
