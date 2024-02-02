package InputCommands;

import SnomExceptions.InvalidCommandException;
import Storage.TaskList;

public abstract class Command {

//    /**
//     * Checks against the Storage.TaskList to ensure that task is valid.
//     * Prevents duplicate tasks and invalid indices (if applicable).
//     *
//     * @param lst is the instance of Storage.TaskList containing all the tasks.
//     * @return a boolean value depending on whether the command is valid.
//     */
//    public abstract boolean checkCommand(Storage.TaskList lst);

    /**
     * Verifies whether the command is valid or not.
     * If invalid, an exception will be thrown.
     *
     * @param lst is the instance of Storage.TaskList containing all the tasks.
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
            cmd = null;
            break;
        case "bye":
            cmd = null;
            break;
        case "mark":
            cmd = null;
            break;
        case "unmark":
            cmd = null;
            break;
        case "delete":
            cmd = null;
            break;
        case "todo":
            System.out.println("done");
            cmd = null;
            break;
        case "deadline":
            cmd = null;
            break;
        case "event":
            cmd = null;
            break;
        default:
            throw new InvalidCommandException("Please enter a valid command");

        }

        return cmd;



    }





}
