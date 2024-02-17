package reacher.command;

import reacher.ReacherException;
import reacher.Storage;
import reacher.TaskList;
import reacher.Ui;

public class FindCommand extends Command {
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws ReacherException {
        ui.print("Searching for:");
        String keyword = ui.readString();
        ui.printList(tasks.findTasks(keyword));
    }
}
