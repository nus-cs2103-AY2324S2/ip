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
            assert !firstWord.equals("todos") : "You want to add 1 task at a time - todo";
            assert !firstWord.equals("events") : "You want to add 1 task at a time - event";
            assert !firstWord.equals("deadlines") : "You want to add 1 task at a time - deadline";
            if (firstWord.equals("mark") || firstWord.equals("unmark")) {
                System.out.println("DO");
                return new EditCommand(firstWord, furtherDetails);
            } else if (firstWord.equals("todo") || firstWord.equals("event") || firstWord.equals("deadline")) {
                return new AddCommand(firstWord, furtherDetails);
            } else if (firstWord.equals("delete")) {
                System.out.println("DO");
                return new DeleteCommand(furtherDetails);
            } else if (firstWord.equals("find")) {
                return new FindCommand(furtherDetails);
            } else {
                throw new NoCmdException("Please tell me what you want me to do.");
            }
        }


    }
}



