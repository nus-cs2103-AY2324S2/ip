package InputCommands;

import SnomExceptions.InvalidCommandDateFormatException;
import SnomExceptions.InvalidCommandDateValueException;
import SnomExceptions.InvalidCommandException;
import SnomExceptions.InvalidCommandTaskDescException;
import TaskList.TaskList;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

class AddEventCommand extends Command {

    protected AddEventCommand(String desc) {
        super(desc);
    }

    @Override
    public String execute(TaskList lst) throws InvalidCommandException {

        try {
            //the format is as such: deadline name/end date
            String detail = this.desc.toLowerCase().split("event ", 2)[1];
            String start_date = detail.split("/", 3)[1].trim(); //removes the whitspace from start and end of date
            String end_date = detail.split("/", 3)[2].trim();
            LocalDate start = LocalDate.parse(start_date);
            LocalDate end = LocalDate.parse(end_date);
            if (end.isBefore(start)) {
                throw new InvalidCommandDateValueException();
            }
            String name = detail.split("/", 2)[0].trim();

            if (name.isEmpty()) {

                throw new InvalidCommandTaskDescException();
            }
            return name + "/" + start.toString() + "/" +end.toString();

            //you can consider changing commands to return tasks instead;

        } catch (ArrayIndexOutOfBoundsException e) {

            throw new InvalidCommandTaskDescException();
        } catch (DateTimeParseException e) {

            throw new InvalidCommandDateFormatException();

        }
    }

    public static void main(String[] args) {
        try {

            System.out.println(new AddEventCommand("event /2009-01-01/2000-01-01").execute(null));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }


}