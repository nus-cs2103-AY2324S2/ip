public class Parser {
    public enum Prompts {
        BYE, LIST, MARK, UNMARK, DELETE, TODO, DEADLINE, EVENT
    }
    public static Prompts parsePrompt(String prompt) throws TheAdvisorException {
        try {
            return Prompts.valueOf(prompt.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new TheAdvisorException("Incorrect prompt use. Please try again with these prompts: " +
                    "todo, list, mark, unmark, bye, event, deadline");
        }
    }
}
