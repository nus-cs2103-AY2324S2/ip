package duke.commands;

/**
 * Represents a set of {@code Command}s that can be queried by their names. Analogous to a
 * <code>HashMap<String, Command></code> with relevant operations supported.
 */
public class CommandList {
    /**
     * Gets the command associated with the given name. Similar to HashMap::get, but it throws if there is nothing
     * found.
     * @param cmdName name of the command to query.
     *
     * @return the Command associated with the name
     *
     * @throws DukeCommandNotFoundException if there is nothing found.
     */
    public Command get(String cmdName) throws DukeCommandNotFoundException {
        switch (cmdName) {
        case "list":
            return new ListCommand();
        case "bye":
            return new ByeCommand();
        case "mark":
            return new MarkCommand();
        case "unmark":
            return new UnmarkCommand();
        case "todo":
            return new ToDoCommand();
        case "deadline":
            return new DeadlineCommand();
        case "event":
            return new EventCommand();
        case "delete":
            return new DeleteCommand();
        case "find":
            return new FindCommand();
        default:
            throw new DukeCommandNotFoundException();
        }
    }

}
