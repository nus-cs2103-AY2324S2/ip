import Task.Task;
import Task.TaskList;

public class MarkCommand extends Command {

    private final int positionToMark;

    MarkCommand(int positionToMark) {
        this.positionToMark = positionToMark;
    }
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DookException {
        Task toMark;
        try {
            toMark = tasks.get(positionToMark - 1);
        } catch (IndexOutOfBoundsException e) {
            DookException err;
            if (tasks.size() == 0) {
                err = new DookException("Nooo! You don't have any tasks to mark :(");
            } else {
                err = new DookException(String.format("Nooo! " +
                                "You have %d tasks!" +
                                " Valid inputs for mark is in the range [1 - %d]",
                        tasks.size(), tasks.size()));
            }
            throw err;
        }
        toMark.markAsDone();
        ui.println("Oki! :D Good job! I've marked this task as done:");
        ui.println(toMark.toString());
        storage.write(tasks);
    }
}
