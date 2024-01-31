public class Parser {
    public Mona.Command getCurrentCommand(String[] input) throws MonaException{
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
                throw new MonaException("Mona does not recognise this command!");
        }
    }
    public String[] getDetails(Mona.Command command, String input) {
        String[] details = new String[3];
        switch (command) {
            case TODO:
                details[0] = input.substring(5);
                break;
            case DEADLINE:
                String rest = input.substring(9);
                String[] parts = rest.split(" /by ");
                details[0] = parts[0];
                details[1] = parts[1];
                break;
            case EVENT:
                String[] subDetails = input.substring(6).split(" /from ");
                String[] startAndEnd = subDetails[1].split(" /to ");
                details[0] = startAndEnd[0];
                details[1] = startAndEnd[1];
                details[2] = startAndEnd[2];
                break;
            default:
                break;
        }
        return details;
    }
}
