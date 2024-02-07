package duke;

import duke.command.*;

/**
 * The class Parser deals with understanding and recording the user command.
 */

public class Parser {
    /**
     * Returns Command that is sorted by the function
     *
     * @param fullCommand full command by the user in String
     * @return Command
     * @throws NoCmdException
     */
    public static Command parse(String fullCommand) throws NoCmdException {
        String [] uCmd = fullCommand.split(" ", 2);
        if (uCmd.length < 2) {
            if (fullCommand.equals("list")) {
                return new ListCommand();
            } else if (fullCommand.equals("bye")) {
                return new ExitCommand();
            } else {
                throw new NoCmdException("Please tell me what you want me to do.");
            }
        } else {
            String firstWord = uCmd[0].trim();
            String furtherDetails = uCmd[1];
            System.out.println(furtherDetails);
            if (firstWord.equals("mark") || firstWord.equals("unmark")) {
                return new EditCommand(firstWord, furtherDetails);
            } else if (firstWord.equals("todo") || firstWord.equals("event") || firstWord.equals("deadline")) {
                return new AddCommand(firstWord, furtherDetails);
            } else if (firstWord.equals("delete")) {
                return new DeleteCommand(furtherDetails);
            } else {
                throw new NoCmdException("Please tell me what you want me to do.");
            }
        }


    }
}



