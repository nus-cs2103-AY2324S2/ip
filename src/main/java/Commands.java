public class Commands {
    public static final String EXIT = "exit";
    public static final String LIST = "list";
    public static final String MARK = "mark";
    public static final String UNMARK = "unmark";
    public static final String TODO = "todo";
    public static final String DEADLINE = "deadline";
    public static final String EVENT = "event";

    public static String[] extractParameters(String parametersString,
                                             String[] parameters) throws ParameterNotFoundException {
        // TODO: return a HashMap rather than a String[]
        int n = parameters.length;
        String[] result = new String[n + 1];

        String[] splitString = new String[] { parametersString };
        for (int i = n - 1; i >= 0; i--) {
            splitString = splitString[0].split(" /" + parameters[i] + ' ', 2);
            if (splitString.length == 1) {
                // This implies the last missing parameter will be displayed, rather than the first
                // "deadline /by 11/10/2019 5pm" will trigger parameter "by" not found
                // "event /from 2/10/2019 2pm /to 4pm" will trigger parameter "from" not found
                throw new ParameterNotFoundException(parameters[i]);
            }
            result[i + 1] = splitString[1];
        }

        result[0] = splitString[0];

        return result;
    }

    public static void processAddCommands(String[] commandArgs) throws EmptyDescriptionException {
        if (commandArgs.length == 1) {
            throw new EmptyDescriptionException(commandArgs[0]);
        }

        // TODO: map each task type to a list of parameters
        // Undefined behaviour when there are multiple instances of the same parameter
        // e.g. event project meeting /from 2/10/2019 2pm /to 4pm /from 4/10/2019 /to 11/10/2019
        try {
            String[] parameters;
            switch (commandArgs[0]) {
                case Commands.TODO:
                    parameters = Commands.extractParameters(commandArgs[1], new String[]{});
                    Bob.handleAdd(commandArgs[0], parameters);
                    break;
                case Commands.DEADLINE:
                    parameters = Commands.extractParameters(commandArgs[1], new String[]{ "by" });
                    Bob.handleAdd(commandArgs[0], parameters);
                    break;
                default:
                    parameters = Commands.extractParameters(commandArgs[1], new String[] { "from", "to" });
                    Bob.handleAdd(commandArgs[0], parameters);
            }
        } catch (ParameterNotFoundException e) {
            Replies.print(e.getMessage());
        }
    }

    public static void processCommands(String[] commandArgs) {
        switch (commandArgs[0]) {
        case Commands.LIST:
            Bob.handleList();
            break;
        case Commands.UNMARK:
            // Fallthrough
        case Commands.MARK:
            try {
                Bob.handleMark(Integer.parseInt(commandArgs[1]) - 1, commandArgs[0].equals(Commands.MARK));
            } catch (NumberFormatException e) {
                // The more "correct" way is to throw an InvalidTaskIndexException?
                Replies.print(Replies.INVALID_TASK_INDEX + commandArgs[1]);
            } catch (ArrayIndexOutOfBoundsException e) {
                // TODO: processParameterisedCommands, which is any command other than exit and list
                Replies.print(String.format(Replies.EMPTY_DESCRIPTION, commandArgs[0]));
            }
            break;
        case Commands.TODO:
            // Fallthrough
        case Commands.DEADLINE:
            // Fallthrough
        case Commands.EVENT:
            try {
                processAddCommands(commandArgs);
            } catch (EmptyDescriptionException e) {
                Replies.print(e.getMessage());
            }
            break;
        default:
            try {
                throw new InvalidCommandException();
            } catch (InvalidCommandException e) {
                Replies.print(e.getMessage());
            }
        }
    }
}
