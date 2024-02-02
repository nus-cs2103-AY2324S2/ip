public class DukeInvalidArgument extends DukeException{

    String args;
    public DukeInvalidArgument(String args) {
        args = args;
    }
    @Override
    public String toString() {
        return String.format("%s The argument(s):\"%s\" you entered are invalid!", super.toString(), args);
    }
}
