package fishstock;

/**
 * Encapsulates the keywords for running respective commands.
 */
public enum Command {
    INVALID, TODO("T"), DEADLINE("D"), EVENT("E"),
    DELETE, FIND, MARK, UNMARK, UNDO, LIST, HELP, BYE;

    private String shortened;

    Command() {
    }

    Command(String shortened) {
        this.shortened = shortened;
    }

    /**
     * Gets the short form of a Command.
     */
    public String getShortened() {
        return shortened;
    }

    /**
     * Finds the Command with the specified shortened string.
     *
     * @param shortened The shortened command in String.
     * @return The Command of enum type.
     */
    public static Command findShortened(String shortened) {
        for (Command command : Command.values()) {
            if (shortened.equals(command.shortened)) {
                return command;
            }
        }
        return Command.INVALID;
    }

    /**
     * Gets the list of all Commands.
     */
    public static String toList() {
        Command[] commands = Command.values();
        String result = commands[1].toString().toLowerCase(); // Remove INVALID Command
        for (int i = 2; i < commands.length; i++) {
            result += ", " + commands[i].toString().toLowerCase();
        }
        return result;
    }
}
