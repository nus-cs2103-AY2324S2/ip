package junjie.commands;

import java.time.DateTimeException;

import junjie.TaskList;
import junjie.Ui;
import junjie.exceptions.InvalidArgumentException;
import junjie.tasks.DeadlineTask;
import junjie.tasks.Task;

/**
 * Represents a command to add a deadline task.
 */
public class DeadlineCommand extends Command {
    /**
     * The command word to add a deadline task.
     */
    public static final String COMMAND_WORD = "deadline";
    private static final String MESSAGE = "added this task for you liao:\n%s";

    private final String name;
    private final String deadline;
    private final String[] tags;

    /**
     * Constructs a deadline command with the specified name, deadline and tags.
     *
     * @param name The name of the task.
     * @param deadline The deadline of the task.
     * @param tags The tags of the task.
     */
    public DeadlineCommand(String name, String deadline, String[] tags) {
        this.name = name;
        this.deadline = deadline;
        this.tags = tags;
    }

    @Override
    public String execute(TaskList tasks, Ui ui) {
        try {
            Task task = new DeadlineTask(name, deadline, tags);
            tasks.add(task);
            return String.format(MESSAGE, task);
        } catch (InvalidArgumentException | DateTimeException e) {
            return String.format(e.getMessage());
        }
    }
}
