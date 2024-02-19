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

    private void parse(String input) throws RickException {
        if (input == null || input.isBlank()) {
            throw new RickException("Task name cannot be empty!");
        }
        if (!input.contains(" /from ") || !input.contains(" /to ")) {
            throw new RickException("Tell me the event date/time in this format: "
                    + "event [title] /from yyyy-mm-ddTHH-MM-SS /to yyyy-mm-ddTHH-MM-SS");
        }
        int fromIndex = input.indexOf(FROM_KEYWORD);
        int toIndex = input.indexOf(TO_KEYWORD);
        int fromLen = FROM_KEYWORD.length();
        int toLen = TO_KEYWORD.length();
        String name = input.substring(0, fromIndex);
        String from = input.substring(fromIndex + fromLen, toIndex);
        String to = input.substring(toIndex + toLen);
        if (name.isBlank() || from.isBlank() || to.isBlank()) {
            throw new RickException("I need both the name and the start and end time!");
        }
        this.name = name;
        this.from = from;
        this.to = to;
    }
    @Override
    public String[] respond() {
        return new String[]{"E", this.name, this.from, this.to};
    }
}
