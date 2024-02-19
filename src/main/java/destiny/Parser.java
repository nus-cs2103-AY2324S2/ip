package destiny;

/**
 * Used to understand the user input.
 */
public class Parser {
    /**
     * Splits user input into the command and its details.
     *
     * @param userMessage The user input.
     * @return An array of strings where the 1st elem is the command and the 2nd is the details of the command.
     */
    public String[] getCommand(String userMessage) {
        String[] result = new String[2];
        Boolean foundSplit = false;
        for (int i = 0; i < userMessage.length(); i++) {
            if (userMessage.charAt(i) == ' ') {
                result[0] = userMessage.substring(0, i);
                result[1] = userMessage.substring(i + 1, userMessage.length());
                foundSplit = true;
                break;
            }
        }
        if (!foundSplit) {
            result[0] = userMessage;
        }

        return result;
    }

    /**
     * Used by commands that require details.
     *
     * @param cmd The command given.
     * @param details The details given by the user.
     * @return Trimmed details.
     * @throws DukeException If details is empty or null.
     */
    public String getCmdDetails(String cmd, String details) throws DukeException {
        if (details == null || details.trim().length() == 0) {
            throw new DukeException("Please enter a description for the " + cmd + " command");
        }
        return details.trim();
    }
}
