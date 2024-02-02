package InputCommands;

import SnomExceptions.InvalidCommandDateFormatException;
import SnomExceptions.InvalidCommandException;
import SnomExceptions.InvalidCommandTaskDescException;
import TaskList.TaskList;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

class AddDeadlineCommand extends Command {

    protected AddDeadlineCommand(String desc) {
        super(desc);
    }

    @Override
    public String execute(TaskList lst) throws InvalidCommandException {

        try {
            //the format is as such: deadline name/end date
            String detail = this.desc.toLowerCase().split("deadline ", 2)[1];
            String due_date = detail.split("/", 2)[1].trim(); //removes the whitspace from start and end of date
            LocalDate date = LocalDate.parse(due_date);
            String name = detail.split("/", 2)[0].trim();

            if (name.isEmpty()) {

                throw new InvalidCommandTaskDescException();
            }
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

            System.out.println(new AddDeadlineCommand("deadline /efg").execute(null));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }


}
