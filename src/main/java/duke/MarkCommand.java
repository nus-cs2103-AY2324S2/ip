package duke;

public class MarkCommand implements Command {
    int index;
    boolean isDone;

    MarkCommand(int index, boolean isDone) {
        this.index = index;
        this.isDone = isDone;
    }

    /**
     * Marks or Unmarks the Task in the TaskList according to the index
     * and displays the UI message.
     *
     * @param tasks the TaskList for which the Task will be added to
     * @param ui the UI that will be used to display the message
     * @throws DukeException if the index is greater than the number of Task in the TaskList
     */
    public String execute(TaskList tasks, Ui ui) throws DukeException {
        if (index + 1 > tasks.size()) {
            throw new DukeException("You only have " + tasks.size() + " tasks in the list.");
        }
        Task task = tasks.get(index);
        if (isDone) {
            task.mark();
            return ui.markMessage(task.toString());
        } else {
            task.unmark();
            return ui.unmarkMessage(task.toString());
        }
    }

    public boolean isExit() {
        return false;
    }
}
