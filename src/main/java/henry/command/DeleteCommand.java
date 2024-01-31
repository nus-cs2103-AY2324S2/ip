package henry.command;

import henry.Storage;
import henry.TaskList;
import henry.Ui;
import henry.HenryException;

public class DeleteCommand extends Command {
    private int index;

    public DeleteCommand(String args) throws HenryException {
        if (args.isBlank()) {
            throw new HenryException("No index provided");
        }
        this.index = Integer.parseInt(args) - 1;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws HenryException {
        tasks.deleteTask(index);
        storage.save(tasks);
    }
}
