package alpaca.tasks;

/**
 * A type of task which signifies when something is due
 **/
public class Deadline extends Task{
    /**
     * Creates a Deadline object
     **/
    public Deadline(String name) {
        super(name);
        setType("D");
    }

    /**
     * Creates a Deadline object, allowing the preset of the done option
     **/
    public Deadline(Boolean done, String name) {
        super(name);
        setType("D");
        if (done) setDone();
    }
}
