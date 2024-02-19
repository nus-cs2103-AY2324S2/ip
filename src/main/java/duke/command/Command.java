package duke.command;

import duke.storage.Storage;

import static duke.command.CommandEnum.getCommandEnumList;

public abstract class Command {

    protected static Storage storage;
    public static final String INVALID_COMMAND = "-------------------------------- \n" +
            "Oops, I'm not sure what you meant by that! Commands available:" +
            getCommandEnumList() +
            "-------------------------------- \n";
    public static final String ERROR_LIST_EMPTY = "-------------------------------- \n" +
            "Oops, list is still empty! \n" +
            "-------------------------------- \n";

    public Command() {

    }
    public abstract CommandResult execute();

    public void configureStorageFile(Storage storage) {
        this.storage = storage;
    }

}
