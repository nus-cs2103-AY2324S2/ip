package theadvisor;

/**
 * A parser class to help in parsing user inputs.
 */
public class Parser {
    public enum Prompts {
        BYE, LIST, MARK, UNMARK, DELETE, TODO, DEADLINE, EVENT, FIND
    }

    /**
     * Parses the input given from the user, returning it as a prompt.
     *
     * @param prompt The user input.
     * @return The parsed prompt.
     * @throws TheAdvisorException If the prompt is incorrect.
     */
    public static Prompts parsePrompt(String prompt) throws TheAdvisorException {
        try {
            return Prompts.valueOf(prompt.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new TheAdvisorException("Incorrect prompt use. Please try again with these prompts: " +
                    "todo, list, mark, unmark, bye, event, deadline, find");
        }
    }
}