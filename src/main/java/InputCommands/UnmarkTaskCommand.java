package InputCommands;


import SnomExceptions.InvalidCommandException;
import SnomExceptions.InvalidCommandIndexException;
import TaskList.TaskList;

class UnmarkTaskCommand extends Command{

    protected UnmarkTaskCommand(String desc) {
        super(desc);
    }

    @Override
    public String execute(TaskList lst) throws InvalidCommandIndexException {
        try {
            int pos = Integer.parseInt(this.desc.split(" ")[1]);
            lst.getTask(pos);
            return "mark " + pos;
        } catch (InvalidCommandIndexException e) {
            throw new InvalidCommandIndexException();
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new InvalidCommandIndexException();
        }

    }

    public static void main(String[] args) {
        TaskList lst = TaskList.makeTaskList();
        try {
            Command.makeCommand("mark ").execute(lst);
        } catch (InvalidCommandException e) {
            System.out.println(e.getMessage());
        }
    }
}
