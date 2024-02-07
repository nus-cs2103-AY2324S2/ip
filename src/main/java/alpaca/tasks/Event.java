package alpaca.tasks;

public class Event extends Task{
    public Event(String name) {
        super(name);
        setType("E");
    }

    public Event(Boolean done, String name) {
        super(name);
        setType("E");
        if (done) setDone();
    }
}
