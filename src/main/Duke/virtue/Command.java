package virtue;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

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

    protected CommandType type;
    protected int index;
    protected String description;
    protected VirtueDateTime by;
    protected VirtueDateTime from;
    protected VirtueDateTime to;

    // Creates a command from the user input.
    public Command(String input) throws VirtueException {
        this.type = Parser.getCommandType(input);

        if (this.type.hasIndex) {
            this.index = Parser.getIndex(input);
        }

        if (this.type.hasDescription) {
            this.description = Parser.getDescription(input);
        }

        if (this.type == CommandType.DEADLINE) {
            this.by = Parser.getBy(input);
        }

        if (this.type == CommandType.EVENT) {
            this.from = Parser.getFrom(input);
            this.to = Parser.getTo(input);
        }
    }

    // Checks if a command is of type "bye".
    public boolean isBye() {
        return type == CommandType.BYE;
    }
}
