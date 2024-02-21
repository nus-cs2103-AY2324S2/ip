package rick.logic.command;

import rick.logic.RickException;

/**
 * An interface for command
 */
public interface Command {
    String[] respond() throws RickException;
}
