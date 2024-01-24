public class EmptyDescException extends Exception {
    public EmptyDescException() {}

    public EmptyDescException(String m) {
        super(m);
    }

    @Override
    public String toString() {
        return "   Uh oh! Looks like you forgot a description of the task!";
    }
}
