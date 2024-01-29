public class DukeException extends Exception {
    public DukeException(String err) {
        super(err);
    }

    @Override
    public String toString() {
        String line = "\n-----------------------------\n";
        return line + super.toString() + line;
    }
}
