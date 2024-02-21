package jivox;


import jivox.exception.JivoxUnknownCommandException;

/**
 * Parser handles parsing of user input.
 */
public class Parser {

    /**
     * Parses the command from the raw user input.
     *
     * @param rawInput The raw input from the user.
     * @return The command parsed from the input.
     * @throws JivoxUnknownCommandException If command is invalid.
     */
    public Commands parseCommand(String rawInput) throws JivoxUnknownCommandException {
        String[] input = rawInput.split(" ", 2);
        try {
            return Commands.valueOf(input[0].toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new JivoxUnknownCommandException(input[0]);
        }
    }

    /**
     * Splits the raw input into parts.
     *
     * @param rawInput The raw input from the user.
     * @return The input split into parts.
     */
    public String[] parseInput(String rawInput) {
        return rawInput.split(" ", 2);
    }

    /**
     * Splits the input by the given regex.
     *
     * @param input The input to split.
     * @param reg The regex to split on.
     * @return The split input parts.
     */
    public String[] split(String input, String reg) {
        return input.split(reg);
    }
    /**
     * Splits the input into a max number of parts.
     *
     * @param input The input to split.
     * @param reg The regex to split on.
     * @param limit The max number of splits.
     * @return The split input parts.
     */
    public String[] split(String input, String reg, int limit) {
        return input.split(reg, limit);
    }

}
