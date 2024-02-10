package dino.command;

import java.io.IOException;

import dino.DinoException;
import dino.Storage;
import dino.TaskList;


/**
 * Represents a command to list all the tasks from the task list.
 */
public class ListCommand extends Command {
    @Override
    public String execute(TaskList taskList, Storage storage) throws IOException, DinoException {
        return taskList.listTask();
    }
}
