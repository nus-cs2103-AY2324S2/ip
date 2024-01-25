public class DeleteCommand extends Command {
    private final int taskNumber;

    DeleteCommand(int taskNumber) {
        super("");
        this.taskNumber = taskNumber;
    }

    @Override
    void execute(TaskList taskList, Ui ui) throws MikeException {
        int taskIndex = taskNumber - 1;
        if (taskList.isEmpty()) {
            throw new MikeException("There are no tasks to remove.");
        } else if (taskIndex >= taskList.size() || taskIndex < 0) {
            throw new MikeException("That task doesn't exist. Please enter a number in the range 1-" + taskList.size() + ".");
        }
        Task task = taskList.get(taskIndex);
        taskList.remove(taskIndex);
        String message =
                "Noted! I've removed this task:\n  " +
                task + "\n"
                + "Now you have " + taskList.size() + " tasks in the list.";
        ui.display(message);
    }

    @Override
    boolean isExit() {
        return false;
    }
}
