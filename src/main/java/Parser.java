public class Parser {
    public Mona.Command getCurrentCommand(String[] input) {
        switch (input[0]) {
            case "bye":
                return Mona.Command.BYE;
            case "list":
                return Mona.Command.LIST;
            case "deadline":
                return Mona.Command.DEADLINE;
            case "todo":
                return Mona.Command.TODO;
            case "event":
                return Mona.Command.EVENT;
            case "mark":
                return Mona.Command.MARK;
            case "unmark":
                return Mona.Command.UNMARK;
            case "delete":
                return Mona.Command.DELETE;
            default:
                return null;
        }
    }
}
