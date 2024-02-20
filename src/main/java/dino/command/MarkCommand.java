package dino.command;

import java.io.IOException;

import dino.DinoException;
import dino.Storage;
import dino.TaskList;
import dino.task.Task;

/**
 * Represents a command to mark a task from the task list.
 */
public class MarkCommand extends Command {
    private int index;
    public MarkCommand(int index) {
        this.index = index;
    }
    @Override
    public String execute(TaskList taskList, Storage storage) throws IOException, DinoException {
        if (index > taskList.size()) {
            throw new DinoException("Uh oh, we do not have a task assigned to that number.");
        } else {
            Task completed = taskList.get(index - 1);
            return completed.markAsDone();
        }
    }
}
