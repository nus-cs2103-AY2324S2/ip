package duke.dukeexception;

public class DukeMissingArgument extends DukeException {
    private int need;
    private String command;

    public DukeMissingArgument(int nd, String cmd) {
        need = nd;
        command = cmd;
    }
    @Override
    public String toString() {
        return String.format("%s There are missing argument(s), %d argument(s) is needed for %s",
                super.toString(), need, command);
    }
}
