package fishstock;

/**
 * Encapsulates a user input.
 */
public class UserInput {
    private final Command command;
    private final String details;

    /**
     * Initializes a UserInput.
     *
     * @param input The whole input from user (including command).
     */
    protected UserInput(String input) {
        assert input != null : "User input should not be null";

        String[] splitInput = input.split(" ", 2);
        command = parseCommandType(splitInput[0]);

        if (splitInput.length == 2) {
            details = splitInput[1];
        } else {
            details = "";
        }
    }

    /**
     * Parses command type from input.
     */
    private static Command parseCommandType(String commandStr) {
        try {
            return Command.valueOf(commandStr.toUpperCase());
        } catch (java.lang.IllegalArgumentException e) {
            return Command.INVALID;
        }
    }

    /**
     * Gets command type from user input.
     */
    public Command getCommandType() {
        return command;
    }

    /**
     * Splits the input into an array, delimited by the specified keywords,
     * and excluding the command keyword.
     *
     * @param keywords The keyword(s) to delimit with.
     * @return The array with the split input.
     */
    public String[] splitByKeywords(String ... keywords) {
        String currDetails = details;
        String[] result = new String[keywords.length + 1];
        int index = 0;

        for (String keyword : keywords) {
            String[] splitDetails = currDetails.split(keyword, 2);
            if (splitDetails.length == 1) { // Not found
                break;
            }
            result[index++] = splitDetails[0].trim();
            currDetails = splitDetails[1].trim();
        }
        result[index] = currDetails.trim();

        return result;
    }

    /**
     * Gets index number from input.
     * Has the format "[command] [task_number]".
     * Subtracts 1 from task_number to obtain index number for array.
     *
     * @return The resulting index number.
     * @throws FishStockException The exceptions while calculating the index number.
     */
    public int getIndex() throws FishStockException {
        if (details.isEmpty()) {
            throw new FishStockException("OH NOSE! Task number cannot be empty..");
        }

        try {
            int num = Integer.parseInt(details);
            return num - 1; // Start from index 0

        } catch (NumberFormatException e) {
            throw new FishStockException("OH NOSE! Task number has to be an integer..");
        }
    }
}
