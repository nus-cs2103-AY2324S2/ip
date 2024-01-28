public class CommandScanner {
    public enum Commands {TODO, DEADLINE, EVENT, LIST, MARK, UNMARK, DELETE, BYE}

    public static String scanCommand(String[] inputs) throws DukeException {

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
                    if (inputs.length > 1) {
                        throw new DukeException("No additional characters needed after list! Appreciate the extra-ness but please try again! :)");
                    }
                    return Commands.LIST.name();
                case "MARK":
                    return Commands.MARK.name();
                case "UNMARK":
                    return Commands.UNMARK.name();
                case "DELETE":
                    return Commands.DELETE.name();
                case "BYE":
                    return Commands.BYE.name();
                default:
                    throw new DukeException("Invalid command used! I am not powered by GPT-4 so do lower your expectations heh :)");
            }
        } else {
            throw new IllegalArgumentException("Empty input, please enter a command.");
        }
    }

}
