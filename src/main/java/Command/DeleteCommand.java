package Command;

import Ping.Task;
import Ping.TaskList;
import Ping.UI;

/**
 * This class is used to delete a task from the list of tasks
 */
public class DeleteCommand extends Command {
    private int idx;

    public DeleteCommand(int idx) {
        this.idx = idx;
    }

    @Override
    public TaskList perform(TaskList tasks, UI ui) {
        try {
            Task deletedTask = tasks.deleteTask(this.idx);
            ui.deleteTaskMessage(this.idx, deletedTask, tasks.taskSize());
        } catch (Exception e) {
            System.out.println("Incorrect number");
        }
        return tasks;
    }
}
