public class Commands {
    public static final String EXIT = "exit";
    public static final String LIST = "list";
    public static final String MARK = "mark";
    public static final String UNMARK = "unmark";
    public static final String TODO = "todo";
    public static final String DEADLINE = "deadline";
    public static final String EVENT = "event";

    public static String[] extractParameters(String parametersString, String[] parameters) {
        // TODO: return a hashmap
        int n = parameters.length;
        String[] result = new String[n + 1];

        String[] splitString = new String[] { parametersString };
        for (int i = n - 1; i >= 0; i--) {
            splitString = splitString[0].split(" /" + parameters[i] + ' ', 2);
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
        String[] parameters;
        switch (commandArgs[0]) {
            case Commands.TODO:
                parameters = Commands.extractParameters(commandArgs[1], new String[] {});
                Bob.handleAdd(commandArgs[0], parameters);
                break;
            case Commands.DEADLINE:
                parameters = Commands.extractParameters(commandArgs[1], new String[] { "by" });
                Bob.handleAdd(commandArgs[0], parameters);
                break;
            default:
                parameters = Commands.extractParameters(commandArgs[1], new String[] { "from", "to" });
                Bob.handleAdd(commandArgs[0], parameters);
        }
    }

    public static void processCommands(String[] commandArgs) throws InvalidCommandException {
        switch (commandArgs[0]) {
            case Commands.LIST:
                Bob.handleList();
                break;
            case Commands.MARK:
                Bob.handleMark(Integer.parseInt(commandArgs[1]) - 1, true);
                break;
            case Commands.UNMARK:
                Bob.handleMark(Integer.parseInt(commandArgs[1]) - 1, false);
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
                throw new InvalidCommandException();
        }
    }
}
