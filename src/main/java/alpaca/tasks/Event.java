package alpaca.tasks;

/**
 * A type of task which signifies a period of time you will be busy
 **/
public class Event extends Task {
    /**
     * Creates a Event object
     * 
     * @param name What the task is named
     **/
    public Event(String name) {
        super(name);
        setType("E");
    }

    /**
     * Creates a Event object, allowing the preset of the done option
     * 
     * @param done If the task should be
     * @param name What the task is named
     **/
    public Event(boolean done, String name) {
        super(name);
        setType("E");
        if (done) {
            setDone();
        }
    }
}
