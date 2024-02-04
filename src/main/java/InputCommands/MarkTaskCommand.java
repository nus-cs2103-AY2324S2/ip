package InputCommands;

import SnomExceptions.InvalidCommandIndexException;
import SnomTaskList.TaskList;

class MarkTaskCommand extends Command{

    protected MarkTaskCommand(String desc) {
        super(desc);
    }

    @Override
    public CmdType getType() {
        return CmdType.MARK;
    }

    @Override
    public String execute(TaskList lst) throws InvalidCommandIndexException {
        try {
            int pos = Integer.parseInt(this.desc.split(" ")[1]);
            lst.getTask(pos);
            return Integer.toString(pos);
        } catch (InvalidCommandIndexException e) {
            throw new InvalidCommandIndexException();
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new InvalidCommandIndexException();
        }

    }


}
