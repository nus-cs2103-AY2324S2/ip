package alpaca.tasks;

/**
 * A type of task which signifies when you have to do something
 **/
public class ToDo extends Task {
    /**
     * Creates a ToDo object
     * 
     * @param name What the task is named
     **/
    public ToDo(String name) {
        super(name);
        setType("T");
    }

    /**
     * Creates a ToDo object, allowing the preset of the done option
     * 
     * @param done If the task should be
     * @param name What the task is named
     **/
    public ToDo(Boolean done, String name) {
        super(name);
        setType("T");
        if (done) {
            setDone();
        }
    }
}
