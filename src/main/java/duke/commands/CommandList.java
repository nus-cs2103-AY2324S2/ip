package duke.commands;

import java.util.HashMap;

/**
 * Represents a set of {@code Command}s that can be queried by their names. Analogous to a
 * <code>HashMap<String, Command></code> with relevant operations supported.
 */
public class CommandList {
    /** The command name-to-Command mapping */
    final HashMap<String, Command> map = new HashMap<>();

    /**
     * Binds the given command to the given name. similar to HashMap::put but does not return a value.
     * @param name The name of the command.
     * @param command the command object.
     */
    public void put(String name, Command command) {
        map.put(name, command);
    }

    /**
     * Checks whether there is a command associated with the given name. Similar to HashMap::containsKey.
     * @param arg name of the command to query.
     */
    public boolean has(String arg) {
        return map.containsKey(arg);
    }

    /**
     * Gets the command associated with the given name. Similar to HashMap::get, but it throws if there is nothing
     * found.
     * @param arg name of the command to query.
     *
     * @return the Command associated with the name
     *
     * @throws DukeCommandNotFoundException if there is nothing found.
     */
    public Command get(String arg) throws DukeCommandNotFoundException {
        if (this.has(arg)) {
            return map.get(arg);
        } else {
            throw new DukeCommandNotFoundException();
        }
    }
}
