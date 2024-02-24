package commands;

public class ListDirCommand extends Command{
    public static final String COMMAND_WORD = "ls";

    @Override
    public String execute() {
        return this.storage.listCaches();
    }
}
