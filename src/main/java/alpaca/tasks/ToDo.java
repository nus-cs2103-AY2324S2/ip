package alpaca.tasks;

public class ToDo extends Task {
    public ToDo(String name) {
        super(name);
        setType("T");
    }

    public ToDo(Boolean done, String name) {
        super(name);
        setType("T");
        if (done) setDone();
    }
}
