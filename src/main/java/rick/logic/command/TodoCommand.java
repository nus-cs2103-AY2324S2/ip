package rick.logic.command;

import rick.logic.RickException;

/**
 * Command that adds to the task list.
 */
public class TodoCommand implements Command {
    private String name;

    /**
     * Constructor of TodoCommand.
     * @param input the second half of user input
     */
    public TodoCommand(String input) throws RickException {
        if (input != null && !input.isBlank()) {
            this.name = input;
        } else {
            throw new RickException("Ugh, Morty, you wanna add a task but you don't even know what it is? "
                    + "Come on, Morty, you're wasting my time here. "
                    + "Figure out what you want and then come back to me.");
        }
    }

    /**
     * Returns a string array containing important arguments of a bye command.
     * @return a string containing command type and related information.
     */
    @Override
    public String[] respond() {
        return new String[]{"T", this.name};
    }
}
