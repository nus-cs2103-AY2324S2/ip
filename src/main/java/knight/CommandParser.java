package knight;

/**
 * Parses the user input to determine the command.
 */
public class CommandParser {
    /**
     * Parses the user input to determine the command.
     *
     * @param s The user input.
     * @return The command type.
     * @throws NonstandardCommandException If the command is not recognised.
     */
    static Command parseCommand(String s) throws NonstandardCommandException {
        if (s.equals("bye")) {
            return Command.BYE;
        } else if (s.equals("save")) {
            return Command.SAVE;
        } else if (s.equals("list")) {
            return Command.LIST;
        } else if (s.matches("mark [1-9]\\d*")) {
            return Command.MARK;
        } else if (s.matches("unmark [1-9]\\d*")) {
            return Command.UNMARK;
        } else if (s.matches("delete [1-9]\\d*")) {
            return Command.DELETE;
        } else if (s.matches("todo \\S.*")) {
            return Command.TODO;
        } else if (s.matches("deadline \\S.* /by \\S.*")) {
            return Command.DEADLINE;
        } else if (s.matches("event \\S.* /from \\S.* /to \\S.*")) {
            return Command.EVENT;
        } else if (s.matches("find \\S.*")) {
            return Command.FIND;
        } else if (s.matches("update [1-9]\\d* \\S.*")) {
            return Command.UPDATE;
        } else {
            handleNonstandardCommand(s);
            return null;
        }
    }

    /**
     * Handles nonstandard commands.
     *
     * @param s The user input.
     * @throws NonstandardCommandException If the command is not recognised.
     */
    private static void handleNonstandardCommand(String s) throws NonstandardCommandException {
        if (s.startsWith("bye")) { // nonstandard command
            throw new NonstandardCommandException("Thou canst bid me farewell simply with:\nbye");
        } else if (s.startsWith("list")) {
            throw new NonstandardCommandException("Though canst view thy list simply with:\nlist");
        } else if (s.matches("find")) {
            throw new NonstandardCommandException(
                    "Thou shouldst specify a keyword to search for in thy list of tasks:\n"
                            + "find [keyword]");
        } else if (s.startsWith("mark")) {
            throw new NonstandardCommandException(
                    "Take heed, for thou shouldst reference the task thou wishest to alter by its index:\n"
                            + "mark [index]");
        } else if (s.startsWith("unmark")) {
            throw new NonstandardCommandException(
                    "Take heed, for thou shouldst reference the task thou wishest to alter by its index:\n"
                            + "unmark [index]");
        } else if (s.startsWith("delete")) {
            throw new NonstandardCommandException(
                    "Take heed, for thou shouldst reference the task thou wishest to alter by its index:\n"
                            + "delete [index]");
        } else if (s.startsWith("todo")) {
            throw new NonstandardCommandException(
                    "Thou shouldst forge a todo task such as so:\n"
                            + "todo [description]");
        } else if (s.startsWith("deadline")) {
            throw new NonstandardCommandException(
                    "Thou shouldst forge a deadline task such as so:\n"
                            + "deadline [description] /by [time]");
        } else if (s.startsWith("event")) {
            throw new NonstandardCommandException(
                    "Thou shouldst forge an event task such as so:\n"
                            + "event [description] /from [start time] /to [end time]");
        } else if (s.startsWith("update")) {
            throw new NonstandardCommandException(
                    "Thou shouldst specify the index of the task thou wishest to update, "
                            + "followed by the format of creation command without keyword."
                            + "For example, to update a deadline, use:\n"
                            + "update [index] [new name] /by [new time]");

        } else {
            throw new NonstandardCommandException(
                    "I beg thine pardon, but I am clueless of the meaning of your utterance.");
        }
    }
}
