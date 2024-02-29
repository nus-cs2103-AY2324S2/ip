package command;

import common.DukeException;
import task.Task;
import task.TaskList;

/**
 * {@inheritDocs}
 * Deletes a task from a tasklist.
 */
public class DeleteCommand extends Command {
    private TaskList taskList;
    private int taskIndex;

    /**
     * Creates an instance of DeleteCommand.
     */
    public DeleteCommand(TaskList taskList, int taskIndex) {
        this.taskList = taskList;
        this.taskIndex = taskIndex;
    }

    /**
     * {@inheritDocs}
     * Deletes a task from a tasklist.
     *
     * @throws DukeException If the command cannot be executed.
     */
    @Override
    public String execute() {
        Task t = taskList.remove(taskIndex);
        assert !taskList.contains(t) : t.getTaskName() + " is not removed.";


        String s = "Noted, I've removed this task:\n"
                + " " + t.toString()
                + "\nNow you have " + taskList.size() + " tasks in the list.";
        
        System.out.println(s);
        return s;
    }

    /**
     * {@inheritDoc}
     *
     * @return True if program will exit.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
