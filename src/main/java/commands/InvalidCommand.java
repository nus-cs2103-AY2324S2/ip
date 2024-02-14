package commands;

import exceptions.DukeException;

public class InvalidCommand extends Command {

    @Override
    public String executeCommand() throws DukeException {
        throw new DukeException("It seems like you have typed an invalid command... please check again!");
    };
}
