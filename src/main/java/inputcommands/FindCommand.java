package inputcommands;


import snomexceptions.InvalidCommandException;
import snomexceptions.InvalidCommandTaskDescException;
import snomtasklist.TaskList;

class FindCommand extends Command {

    protected FindCommand(String desc) {
        super(desc);
    }

    @Override
    public CmdType getType() {
        return CmdType.FIND;
    }
    @Override
    public String execute(TaskList lst) throws InvalidCommandException {
        try {
            String detail = this.desc.toLowerCase().split("find ", 2)[1].trim();
            if (detail.isEmpty()) {
                throw new InvalidCommandTaskDescException();
            }
            return detail;
        } catch (ArrayIndexOutOfBoundsException e) {

            throw new InvalidCommandTaskDescException();
        }

    }
}
