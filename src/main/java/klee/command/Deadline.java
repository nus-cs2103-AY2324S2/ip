package klee.command;

import java.time.LocalDateTime;

import klee.Storage;
import klee.TaskList;
import klee.Ui;
import klee.task.Task;

/**
 * Represents the command to create a deadline and add it to the list of tasks.
 */
public class Deadline extends Command {
    protected String description;
    protected LocalDateTime deadline;

    /**
     * Constructor for Deadline class.
     *
     * @param description
     * @param deadline
     */
    public Deadline(String description, LocalDateTime deadline) {
        this.description = description;
        this.deadline = deadline;
    }

    /**
     * To execute the command.
     * Creates new instance of Deadline class and adds it to tasks.
     * Invokes the ui to show user that task has been added.
     * Invokes the storage to write the current updated tasks into txt file.
     *
     * @param ui
     * @param storage
     * @param tasks
     */
    @Override
    public void runCommand(Ui ui, Storage storage, TaskList tasks) {
        Task task = new klee.task.Deadline(description, deadline);
        tasks.add(task);
        ui.showCreation(task, tasks.size());
        storage.saveTasks(tasks);
    }

    /**
     * Check if given obj is an instance of Deadline with the same description and deadline.
     *
     * @param obj
     * @return If obj has the same fields.
     */
    @Override
    public boolean equals(Object obj) {
        if (obj.getClass() == Deadline.class) {
            boolean hasSameFields = this.description.equals(((Deadline) obj).description)
                    && this.deadline.equals((((Deadline) obj).deadline));
            return (hasSameFields);
        } else {
            return false;
        }
    }
}
