package simpli.parser;

import simpli.actions.Action;
import simpli.exceptions.ActionException;

import java.util.Arrays;

public final class Parser {
    /* tokens index information
     * 0 - command
     * 1 - isDone
     * 2 - taskName or taskNum (Mark, Unmark, Delete or Todo, Deadline, Event)
     * 3 - dueDate or fromDate (Deadline or Event task)
     * 4 - toDate (Event task) */
    private static final int MAX_TOKENS = 5;

    public String[] parseCommand(String content) throws ActionException {
        String[] parsedTokens = new String[MAX_TOKENS];
        Arrays.fill(parsedTokens, "");

        String[] tokens = content.split(" /[a-zA-Z]* ");  // 1:
        String[] taskInfo = tokens[0].split(" ", 2);

        parsedTokens[0] = taskInfo[0];
        parsedTokens[1] = "0";

        Action actionType;
        try {
            actionType = Action.valueOf(taskInfo[0].toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new ActionException("No such command!");
        }
        parsedTokens[2] = !actionType.equals(Action.LIST) ? taskInfo[1] : "";

        System.arraycopy(tokens, 1, parsedTokens, 3, tokens.length - 1);

        if (!isValidCommand(parsedTokens)) {
            throw new ActionException();
        }

        return parsedTokens;
    }

    public boolean isValidCommand(String[] tokens) {
        try {
            Action actionType = Action.valueOf(tokens[0].toUpperCase());
        } catch (IllegalArgumentException e) {
            return false;
        }
        return true;
    }

    public String[] parseCsv(String csv) {
        String[] parsedTokens = new String[MAX_TOKENS];
        Arrays.fill(parsedTokens, "");
        String[] fields = csv.split(",");

        System.arraycopy(fields, 0, parsedTokens, 0, fields.length);

        return parsedTokens;
    }
}
