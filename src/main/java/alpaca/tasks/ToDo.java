package alpaca.tasks;

/**
 * A type of task which signifies when you have to do something
 **/
public class ToDo extends Task {
    /**
     * Creates a ToDo object
     **/
    public ToDo(String name) {
        super(name);
        setType("T");
    }

    /**
     * Creates a ToDo object, allowing the preset of the done option
     **/
    public ToDo(Boolean done, String name) {
        super(name);
        setType("T");
        if (done) setDone();
    }
}
