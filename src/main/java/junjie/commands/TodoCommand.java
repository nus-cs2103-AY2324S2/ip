package junjie.commands;

import junjie.TaskList;
import junjie.Ui;
import junjie.exceptions.InvalidArgumentException;
import junjie.tasks.Task;
import junjie.tasks.TodoTask;

/**
 * Represents a command to add a todo task to the task list.
 */
public class TodoCommand extends Command {
    /**
     * The command word to add a todo task.
     */
    public static final String COMMAND_WORD = "todo";
    private static final String MESSAGE = "added this task for you liao:\n%s";

    private final String name;
    private final String[] tags;

    /**
     * Constructs a command to add a todo task with the given name and tags.
     *
     * @param name The name of the task.
     * @param tags The tags of the task.
     */
    public TodoCommand(String name, String[] tags) {
        this.name = name;
        this.tags = tags;
    }

    @Override
    public String execute(TaskList tasks, Ui ui) {
        try {
            Task task = new TodoTask(name, tags);
            tasks.add(task);
            return String.format(MESSAGE, task);
        } catch (InvalidArgumentException e) {
            return e.getMessage();
        }
    }
}
