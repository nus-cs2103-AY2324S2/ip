package duke;

public class MarkCommand implements Command {
    int index;
    boolean isDone;

    MarkCommand(int index, boolean isDone) {
        this.index = index;
        this.isDone = isDone;
    }

    public void execute(TaskList tasks, Ui ui) throws DukeException {
        if (index + 1 > tasks.size()) {
            throw new DukeException("You only have " + tasks.size() + " tasks in the list.");
        }
        Task task = tasks.get(index);
        if (isDone) {
            task.mark();
            ui.markMessage(task.toString());
        } else {
            task.unmark();
            ui.unmarkMessage(task.toString());
        }
    }

    public boolean isExit() {
        return false;
    }
}
