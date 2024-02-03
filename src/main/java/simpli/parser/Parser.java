package simpli.parser;

import simpli.actions.Action;
import simpli.exceptions.TaskException;

import java.time.LocalDate;
import java.util.Arrays;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


public final class Parser {
    /* tokens index information
     * 0 - command
     * 1 - isDone
     * 2 - taskName or taskNum (Mark, Unmark, Delete or Todo, Deadline, Event)
     * 3 - dueDate or fromDate (Deadline or Event task)
     * 4 - toDate (Event task) */
    private static final int MAX_TOKENS = 5;

    public String[] parseCommand(String content) throws TaskException{
        String[] parsedTokens = new String[MAX_TOKENS];
        Arrays.fill(parsedTokens, "");

        String[] tokens = content.split(" /[a-zA-Z]* ");  // 1:
        String[] taskInfo = tokens[0].split(" ", 2);

        parsedTokens[0] = taskInfo[0];
        parsedTokens[1] = "0";

        Action actionType = Action.valueOf(taskInfo[0].toUpperCase());
        switch (actionType) {
        case Action.LIST:
            break;
        case Action.MARK: {
            parsedTokens[2] = taskInfo[1];
            break;
        }
        case Action.UNMARK: {
            parsedTokens[2] = taskInfo[1];
            break;
        }
        case Action.DELETE: {
            parsedTokens[2] = taskInfo[1];
            break;
        }
        case Action.TODO: {
            parsedTokens[2] = taskInfo[1];
            break;
        }
        case Action.DEADLINE: {
            if (!content.contains("/by")) {
                throw new TaskException();
            }
            parsedTokens[2] = taskInfo[1];
            parsedTokens[3] = tokens[1];
            parsedTokens[4] = tokens[2];
        }
        case Action.EVENT: {
            if (!content.contains("/from") || !content.contains("/to")) {
                throw new TaskException();
            }
            parsedTokens[2] = taskInfo[1];
            parsedTokens[3] = tokens[1];
            parsedTokens[4] = tokens[2];
        }
        }

        return parsedTokens;
    }

    public String[] parseCsv(String csv) {
        String[] parsedTokens = new String[MAX_TOKENS];
        Arrays.fill(parsedTokens, "");
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
            parsedTokens[3] = fields[3];
            break;
        }
        case Action.EVENT: {
            parsedTokens[3] = fields[3];
            parsedTokens[4] = fields[4];
            break;
        }
        }
        return parsedTokens;
    }
}
