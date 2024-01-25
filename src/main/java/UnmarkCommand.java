public class UnmarkCommand extends Command {
    private final int taskNumber;

    UnmarkCommand(int taskNumber) {
        super("");
        this.taskNumber = taskNumber;
    }

    @Override
    void execute(TaskList taskList, Ui ui) throws MikeException {
        int taskIndex = taskNumber - 1;
        if (taskList.isEmpty()) {
            throw new MikeException("There are no tasks to mark. Please add a task first.");
        } else if (taskIndex >= taskList.size() || taskIndex < 0) {
            throw new MikeException("That's suspicious. Please enter a number in the range 1-" + taskList.size() + ".");
        }
        Task task = taskList.get(taskIndex);
        task.markAsNotDone();
        String message = "I've marked this task as not done:\n  " + task;
        ui.display(message);
        /*
           TODO:
            1. Check that task is or is not done.
        */
    }

    @Override
    boolean isExit() {
        return false;
    }
}
