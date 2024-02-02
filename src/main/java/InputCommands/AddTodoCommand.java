package InputCommands;

import SnomExceptions.InvalidCommandTaskDescException;
import TaskList.TaskList;

class AddTodoCommand extends Command{

    protected AddTodoCommand(String desc) {
        super(desc);
    }

    @Override
    public String execute(TaskList lst) throws InvalidCommandTaskDescException {
        try {
            String detail = this.desc.toLowerCase().split("todo ", 2)[1].trim();
            if (detail.isEmpty()) {
                throw new InvalidCommandTaskDescException();
            }
            return "todo " + detail;
        } catch (ArrayIndexOutOfBoundsException e) {

            throw new InvalidCommandTaskDescException();
        }

    }

    public static void main(String[] args) {
        try {
            System.out.println(new AddTodoCommand("todo abc todo").execute(null));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

}
