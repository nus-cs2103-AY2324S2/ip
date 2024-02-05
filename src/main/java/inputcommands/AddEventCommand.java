package inputcommands;


import snomexceptions.InvalidCommandDateFormatException;
import snomexceptions.InvalidCommandDateValueException;
import snomexceptions.InvalidCommandException;
import snomexceptions.InvalidCommandTaskDescException;
import snomtasklist.TaskList;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

class AddEventCommand extends Command {

    protected AddEventCommand(String desc) {
        super(desc);
    }

    @Override
    public CmdType getType() {
        return CmdType.EVENT;
    }

    @Override
    public String execute(TaskList lst) throws InvalidCommandException {

        try {
            //the format is as such: deadline name/end date
            String detail = this.desc.toLowerCase().split("event ", 2)[1];
            String startDate = detail.split("/", 3)[1].trim(); //removes the whitspace from start and end of date
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

            //you can consider changing commands to return tasks instead;

        } catch (ArrayIndexOutOfBoundsException e) {

            throw new InvalidCommandTaskDescException();
        } catch (DateTimeParseException e) {

            throw new InvalidCommandDateFormatException();

        }
    }

    public static void main(String[] args) {
        try {

            System.out.println(new AddEventCommand("event AS/1999-01-01/2000-01-01").execute(null));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }


}