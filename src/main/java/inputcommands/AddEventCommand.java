package inputcommands;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import snomexceptions.InvalidCommandDateFormatException;
import snomexceptions.InvalidCommandDateValueException;
import snomexceptions.InvalidCommandException;
import snomexceptions.InvalidCommandTaskDescException;
import snomtasklist.TaskList;






/**
 * The AddEventCommand implements the command of adding
 * a task of type Event to the TaskList.
 */
class AddEventCommand extends Command {

    protected AddEventCommand(String desc) {
        super(desc);
    }


    /**
     * {@inheritDoc}
     * In this subclass, the command will return enum
     * of type EVENT
     *
     * @return the enum of type EVENT
     */
    @Override
    public CmdType getType() {
        return CmdType.EVENT;
    }

    /**
     * {@inheritDoc}
     * In this subclass, we implement date checks and task description checks
     * to ensure that the command is valid.
     *
     * @param lst is the instance of Storage.TaskList.TaskList containing all the tasks.
     * @return a string representing a valid command.
     * @throws InvalidCommandException if the description or date is blank or when the
     *         date is in the wrong format.
     */
    @Override
    public String execute(TaskList lst) throws InvalidCommandException {

        try {
            //the format is as such: deadline name/end date
            String detail = this.desc.toLowerCase().split("event ", 2)[1];
            String startDate = detail.split("/", 3)[1].trim(); //removes the whitespace from start and end of date
            String endDate = detail.split("/", 3)[2].trim();
            LocalDate start = LocalDate.parse(startDate);
            LocalDate end = LocalDate.parse(endDate);
            if (end.isBefore(start)) {
                throw new InvalidCommandDateValueException();
            }
            String name = detail.split("/", 3)[0].trim();

            if (name.isEmpty()) {

                throw new InvalidCommandTaskDescException();
            }
            return name + "/" + start.toString() + "/" + end.toString();


        } catch (ArrayIndexOutOfBoundsException e) {

            throw new InvalidCommandTaskDescException();
        } catch (DateTimeParseException e) {

            throw new InvalidCommandDateFormatException();

        }
    }


}
