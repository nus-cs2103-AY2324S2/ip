package theadvisor;

/**
 * A parser class to help in parsing user inputs.
 */
public class Parser {

    /**
     * Enumeration of available prompts for parsing user inputs.
     */
    public enum Prompts {
        /**
         * Prompt for ending the program.
         */
        BYE,

        /**
         * Prompt for listing tasks.
         */
        LIST,

        /**
         * Prompt for marking a task as done.
         */
        MARK,

        /**
         * Prompt for unmarking a task.
         */
        UNMARK,

        /**
         * Prompt for deleting a task.
         */
        DELETE,

        /**
         * Prompt for adding a todo task.
         */
        TODO,

        /**
         * Prompt for adding a deadline task.
         */
        DEADLINE,

        /**
         * Prompt for adding an event task.
         */
        EVENT,

        /**
         * Prompt for finding tasks.
         */
        FIND
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
            throw new TheAdvisorException("Incorrect prompt use. Please try again with these prompts: "
                    + "todo, list, mark, unmark, bye, event, deadline, find");
        }
    }
}
