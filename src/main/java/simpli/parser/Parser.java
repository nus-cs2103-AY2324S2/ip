package simpli.parser;

import simpli.actions.Action;

import java.util.Arrays;

public final class Parser {
    /* tokens index information
     * 0 - command
     * 1 - isDone
     * 2 - taskName
     * 3 - "by" or "from" (Deadline or Event task)
     * 4 - dueDate or fromDate (Deadline or Event task)
     * 5 - "to" (Event task)
     * 6 - toDate (Event task) */
    private static final int MAX_TOKENS = 7;

    public String[] parseCommand(String content) {
        String[] parsedTokens = new String[MAX_TOKENS];
        Arrays.fill(parsedTokens, "");

        String[] tokens = content.split(" ");

        parsedTokens[0] = tokens[0];  // command
        parsedTokens[1] = "0";      // isDone
        int index = 2;
        for (int i = 1; i < tokens.length; i++) {
            if (!tokens[i].contains("/")) {
                parsedTokens[index] += parsedTokens[index].isEmpty() ? "" : " ";
                parsedTokens[index] += tokens[i];
            } else {
                index++;
                parsedTokens[index] = tokens[i].substring(1);
                index++;
            }
        }

        return parsedTokens;
    }

    public String[] parseCsv(String csv) {
        String[] parsedTokens = new String[MAX_TOKENS];
        String[] fields = csv.split(",");

        parsedTokens[0] = fields[0];
        parsedTokens[1] = fields[1];
        parsedTokens[2] = fields[2];
        Action actionType = Action.valueOf(fields[0].toUpperCase());
        switch (actionType) {
        case Action.TODO: {
            break;
        }
        case Action.DEADLINE: {
            parsedTokens[3] = "by";
            parsedTokens[4] = fields[3];
            break;
        }
        case Action.EVENT: {
            parsedTokens[3] = "from";
            parsedTokens[4] = fields[3];
            parsedTokens[5] = "to";
            parsedTokens[6] = fields[4];
            break;
        }
        }
        return parsedTokens;
    }
}
