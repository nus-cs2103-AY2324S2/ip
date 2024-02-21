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

    private void parse(String input) throws RickException {
        if (input == null || input.isBlank()) {
            throw new RickException("Task name cannot be empty!");
        }
        if (!input.contains(BY_KEYWORD)) {
            throw new RickException("Tell me the due date/time in this format: "
                    + "deadline [title] /by yyyy-mm-ddTHH-MM-SS");
        }
        assert input.contains(BY_KEYWORD);
        int byIndex = input.indexOf(BY_KEYWORD);
        int keywordLength = BY_KEYWORD.length();
        String name = input.substring(0, byIndex);
        String deadline = input.substring(byIndex + keywordLength);
        if (name.isBlank() || deadline.isBlank()) {
            throw new RickException("I need both the name and the deadline!");
        }
        try {
            LocalDateTime.parse(deadline);
        } catch (Exception e) {
            throw new RickException("Key in the deadline in this format: yyyy-mm-dd or yyyy-mm-ddTHH-MM-SS");
        }
        this.name = name;
        this.deadline = deadline;
    }
    @Override
    public String[] respond() {
        return new String[]{"DL", this.name, this.deadline};
    }
}
