import java.io.IOException;

public class UnmarkCommand implements Command {
    private int taskIndex;

    public UnmarkCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            if (taskIndex >= 0 && taskIndex < (tasks.getTasks()).size()) {
                Task markedTask = tasks.getTask(taskIndex);
                tasks.markTaskAsNotDone(taskIndex);
                storage.saveTasksToFile(tasks);
                ui.showUnmarkTaskAsDone(markedTask);
            } else {
                ui.invalidTaskIndex();
            }
        } catch (IOException e) {
            ui.showIOExceptionMessage();
        }
    }
}
