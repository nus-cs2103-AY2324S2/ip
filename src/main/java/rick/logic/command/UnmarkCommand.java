package rick.logic.command;

import rick.logic.RickException;

/**
 * A command which asks to mark a task.
 */
public class UnmarkCommand implements Command {
    private String index;

    /**
     * Constructor for MarkCommand. Fills attribute "type" and "index" upon creation of new instance.
     * @param input the second half of user input.
     * @throws RickException when the user input has format problems.
     */
    public UnmarkCommand(String input) throws RickException {
        try {
            this.parse(input);
        } catch (RickException e) {
            throw e;
        }
    }

    /**
     * Parses an input command using deadline format.
     * @param input user's input.
     * @throws RickException when input does not follow format of deadline command.
     */
    private void parse(String input) throws RickException {
        try {
            Integer.parseInt(input);
            this.index = input;
        } catch (Exception e) {
            throw new RickException("An integer should follow!");
        }
    }

    /**
     * Returns a string array containing important arguments of a bye command.
     * @return a string containing command type and related information.
     */
    @Override
    public String[] respond() {
        return new String[]{"U", this.index};
    }
}

