package klee.command;

import java.time.LocalDateTime;

import klee.Storage;
import klee.TaskList;
import klee.Ui;
import klee.task.Task;

/**
 * Represents the command for Klee to create a new Event and add it into tasks.
 */
public class Event extends Command {
    protected String description;
    protected LocalDateTime from;
    protected LocalDateTime to;

    /**
     * Constructor for Event class.
     *
     * @param description
     * @param from
     * @param to
     */
    public Event(String description, LocalDateTime from, LocalDateTime to) {
        this.description = description;
        this.from = from;
        this.to = to;
    }

    /**
     * Execute the command to create Event.
     * Add new instance of Event into tasks.
     * Invoke ui to show that Event was added.
     * Invoke storage to save tasks into txt file.
     *
     * @param ui
     * @param storage
     * @param tasks
     */
    @Override
    public void runCommand(Ui ui, Storage storage, TaskList tasks) {
        Task task = new klee.task.Event(description, from, to);
        tasks.add(task);
        ui.showCreation(task, tasks.size());
        storage.saveTasks(tasks);
    }

    /**
     * Check if obj is equivalent to this instance.
     *
     * @param obj
     * @return If obj is instance of Event with the same fields.
     */
    @Override
    public boolean equals(Object obj) {
        if (obj.getClass() == Event.class) {
            boolean hasSameFields = this.description.equals(((Event) obj).description)
                    && this.from.equals((((Event) obj).from)) && this.to.equals((((Event) obj).to));
            return (hasSameFields);
        } else {
            return false;
        }
    }
}
