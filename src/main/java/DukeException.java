public class DukeException extends Exception{
    public DukeException(String message) {
        super(message);
    }
    @Override
    public String toString() {
        return "____________________________________________________________\n"
                + "     " + super.getMessage() + "\n"
                + "____________________________________________________________";
    }
}
