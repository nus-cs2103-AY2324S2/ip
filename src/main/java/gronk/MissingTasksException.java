package gronk;

public class MissingTasksException extends Exception {
    public MissingTasksException() {}

    public MissingTasksException(String m) {
        super(m);
    }

    @Override
    public String toString() {
        return "\tFile not found!";
    }
}