package inputcommands;

import snomexceptions.InvalidCommandException;
import snomtasklist.TaskList;


/**
 * The Command class is an abstract class that implements
 * the different types of command.
 */
public abstract class Command {

    protected String desc;

    public Command(String desc) {
        this.desc = desc;
    }

    /**
     * Tells the parser what type of command the current instance is.
     * @return an enum type representing the type of command.
     */
    public abstract CmdType getType();


    /**
     * Verifies whether the command is valid or not.
     * If invalid, an exception will be thrown.
     *
     * @param lst is the instance of Storage.TaskList.TaskList containing all the tasks.
     * @return a String representing the command
     */
    public abstract String execute(TaskList lst) throws InvalidCommandException;

    /**
     * Creates a new Command based on the command entered.
     * @param description is the string entered by the user.
     * @return an instance of command.
     * @throws InvalidCommandException if the user enters an invalid command.
     */
    public static Command makeCommand(String description) throws InvalidCommandException {
        String type = description.split(" ")[0].toLowerCase();
        Command cmd;
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
            cmd = new AddTodoCommand(description);
            break;
        case "deadline":
            cmd = new AddDeadlineCommand(description);
            break;
        case "event":
            cmd = new AddEventCommand(description);
            break;
        case "find":
            cmd = new FindCommand(description);
            break;
        default:
            throw new InvalidCommandException();

        }

        return cmd;



    }





}
