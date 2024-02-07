package duke;

public class DeleteCommand implements Command {
    int index;

    DeleteCommand(int index) {
        this.index = index;
    }

    public void execute(TaskList tasks, Ui ui) throws DukeException {
        if (index + 1 > tasks.size()) {
            throw new DukeException("You only have " + tasks.size() + " tasks in the list.");
        }
        Task task = tasks.get(index);
        tasks.remove(index);
        ui.deleteMessage(task.toString(), tasks.size());
    }

    public boolean isExit() {
        return false;
    }
}
