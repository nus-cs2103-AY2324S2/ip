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

    @Override
    public boolean equals(Object o) {
        if (o == null) {
            return false;
        }

        if (o instanceof ListCommand) {
            return true;
        }

        return false;
    }
}
