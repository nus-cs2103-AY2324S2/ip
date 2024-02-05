package inputcommands;

import snomexceptions.InvalidCommandDateFormatException;
import snomexceptions.InvalidCommandException;
import snomexceptions.InvalidCommandTaskDescException;
import snomtasklist.TaskList;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;


class AddDeadlineCommand extends Command {

    protected AddDeadlineCommand(String desc) {
        super(desc);
    }


    @Override
    public CmdType getType() {
        return CmdType.DEADLINE;
    }
    @Override
    public String execute(TaskList lst) throws InvalidCommandException {

        try {
            //the format is as such: deadline name/end date
            String detail = this.desc.toLowerCase().split("deadline ", 2)[1];
            String name = detail.split("/", 2)[0].trim();
            if (name.isEmpty()) {
                throw new InvalidCommandTaskDescException();
            }
            String deadline = detail.split("/", 2)[1].trim(); //removes the whitspace from start and end of date
            if (deadline.isEmpty()) {
                throw new InvalidCommandTaskDescException();
            }
            LocalDate date = LocalDate.parse(deadline);



            return name + "/" + date.toString();

            //you can consider changing commands to return tasks instead;

        } catch (ArrayIndexOutOfBoundsException e) {

            throw new InvalidCommandTaskDescException();
        } catch (DateTimeParseException e) {

            throw new InvalidCommandDateFormatException();

        }
    }

    public static void main(String[] args) {
        try {

            System.out.println(new AddDeadlineCommand("deadline /2000-01-01").execute(null));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }


}
