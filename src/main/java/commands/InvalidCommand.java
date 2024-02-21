package commands;

import exceptions.HowieException;

public class InvalidCommand extends Command {

    @Override
    public String executeCommand() throws HowieException {
        throw new HowieException("It seems like you have typed an invalid command... please check again!");
    };
}
