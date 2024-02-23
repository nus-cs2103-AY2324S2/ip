package junjie.commands;

import java.time.DateTimeException;

import junjie.TaskList;
import junjie.Ui;
import junjie.exceptions.InvalidArgumentException;
import junjie.tasks.EventTask;
import junjie.tasks.Task;

/**
 * Represents a command to add an event task.
 */
public class EventCommand extends Command {
    /**
     * The command word to add an event task.
     */
    public static final String COMMAND_WORD = "event";
    private static final String MESSAGE = "added this task for you liao:\n%s";

    private final String name;
    private final String from;
    private final String to;
    private final String[] tags;

    /**
     * Constructs an event command with the specified name, from, to and tags.
     *
     * @param name The name of the task.
     * @param from The start time of the task.
     * @param to The end time of the task.
     * @param tags The tags of the task.
     */
    public EventCommand(String name, String from, String to, String[] tags) {
        this.name = name;
        this.from = from;
        this.to = to;
        this.tags = tags;
    }

    @Override
    public String execute(TaskList tasks, Ui ui) {
        try {
            Task task = new EventTask(name, from, to, tags);
            tasks.add(task);
            return String.format(MESSAGE, task);
        } catch (DateTimeException | InvalidArgumentException e) {
            return e.getMessage();
        }
    }
}
