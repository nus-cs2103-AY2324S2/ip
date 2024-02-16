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

    private void parse(String input) throws RickException {
        try {
            Integer.parseInt(input);
            this.index = input;
        } catch (Exception e) {
            throw new RickException("An integer should follow!");
        }
    }
    @Override
    public String[] respond() {
        return new String[]{"D", this.index};
    }
}
