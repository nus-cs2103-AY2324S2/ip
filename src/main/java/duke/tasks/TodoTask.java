package duke.tasks;

import duke.TaskList;

/**
 * The TodoTask class represents a task with a todo description.
 * Inherits from the Task class.
 */
public class TodoTask extends Task {

    /**
     * Constructs a TodoTask object with the specified description.
     *
     * @param desc the description of the todo task
     */
    public TodoTask(String desc) {
        super(desc);
    }

    /**
     * Constructs a TodoTask object with the specified description and completion status.
     *
     * @param desc the description of the todo task
     * @param isDone the completion status of the todo task ("1" for done, "0" for not done)
     */
    public TodoTask(String desc, String isDone) {
        super(desc, isDone);
    }

    /**
     * Gets the status icon of the todo task.
     *
     * @return the status icon of the todo task ("[T][X]" if done, "[T][ ]" if not done)
     */
    public String getStatusIcon() {
        return (this.isDone() ? "[T][X] " : "[T][ ] ");
    }

    /**
     * Returns a string representation of the todo task.
     *
     * @return a string representation of the todo task, including its status icon and description
     */
    public String toString() {
        return this.getStatusIcon() + this.getDesc();
    }

    /**
     * Returns a string in the CSV format to represent the todo task for saving to file.
     *
     * @param tasks the TaskList this task is saved in
     * @return a string in the CSV format representing the todo task ("T,1,description" if done,
     *     "T,0,description" if not done)
     */
    public String save(TaskList tasks) {
        String isDone = this.isDone() ? "1" : "0";
        return "T," + isDone + "," + this.getDesc();
    }
}
