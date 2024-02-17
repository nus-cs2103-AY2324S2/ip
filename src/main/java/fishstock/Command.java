package fishstock;

/**
 * Encapsulates the keywords for running respective commands.
 */
public enum Command {
    INVALID, BYE, LIST, MARK, UNMARK, DELETE, FIND, UNDO,
    TODO("T"), DEADLINE("D"), EVENT("E");

    private String shortened;

    Command() {
    }

    Command(String shortened) {
        this.shortened = shortened;
    }

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
        for (Command keyword : Command.values()) {
            if (shortened.equals(keyword.shortened)) {
                return keyword;
            }
        }
        return Command.INVALID;
    }
}
