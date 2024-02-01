package Duke;

import java.io.IOException;

public class MarkCommand implements Command {
    private int taskIndex;

    public MarkCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            if (taskIndex >= 0 && taskIndex < (tasks.getTasks()).size()) {
                Task markedTask = tasks.getTask(taskIndex);
                tasks.markTaskAsDone(taskIndex);
                storage.saveTasksToFile(tasks);
                ui.showMarkTaskAsDone(markedTask);
            } else {
                ui.invalidTaskIndex();
            }
        } catch (IOException e) {
            ui.showIOExceptionMessage();
        }
    }
}

