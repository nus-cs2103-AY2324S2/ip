package virtue;

public class Command {
    // The available types of commands in Virtue.
    public enum CommandType {
        BYE ("bye", false, false),
        LIST ("list", false, false),
        MARK ("mark", true, false),
        UNMARK ("unmark", true, false),
        TODO ("todo", false, true),
        DEADLINE ("deadline", false, true),
        EVENT ("event", false, true),
        DELETE("delete", true, false);

        private final String string;
        private final boolean hasIndex;
        private final boolean hasDescription;

        CommandType(String string,
                    boolean hasIndex,
                    boolean hasDescription) {
            this.string = string;
            this.hasIndex = hasIndex;
            this.hasDescription = hasDescription;
        }

        @Override
        public String toString() {
            return this.string;
        }
    }

    private String command;
    protected CommandType type;
    protected int index;
    protected String description;
    protected String by;
    protected String from;
    protected String to;

    public Command(String command) throws VirtueException {
        this.command = command;
        this.type = getCommandType();

        if (this.type.hasIndex) {
            this.index = getIndex();
        }

        if (this.type.hasDescription) {
            this.description = getDescription();
        }

        if (this.type == CommandType.DEADLINE) {
            this.by = getBy();
        }

        if (this.type == CommandType.EVENT) {
            this.from = getFrom();
            this.to = getTo();
        }
    }

    // Gets the type of the command, which is its first word.
    private CommandType getCommandType() throws UnknownCommandTypeException {
        String firstWord = command.split(" ", 2)[0];

        for (CommandType type : CommandType.values()) {
            if (firstWord.equals(type.toString())) {
                return type;
            }
        }

        throw new UnknownCommandTypeException();
    }

    // Gets the index input by the user for a mark/unmark/delete command.
    private int getIndex() throws EmptyIndexException, IndexFormatException {
        try {
            return Integer.parseInt(command.split(" ", 2)[1]);
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new EmptyIndexException(type.toString());
        } catch (NumberFormatException e) {
            throw new IndexFormatException(type.toString());
        }
    }

    // Gets the description for a todo/deadline/event command.
    private String getDescription() throws EmptyDescriptionException {
        String description;
        String firstWordRemoved;

        try {
            firstWordRemoved = command.split(" ", 2)[1];
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new EmptyDescriptionException(type.toString());
        }

        switch (type) {
            case DEADLINE:
                description = firstWordRemoved.split("/by", 2)[0];
                break;
            case EVENT:
                description = firstWordRemoved.split(" /from ", 2)[0];
                break;
            default: // case TODO
                description = firstWordRemoved;
        }

        return description;
    }

    // Gets the deadline for a deadline command.
    private String getBy() {
        String firstWordRemoved = command.split(" ", 2)[1];
        return firstWordRemoved.split(" /by ", 2)[1];
    }

    // Gets the start for a deadline command.
    private String getFrom() {
        String firstWordRemoved = command.split(" ", 2)[1];
        String fromAndTo = firstWordRemoved.split(" /from ", 2)[1];
        return fromAndTo.split(" /to ", 2)[0];
    }

    // Gets the end for a deadline command.
    private String getTo() {
        String firstWordRemoved = command.split(" ", 2)[1];
        String fromAndTo = firstWordRemoved.split(" /from ", 2)[1];
        return fromAndTo.split(" /to ", 2)[1];
    }

    public boolean isBye() {
        return type == CommandType.BYE;
    }
}
