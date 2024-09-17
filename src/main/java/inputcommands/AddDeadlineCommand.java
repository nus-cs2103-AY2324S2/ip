package inputcommands;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import snomexceptions.InvalidCommandDateFormatException;
import snomexceptions.InvalidCommandException;
import snomexceptions.InvalidCommandTaskDescException;
import snomtasklist.TaskList;






/**
 * The AddDeadlineCommand implements the command of
 * adding a task of type Deadline into TaskList.
 */
class AddDeadlineCommand extends Command {

    protected AddDeadlineCommand(String desc) {
        super(desc);
    }



    /**
     * {@inheritDoc}
     *         In this subclass, the command will return enum
     *         of type DEADLINE
     *
     * @return the enum of type DEADLINE
     */
    @Override
    public CmdType getType() {
        return CmdType.DEADLINE;
    }

    /**
     * {@inheritDoc}
     *         In this subclass, we implement date checks and task description checks
     *        to ensure that the command is valid.
     *
     * @param lst is the instance of Storage.TaskList.TaskList containing all the tasks.
     * @return a string representing a valid command.
     * @throws InvalidCommandException if the description or date is blank or when the
     *     date is in the wrong format.
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
            String deadline = detail.split("/", 2)[1].trim(); //removes the whitespace from start and end of date
            if (deadline.isEmpty()) {
                throw new InvalidCommandTaskDescException();
            }
            LocalDate date = LocalDate.parse(deadline);



            return name + "/" + date.toString();


        } catch (ArrayIndexOutOfBoundsException e) {

            throw new InvalidCommandTaskDescException();
        } catch (DateTimeParseException e) {

            throw new InvalidCommandDateFormatException();

        }
    }



}
