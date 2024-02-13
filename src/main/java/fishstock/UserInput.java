package fishstock;

import fishstock.Command.CommandType;

/**
 * Encapsulates a user input.
 */
class UserInput {
    private final CommandType commandType;
    private final String details;

    /**
     * Initializes a UserInput.
     * @param input The whole input from user (including command).
     */
    protected UserInput(String input) {
        assert input != null : "User input should not be null";

        String[] splitInput = input.split(" ", 2);
        commandType = Parser.parseCommandType(splitInput[0]);

        if (splitInput.length == 2) {
            details = splitInput[1];
        } else {
            details = "";
        }
    }

    /**
     * Gets command type from user input.
     * @return The command type.
     */
    protected CommandType getCommandType() {
        return commandType;
    }

    /**
     * Splits the input into an array, delimited by the specified keywords,
     * and excluding the command keyword.
     * @param keywords The keyword(s) to delimit with.
     * @return The array with the split input.
     */
    protected String[] splitByKeywords(String ... keywords) {
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
     * @return The resulting index number.
     * @throws FishStockException The exceptions while calculating the index number.
     */
    protected int getIndex() throws FishStockException {
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
