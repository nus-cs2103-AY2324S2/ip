public class DukeException extends Exception {
    public DukeException() {}

    public DukeException(String m) {
        super(m);
    }

    @Override
    public String toString() {
        return "   I'm sorry, I didn't recognize that command. Please try again!";
    }
}
