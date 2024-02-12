package commands;

import Exceptions.InvalidInputException;

public class CreateDirCommand extends Command {
    public static final String COMMAND_WORD = "mkdir";
    private final String dirName;

    public CreateDirCommand(String dirName){
        this.dirName = dirName;
    }

    @Override
    public String execute() throws InvalidInputException {
        this.storage.makeFile(this.dirName);
        return "Directory " + dirName + " created!";
    }
}
