package duke;

/**
 * An abstract class to handle the general aspects of the 3 types of task
 */
public abstract class Task {

    /**
     * The name of the task
     */
    protected String name;

    /**
     * Whether the task is done
     */
    protected boolean isDone;

    /**
     * The string representation of the task
     *
     * @return the string representation of the task, which differs based on isDone.
     */
    @Override
    public String toString() {
        String str = "";
        if (isDone) {
            str = String.format("[X] %s", name);
        } else {
            str = String.format("[ ] %s", name);
        }
        return str;
    }

    /**
     * Marks a task as completed
     */
    public void mark() {
        isDone = true;
        System.out.println("Good job! You have completed this task:");
        System.out.println(toString());
    }

    /**
     * Marks a task as not completed
     */
    public void unmark() {
        isDone = false;
        System.out.println("Alright. This task has been unmarked");
        System.out.println(toString());
    }

    /**
     * Gets the name of the task
     *
     * @return the name of the task
     */
    public String getName() {
        return this.name;
    }

    /**
     * Converts a task into its text representation in the save file.
     * The parent function for all convertToText methods in the 3 subclasses.
     * This specific method is never called, only the child methods.
     *
     * @return the text representation of the task
     */
    public String convertToText() {
        return "";
    }
}
