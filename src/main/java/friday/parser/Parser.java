package friday.parser;

/**
 * The Parser class is responsible for parsing user commands in the Friday application.
 * It extracts the command keyword from the user input for further processing.
 */
public class Parser {
    /**
     * Parses the user input to extract the command keyword.
     *
     * @param userInput The user input to be parsed.
     * @return The command keyword extracted from the user input.
     */
    public String parseCommand(String userInput) {
        return userInput.split(" ")[0].toLowerCase().trim();
    }
}
