package duke;

/**
 * Handles ToDo tasks, that only have a name.
 */
public class ToDo extends Task {

    /**
     * Creates a ToDo task
     *
     * @param name the name of the task
     * @param isDone whether the task is done
     */
    public ToDo(String name, boolean isDone) {
        this.name = name;
        this.isDone = isDone;
    }

    @Override
    public String toString() {
        String str = "";
        str = String.format("[T]%s", super.toString());
        return str;
    }

    @Override
    public String convertToText() {
        String str = "";
        str = String.format("%s todo %s", isDone, name);
        return str;
    }
}
