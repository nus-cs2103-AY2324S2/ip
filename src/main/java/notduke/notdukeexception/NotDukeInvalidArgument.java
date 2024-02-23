package notduke.notdukeexception;

public class NotDukeInvalidArgument extends NotDukeException {
    private String args;
    public NotDukeInvalidArgument(String args) {
        this.args = args;
    }
    @Override
    public String toString() {
        return String.format("%s The argument(s):\"%s\" you entered are invalid!", super.toString(), args);
    }
}
