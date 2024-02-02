package duke.command;
import duke.exception.DukeException;
import duke.main.Storage;
import duke.main.TaskList;
import duke.main.Ui;

public class UnMarkCommand extends Command {

    private int zeroItem;

    public UnMarkCommand(int oneItem) {
        this.zeroItem = oneItem - 1;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        int oneItem = zeroItem + 1;
        if (oneItem < 1 || oneItem > tasks.getSize() || tasks.get(oneItem - 1) == null) {
            throw new DukeException("Error! duke.task.Task number '" + oneItem + "' does not exist.");
        }
        tasks.unMarkAsDone(zeroItem);
        System.out.println("\nOK, I've marked this task as not done yet:\n\t" + tasks.get(zeroItem) + "\n");
        storage.saveList(tasks.getTasks());
    }
}
