package rick.logic.command;

import rick.logic.RickException;

/**
 * A command which asks to search for tasks containing a particular sub-string.
 */
public class FindCommand implements Command {
    private String substring;

    /**
     * Condtructor for FindCommand. Fills sub-string to look for upon creation of new instance.
     * @param input the second half of user input.
     * @throws RickException if user input has format problems.
     */
    public FindCommand(String input) {
        this.substring = input;
    }

    /**
     * Returns a string array containing important arguments of a bye command.
     * @return a string containing command type and related information.
     */
    @Override
    public String[] respond() throws RickException {
        if (this.substring.isEmpty()) {
            throw new RickException("Morty, I can't believe this, Morty. "
                    + "You won't even tell me what to look for? "
                    + "I don't have time for this, Morty. "
                    + "Just tell me what you need, Morty, and let's get it done!");
        }
        return new String[]{"F", this.substring};
    }
}
