import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

public class Parser {
    public static Command parse(String input) {
        String[] inputSplit = input.split(" ");

        int num;
        String name;
        String[] temp;
        Task currTask;

        switch(inputSplit[0]) {
            case "list":
                return new ListAllCommand();
            case "mark":
                num = Integer.parseInt(inputSplit[1]);
                return new MarkCommand(num);
            case "unmark":
                num = Integer.parseInt(inputSplit[1]);
                return new UnmarkCommand(num);
            case "todo":
                try {
                    name = input.substring(5);
                    currTask = new TodoTask(name);
                    return new TaskCommand(currTask);
                } catch (StringIndexOutOfBoundsException e) {
                    System.out.println("Sorry, the name of the task todo cannot be empty.\n" +
                            "The way to use the command is as such: todo taskname");
                }
            case "deadline":
                try {
                    temp = input.split("/");
                    name = temp[0].substring(9);
                    String date = temp[1].substring(3);
                    currTask = new DeadlineTask(name, date);
                    return new TaskCommand(currTask);
                } catch (IndexOutOfBoundsException e) {
                    System.out.println("Sorry, this command is in the wrong format.\n" +
                            "The way to use the command is: deadline taskname /by date_and_time");
                } catch (DateTimeParseException e) {
                    System.out.println("Unable to add task, wrong date/time format!");
                    System.out.println("Suggested format: DD/MM/YYY HH:MM");
                }
            case "event":
                try {
                    temp = input.split("/");
                    name = temp[0].substring(6);
                    String date1 = temp[1].substring(5);
                    String date2 = temp[2].substring(3);
                    currTask = new EventTask(name, date1, date2);
                    return new TaskCommand(currTask);
                } catch (IndexOutOfBoundsException e) {
                    System.out.println("Sorry, this command is in the wrong format.\n" +
                            "The way to use the command is: event taskname /from date_and_time /to date_and_time");
                } catch (DateTimeParseException e) {
                    System.out.println("Unable to add task, wrong date/time format!");
                    System.out.println("Suggested format: DD/MM/YYY HH:MM");
                }
            case "delete":
                try {
                    temp = input.split(" ");
                    int index = Integer.parseInt(temp[1])-1;
                    return new DeleteCommand(index);
                } catch (NumberFormatException e) {
                    System.out.println("Sorry, there is no number detected.\n" +
                            "The correct way to use the command is: delete number");
                } catch (IndexOutOfBoundsException e) {
                    System.out.println("Sorry, the format for this command is wrong.\n" +
                            "The correct way to use the command is: delete number");
                }
            case "today":
                return new TasksTodayCommand();
            case "help":
                return new HelpCommand();
            case "bye":
                return new ExitCommand();
            default:
                return new UnrecognisedCommand();
        }
    }
}
