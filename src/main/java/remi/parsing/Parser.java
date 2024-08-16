package remi.parsing;

import remi.io.Message;
import remi.model.commands.CommandList;
import remi.utils.RemiError;

/**
 * Parses strings and feeds them to the command line. Also allows you to directly run the parsed string.
 */
public class Parser {
    private CommandList commandList;

    /**
     * Initializes the parser and loads all the commands.
     *
     * @param commandList CommandList object provided by the Chatbot
     */
    public Parser(CommandList commandList) {
        this.commandList = commandList;
    }

    private String[] getKeywordArgsSplit(Message input) {
        int idx = input.getMessage().indexOf(' ');
        String[] res = new String[2];

        // this indicates a single word command
        if (idx == -1) {
            res[0] = input.getMessage();
            res[1] = "";
        } else {
            res[0] = input.getMessage().substring(0, idx);
            res[1] = input.getMessage().substring(idx + 1);
        }
        return res;
    }

    /**
     * Runs the necessary logic given a message and returns the output.
     *
     * @param input message received from the console
     * @return message to be outputted
     */
    public Message parseAndRun(Message input) throws RemiError {
        String[] splitMsg = getKeywordArgsSplit(input);

        String keyword = splitMsg[0];
        String args = splitMsg[1];

        return commandList.runKeyword(keyword, args);
    }

    /**
     * Gets a label from the args of any of the task commands i.e. todo, deadline, event.
     *
     * @param input the args to the command
     * @return the label of the task
     * @throws RemiError if there is no description for the task
     */
    public static String getLabel(String input) throws RemiError {
        int idx = input.indexOf("/");
        if (idx == -1) {
            idx = input.length();
        } else {
            idx--;
        }

        if (idx <= 0) {
            throw new RemiError("You didn't put a description for the task.");
        }
        return input.substring(0, idx);
    }

    /**
     * Finds the option of the form ".../option...".
     * Example: "going home /by: friday", should return the string "friday"
     *
     * @param option the option to be found, include the "/" at the start
     * @param input the input line to be scanned
     * @return the string value of the specific option
     */
    public static String findOption(String option, String input) throws RemiError {
        assert !option.isEmpty();

        int idx = input.indexOf(option);

        if (idx == -1) {
            throw new RemiError("I couldn't find a " + option + ", please specify it by adding a " + option);
        } else {
            int endIdx = input.indexOf("/", idx + 1);
            if (endIdx == -1) {
                endIdx = input.length();
            } else {
                // decrement to get the space before the /
                endIdx = endIdx - 1;
            }
            return input.substring(idx + option.length() + 1, endIdx);
        }
    }
}
