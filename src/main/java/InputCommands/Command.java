package InputCommands;

import SnomExceptions.InvalidCommandException;
import TaskList.TaskList;

public abstract class Command {

    protected String desc;

    public Command(String desc) {
        this.desc = desc;
    }

//    /**
//     * Checks against the Storage.TaskList.TaskList to ensure that task is valid.
//     * Prevents duplicate tasks and invalid indices (if applicable).
//     *
//     * @param lst is the instance of Storage.TaskList.TaskList containing all the tasks.
//     * @return a boolean value depending on whether the command is valid.
//     */
//    public abstract boolean checkCommand(Storage.TaskList.TaskList lst);

    /**
     * Verifies whether the command is valid or not.
     * If invalid, an exception will be thrown.
     *
     * @param lst is the instance of Storage.TaskList.TaskList containing all the tasks.
     * @return a String representing the command
     */
    public abstract String execute (TaskList lst) throws InvalidCommandException;

    /**
     * Creates a new Command based on the command entered.
     * @param description is the string entered by the user.
     * @return an instance of command.
     * @throws InvalidCommandException if the user has enterd an invalid command.
     */
    public static Command makeCommand(String description) throws InvalidCommandException {
        String type = description.split(" ")[0].toLowerCase();
        String[] cmd_lst = {"list", "bye", "mark", "unmark", "delete", "todo", "deadline", "event"};

        Command cmd = null;

        switch (type) {
        case "list":
            cmd = new ListCommand();
            break;
        case "bye":
            cmd = new ByeCommand();
            break;
        case "mark":
            cmd = new MarkTaskCommand(description);
            break;
        case "unmark":
            cmd = new UnmarkTaskCommand(description);
            break;
        case "delete":
            cmd = new DeleteTaskCommand(description);
            break;
        case "todo":
            cmd = null;
            break;
        case "deadline":
            cmd = null;
            break;
        case "event":
            cmd = null;
            break;
        default:
            throw new InvalidCommandException();

        }

        return cmd;



    }





}
