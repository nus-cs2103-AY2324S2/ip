package alpaca.tasks;

/**
 * A type of task which signifies when something is due.
 **/
public class Deadline extends Task {
    /**
     * Creates a Deadline object.
     *
     * @param name What the task is named
     **/
    public Deadline(String name) {
        super(name);
        setType("D");
    }

    /**
     * Creates a Deadline object, allowing the preset of the done option.
     *
     * @param isDone If the task should be
     * @param name   What the task is named
     **/
    public Deadline(boolean isDone, String name) {
        super(name);
        setType("D");
        if (isDone) {
            setDone();
        }
    }
}
