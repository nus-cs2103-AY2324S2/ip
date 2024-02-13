package duke.dukeexception;

public class DukeInvalidArgument extends DukeException {
    private String args;
    public DukeInvalidArgument(String args) {
        this.args = args;
    }
    @Override
    public String toString() {
        return String.format("%s The argument(s):\"%s\" you entered are invalid!", super.toString(), args);
    }
}
