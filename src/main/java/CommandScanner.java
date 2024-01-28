public class CommandScanner {
    public enum Commands {TODO, DEADLINE, EVENT, LIST, MARK, UNMARK, BYE}

    public static String scanCommand(String[] inputs) {

        if (inputs.length > 0) {
            String firstWord = inputs[0].toUpperCase();

            switch (firstWord) {
                case "TODO":
                    return Commands.TODO.name();
                case "DEADLINE":
                    return Commands.DEADLINE.name();
                case "EVENT":
                    return Commands.EVENT.name();
                case "LIST":
                    return Commands.LIST.name();
                case "MARK":
                    return Commands.MARK.name();
                case "UNMARK":
                    return Commands.UNMARK.name();
                case "BYE":
                    return Commands.BYE.name();
                default:
                    throw new IllegalArgumentException("Invalid command, please try again.");
            }
        } else {
            throw new IllegalArgumentException("Empty input, please enter a command.");
        }
    }

}
