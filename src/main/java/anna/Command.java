package anna;

import java.util.function.Consumer;

/**
 * Represents a command the chatbot should handle
 */
public interface Command {
    public boolean execute(Consumer<String> reply, TaskList list) throws AnnaException;
}
