package InputCommands;

import SnomExceptions.InvalidCommandDateFormatException;
import SnomExceptions.InvalidCommandException;
import SnomExceptions.InvalidCommandTaskDescException;
import SnomTaskList.TaskList;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;


/**
 * The AddDeadlineCommand implements the command of
 * adding a task of type Deadline into tasklist.
 */
class AddDeadlineCommand extends Command {

    /**
     * {@inheritDoc}
     * In this subclass, the command will return enum
     * of type DEADLINE
     *
     * @return the enum of type DEADLINE
     */
    @Override
    public CmdType getType() {
        return CmdType.DEADLINE;
    }

    protected AddDeadlineCommand(String desc) {
        super(desc);
    }

    /**
     * {@inheritDoc}
     * In this subclass, we implement date checks and task description checks
     * to ensure that the command is valid.
     *
     * @param lst is the instance of Storage.TaskList.TaskList containing all the tasks.
     * @return a string representing a valid command.
     * @throws InvalidCommandException if the description or date is blank or when the
     * date is in the wrong format.
     */
    @Override
    public String execute(TaskList lst) throws InvalidCommandException {

        try {
            //the format is as such: deadline name/end date
            String detail = this.desc.toLowerCase().split("deadline ", 2)[1];
            String name = detail.split("/", 2)[0].trim();
            if (name.isEmpty()) {
                throw new InvalidCommandTaskDescException();
            }
            String due_date = detail.split("/", 2)[1].trim(); //removes the whitspace from start and end of date
            if (due_date.isEmpty()) {
                throw new InvalidCommandTaskDescException();
            }
            LocalDate date = LocalDate.parse(due_date);



            return name + "/" + date.toString();

            //you can consider changing commands to return tasks instead;

        } catch (ArrayIndexOutOfBoundsException e) {

            throw new InvalidCommandTaskDescException();
        } catch (DateTimeParseException e) {

            throw new InvalidCommandDateFormatException();

        }
    }



}
