package rick.logic.command;

import java.time.LocalDateTime;

import rick.logic.RickException;

/**
 * A command that asks to add a Deadline task.
 */
public class DeadlineCommand implements Command {
    private static final String BY_KEYWORD = " /by ";
    private String name;
    private String deadline;

    /**
     * Constructor of DeadlineCommand. Fills attribute upon creation of new instance.
     * @param input user input
     * @throws RickException if user input has format problems.
     */
    public DeadlineCommand(String input) throws RickException {
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
        if (input == null || input.isBlank()) {
            throw new RickException("Ugh, Morty, you wanna add a task but you don't even know what it is? "
                    + "Come on, Morty, you're wasting my time here. "
                    + "Figure out what you want and then come back to me.");
        }
        if (!input.contains(BY_KEYWORD)) {
            throw new RickException("Seriously, Morty? You don't even know when the deadline is? "
                    + "How am I supposed to help you if you don't give me all the information? "
                    + "Put it in 'deadline [title] /by yyyy-mm-ddTHH-MM-SS' format, Morty. Get it together. ");
        }
        assert input.contains(BY_KEYWORD);
        int byIndex = input.indexOf(BY_KEYWORD);
        int keywordLength = BY_KEYWORD.length();
        String name = input.substring(0, byIndex);
        String deadline = input.substring(byIndex + keywordLength);
        if (name.isBlank() || deadline.isBlank()) {
            throw new RickException("Seriously, Morty? You don't even know when the deadline is? "
                    + "How am I supposed to help you if you don't give me all the information? "
                    + "Put it in 'deadline [title] /by yyyy-mm-ddTHH-MM-SS' format, Morty. Get it together. ");
        }
        try {
            LocalDateTime.parse(deadline);
        } catch (Exception e) {
            throw new RickException("Nice try, Morty, but you messed up the deadline format. "
                    + "I need it in yyyy-mm-dd, or yyyy-mm-ddTHH-MM-SS, Morty. "
                    + "Don't make me repeat myself.");
        }
        this.name = name;
        this.deadline = deadline;
    }

    /**
     * Returns a string array containing important arguments of a deadline command.
     * @return a string containing command type and related information.
     */
    @Override
    public String[] respond() {
        return new String[]{"DL", this.name, this.deadline};
    }
}
