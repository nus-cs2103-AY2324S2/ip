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
}
