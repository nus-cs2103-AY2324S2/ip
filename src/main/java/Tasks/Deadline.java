package Tasks;

public class Deadline extends Task{
    public Deadline(String name) {
        super(name);
        setType("D");
    }

    public Deadline(Boolean done, String name) {
        super(name);
        setType("D");
        if (done) setDone();
    }
}
