public class EmptyListException extends Exception {
    public EmptyListException() {}

    public EmptyListException(String m) {
        super(m);
    }

    @Override
    public String toString() {
        return "\tNothing added yet!";
    }
}
