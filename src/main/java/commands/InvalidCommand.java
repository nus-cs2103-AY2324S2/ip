package commands;

import exceptions.HowieException;

public class InvalidCommand extends Command {

    /**
     * Prints out an error message when an invalid input is detected.
     */
    @Override
    public String executeCommand() throws HowieException {
        throw new HowieException("It seems like you have typed an invalid command... please check again!");
    };
}
