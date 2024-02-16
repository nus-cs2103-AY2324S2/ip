package rick.logic.command;

import rick.logic.RickException;

/**
 * A command which asks to mark a task.
 */
public class MarkCommand implements Command {
    private String index;

    /**
     * Constructor for MarkCommand. Fills attribute "index" upon creation of new instance.
     * @param input the second half of user input.
     * @throws RickException when the user input has format problems.
     */
    public MarkCommand(String input) throws RickException {
        try {
            this.parse(input);
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
        return new String[]{"M", this.index};
    }
}
