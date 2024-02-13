package duke.dukeexception;

public class DukeInvalidArgument extends DukeException {
    private String arguments;
    public DukeInvalidArgument(String args) {
        arguments = args;
    }
    @Override
    public String toString() {
        return String.format("%s The argument(s):\"%s\" you entered are invalid!", super.toString(), arguments);
    }
}
