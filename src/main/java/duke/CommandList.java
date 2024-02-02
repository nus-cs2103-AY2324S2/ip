package duke;

import java.util.HashMap;

public class CommandList {
    final HashMap<String, Command> map = new HashMap<>();

    public void put(String name, Command command) {
        map.put(name, command);
    }

    public boolean has(String arg) {
        return map.containsKey(arg);
    }

    public Command get(String arg) throws DukeCommandNotFoundException {
        if (this.has(arg)) {
            return map.get(arg);
        } else {
            throw new DukeCommandNotFoundException();
        }
    }
}
