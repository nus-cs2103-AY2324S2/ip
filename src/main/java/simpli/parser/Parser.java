package simpli.parser;

import java.util.Arrays;
import java.util.HashSet;

import simpli.actions.Action;
import simpli.exceptions.ActionException;


/**
 * Parses various strings into an array of tokens.
 */
public final class Parser {
    /**
     * The maximum number of tokens allowed.
     * Token index information:
     * 0 - command
     * 1 - isDone
     * 2 - taskName or taskNum (Mark, Unmark, Delete or Todo, Deadline, Event)
     * 3 - dueDate or fromDate (Deadline or Event task)
     * 4 - toDate (Event task)
     * */
    private static final int MAX_TOKENS = 5;
    private HashSet<Action> singleCommands;

    public Parser() {
        this.singleCommands = new HashSet<>();
        singleCommands.add(Action.LIST);
        singleCommands.add(Action.GREET);
        singleCommands.add(Action.BYE);
    }

    /**
     * Parses and breaks down the content String into tokens.
     *
     * @param content A command String.
     * @return An Array of tokens.
     * @throws ActionException if there are unknown tokens or invalid commands.
     */
    public String[] parseCommand(String content) throws ActionException {
        String[] parsedTokens = new String[MAX_TOKENS];
        Arrays.fill(parsedTokens, "");

        String[] tokens = content.split(" /[a-zA-Z]* ");
        String[] taskInfo = tokens[0].split(" ", 2);

        parsedTokens[0] = taskInfo[0];
        parsedTokens[1] = "0";

        Action actionType;
        try {
            actionType = Action.valueOf(taskInfo[0].toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new ActionException("No such command!");
        }

        parsedTokens[2] = !singleCommands.contains(actionType) ? taskInfo[1] : "";
        System.arraycopy(tokens, 1, parsedTokens, 3, tokens.length - 1);

        if (!isValidCommand(parsedTokens)) {
            throw new ActionException();
        }

        return parsedTokens;
    }

    /**
     * Checks if command is valid.
     *
     * @param tokens An Array of tokens.
     * @return true if command is valid, false if otherwise.
     */
    public boolean isValidCommand(String[] tokens) {
        try {
            Action.valueOf(tokens[0].toUpperCase());
        } catch (IllegalArgumentException e) {
            return false;
        }
        return true;
    }

    /**
     * Parses and breaks down string of comma-separated values (csv) into tokens.
     *
     * @param csv comma-separated values.
     * @return An Array of token.
     */
    public String[] parseCsv(String csv) {
        String[] parsedTokens = new String[MAX_TOKENS];
        Arrays.fill(parsedTokens, "");
        String[] fields = csv.split(",");

        System.arraycopy(fields, 0, parsedTokens, 0, fields.length);

        return parsedTokens;
    }
}
