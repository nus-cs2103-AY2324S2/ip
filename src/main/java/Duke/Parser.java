package Duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import Duke.command.*;
import Duke.task.*;

/**
 * The class used to parse the input from user to commands that
 * the program can execute
 */
public class Parser{
    /**
     * Parse the data in string formate to a specified LocalDate format
     * .
     * @param date the string representation of a date
     * @return the localDate representation of the data inputted by the user
     */
    public static LocalDate parseDate(String date){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate date1 = LocalDate.parse(date, formatter);
        return date1;
    }

    /**
     * Parse the String command by the user to command that could be executed by the program
     *
     * @param msg The input from users
     * @return A command object that represent the action to be performed
     */
    public static Command parse(String msg){
        if (msg.equals("bye")) {
            return new ExitCommand();
        } else if (msg.equals("list")) {
            return new ListCommand();
        } else if (msg.length() >= 4 && msg.substring(0, 4).equals("todo")) {
            return new AddCommand(new Todos(msg.substring(4)));

        } else if (msg.length() >= 8 && msg.substring(0, 8).equals("deadline")) {
            String[] strarr = processDeadlineMsg(msg.substring(8));
            return new AddCommand(new Deadline(strarr[0], parseDate(strarr[1])));
        } else if (msg.length() >= 5 && msg.substring(0, 5).equals("event")) {
            String[] strarr = processEventMsg(msg.substring(5));
            return new AddCommand(new Event(strarr[0], parseDate(strarr[1]),parseDate(strarr[2])));
//                return new AddCommand(new Event(msg.substring(5)));
        } else if (msg.length() > 4 && msg.substring(0, 4).equals("mark")) {
            int number = Integer.parseInt(msg.substring(5));
            return new MarkCommand(number);
        } else if (msg.length() > 6 && msg.substring(0, 6).equals("unmark")) {
            int number = Integer.parseInt(msg.substring(7));
            return new UnmarkCommand(number);
        } else if (msg.length() > 6 && msg.substring(0, 6).equals("delete")) {
            int number = Integer.parseInt(msg.substring(7));
            return new DeleteCommand(number);
        }else if (msg.length() > 4 && msg.substring(0, 4).equals("find")) {
            String stringToFind = msg.substring(5);
            return new FindCommand(stringToFind);
        }
        else {
            return new DontknowCommand();
        }
    }

    /**
     * Extract important information in user's command and organized them into a list of String
     *
     * @param msg User's input with only informative parts, i.e. without the command part
     * @return A list of String of three elements, the first element is the information about the event,
     * the second element is the starting time of the event, and the third element is the ending time of the event
     */
    static String[] processEventMsg(String msg){
        String[] arr = new String[3];
        String[] strarr = msg.split("/from");
        if(strarr.length == 2){
            arr[0] = strarr[0].trim();
            String[] strArr = strarr[1].split("/to");
            arr[1] = strArr[0].trim();
            if(strArr.length == 2){
                arr[2]=strArr[1].trim();
            }else{
                arr[2]="";
            }
        }else{
            String[] strArr = strarr[0].split("/to");
            if(strArr.length == 2) {
                arr[0] = strArr[0].trim();
                arr[1] = "";
                arr[2] = strArr[1].trim();
            }
            else{
                arr[0] = strArr[0].trim();
                arr[1] = "";
                arr[2] = "";
            }
        }
        return arr;
    }
    /**
     * Extract important information in user's command and organized them into a list of String
     *
     * @param msg User's input with only informative parts, i.e. without the command part
     * @return A list of String of two elements, the first element is the information about the event,
     * the second element is the deadline of the event
     */
    static String[] processDeadlineMsg(String msg){
        String[] arr = new String[2];
        String[] strarr = msg.split("/by");
        if(strarr.length == 2){
            arr[0] = strarr[0].trim();
            arr[1] = strarr[1].trim();
        }else{
            arr[0] = (strarr[0].trim());
            arr[1] = "";
        }
        return arr;
    }
}
