package Commands;

import Utils.CommandTypes;

public class ByeCommand extends Command {

    public ByeCommand() {
        super("bye", "bye");
    }

    public String execute() {
        return "Bye. Hope to see you again soon!";
    }

    @Override
    public CommandTypes getCommandType() {
        return CommandTypes.BYE;
    }
}
