package rick.logic.command;

import rick.logic.RickException;

/**
 * A command which asks for deleting a task.
 */
public class DeleteCommand implements Command {
    private String index;

    /**
     * Condtructor for DeleteCommand. Fills index to delete upon creation of new instance.
     * @param input the second half of user input.
     * @throws RickException if user input has format problems.
     */
    public DeleteCommand(String input) throws RickException {
        try {
            parse(input);
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
            throw new RickException("Come on, Morty, you gotta put in an integer to delete a task. "
                    + "You can't just type random stuff and expect it to work. "
                    + "Try again, Morty, and this time, put in a number.");
        }
    }

    /**
     * Returns a string array containing important arguments of a bye command.
     * @return a string containing command type and related information.
     */
    @Override
    public String[] respond() {
        return new String[]{"D", this.index};
    }
}
