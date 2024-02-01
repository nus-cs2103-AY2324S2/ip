package Duke;

import java.io.IOException;

public class DeleteCommand implements Command {
    private int taskIndex;

    public DeleteCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            if (taskIndex >= 0 && taskIndex < (tasks.getTasks()).size()) {
                Task taskBeforeDeletion = tasks.getTask(taskIndex);
                tasks.deleteTask(taskIndex);
                storage.saveTasksToFile(tasks);
                ui.showDeleteMessage(tasks.getTasks(), taskBeforeDeletion);
            } else {
                ui.invalidTaskIndex();
            }
        } catch (IOException e) {
            ui.showIOExceptionMessage();
        }
    }
}

