package rick.logic.command;

import rick.logic.RickException;

/**
 * A command that asks to add an Event task.
 */
public class EventCommand implements Command {
    private static final String FROM_KEYWORD = " /from ";
    private static final String TO_KEYWORD = " /to ";
    private String name;
    private String from;
    private String to;

    /**
     * Constructor of DeadlineCommand. Fills attribute upon creation of new instance.
     * @param input user input
     * @throws RickException if user input has format problems.
     */
    public EventCommand(String input) throws RickException {
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
            throw new RickException("Whoa, Morty, you want me to record an event, "
                    + "but you didn't even give me the name of the event? "
                    + "How am I supposed to do that, Morty? I'm not a mind reader, you know");
        }
        if (!input.contains(" /from ") || !input.contains(" /to ")) {
            throw new RickException("Alright, Morty, you forgot to tell me the start and end times for the event. "
                    + "You gotta give me all the info like below, Morty, or I can't help you. Get it together.\n"
                    + "event [title] /from yyyy-mm-ddTHH-MM-SS /to yyyy-mm-ddTHH-MM-SS");
        }
        assert input.length() > (FROM_KEYWORD.length() + TO_KEYWORD.length());
        int fromIndex = input.indexOf(FROM_KEYWORD);
        int toIndex = input.indexOf(TO_KEYWORD);
        int fromLen = FROM_KEYWORD.length();
        int toLen = TO_KEYWORD.length();
        String name = input.substring(0, fromIndex);
        String from = input.substring(fromIndex + fromLen, toIndex);
        String to = input.substring(toIndex + toLen);
        if (name.isBlank() || from.isBlank() || to.isBlank()) {
            throw new RickException("Alright, Morty, you forgot to tell me the start and end times for the event. "
                    + "You gotta give me all the info like below, Morty, or I can't help you. Get it together.\n"
                    + "event [title] /from yyyy-mm-ddTHH-MM-SS /to yyyy-mm-ddTHH-MM-SS");
        }
        this.name = name;
        this.from = from;
        this.to = to;
    }

    /**
     * Returns a string array containing important arguments of a bye command.
     * @return a string containing command type and related information.
     */
    @Override
    public String[] respond() {
        return new String[]{"E", this.name, this.from, this.to};
    }
}
