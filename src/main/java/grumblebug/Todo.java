package grumblebug;

public class Todo extends Task {
    /**
     * Constructor for To-do type task.
     * @param done Doneness of task.
     * @param description Description of task.
     */
    public Todo(boolean done, String description) {
        this.description = description;
        this.isDone = done;
    }

    /**
     * To return full status representing a To-do task.
     * @return a String that is readable easily, showing task info.
     */
    public String getFullStatus() {
        String status = ". [" + this.getStatusIcon() + "][" + this.getTaskType() + "] " + this.description;
        return status;
    }

    /**
     * Representation for task type, for text storage purposes.
     * @return a Character representing the To-do task type.
     */
    @Override
    public char getTaskType() {
        return 'T';
    }
}
