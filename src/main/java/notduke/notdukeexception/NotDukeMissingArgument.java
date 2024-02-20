package notduke.notdukeexception;

public class NotDukeMissingArgument extends NotDukeException {
    private String command;

    public NotDukeMissingArgument(String command) {
        this.command = command;
    }

    private int numOfArgs(String command) {
        switch (command) {
        case "check":
        case "delete":
        case "find":
        case "mark":
        case "todo":
        case "unmark":
            return 1;
        case "deadline":
            return 2;
        case "event":
            return 3;
        default:
            return -1;
        }
    }

    @Override
    public String toString() {
        return String.format("%s There are missing argument(s), %d argument(s) is needed for %s",
                super.toString(), numOfArgs(command), command);
    }
}
