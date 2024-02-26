package fireraya.main;

import fireraya.command.*;
import fireraya.exception.FirerayaException;
import fireraya.exception.InvalidNumOfArgsException;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;


/**
 * This class represents the Parser for the program.
 *
 * Given a string input from the user, the parser
 * will classify it into a specific task and return a command.
 */
public class Parser {

    /**
     * The main method of the parser class used to parse strings.
     *
     * @param command a String command from the user.
     * @return A Command of the class specified in the input string.
     */
    static Command parse(String command) throws FirerayaException {

        try {

            String[] all = command.split(" ");
            String keyword = all[0];
            int arrLen = all.length;


            if (keyword.equals("bye")) {
                return new ExitCommand();
            }

            if (keyword.equals("list")) {
                return new ListCommand();
            }

            if (keyword.equals("mark")) {
                if (all.length > 2) {
                    throw new InvalidNumOfArgsException();
                }
                int curr = Integer.parseInt(all[1]) - 1;
                return new MarkCommand(curr);
            }

            if (keyword.equals("unmark")) {
                if (all.length > 2) {
                    throw new InvalidNumOfArgsException();
                }
                int curr = Integer.parseInt(all[1]) - 1;
                return new UnmarkCommand(curr);
            }

            if (keyword.equals("todo")) {
                if (arrLen == 1) {
                    throw new FirerayaException("Error! Description of a todo cannot be empty");
                }
                String description = String.join(" ", Arrays.copyOfRange(all, 1, arrLen));
                return new ToDoCommand(description);
            }

            if (keyword.equals("deadline")) {
                int index = -1;
                int i = 0;
                String breakpoint = "/by";
                for (String element : all) {
                    if (element.equals(breakpoint)) {
                        index = i;
                        break;
                    }
                    i++;
                }
                if (index == -1) {
                    throw new FirerayaException("No /by detected in deadline");
                }

                String description = String.join(" ", Arrays.copyOfRange(all, 1, index));
                String deadline = String.join(" ", Arrays.copyOfRange(all, index + 1, arrLen));

                if (isDateCheck(deadline)) {
                    Date d = parseDate(deadline);
                    return new DeadlineCommand(description, d);

                }
                return new DeadlineCommand(description, deadline);
            }

            if (keyword.equals("do")) {
                int index = -1;
                int i = 0;
                String breakpoint = "/after";
                for (String element : all) {
                    if (element.equals(breakpoint)) {
                        index = i;
                        break;
                    }
                    i++;
                }
                if (index == -1) {
                    throw new FirerayaException("No /after detected in deadline");
                }

                String description = String.join(" ", Arrays.copyOfRange(all, 1, index));
                String after = String.join(" ", Arrays.copyOfRange(all, index + 1, arrLen));

                if (isDateCheck(after)) {
                    Date d = parseDate(after);
                    return new DoAfterCommand(description, d);

                }
                return new DoAfterCommand(description, after);
            }

            if (keyword.equals("find")) {
                if (all.length > 2) {
                    throw new InvalidNumOfArgsException();
                }
                String key = all[1];
                return new FindCommand(key);
            }


            if (keyword.equals("event")) {
                int indexf = -1;
                int indext = -1;
                int f = 0;
                int t = 0;
                String fromPoint = "/from";
                String toPoint = "/to";

                for (String element : all) {
                    if (element.equals(fromPoint)) {
                        indexf = f;
                        break;
                    }
                    f++;
                }
                if (indexf == -1) {
                    throw new FirerayaException("No /from detected in deadline");
                }
                for (String element : all) {
                    if (element.equals(toPoint)) {
                        indext = t;
                        break;
                    }
                    t++;
                }
                if (indext == -1) {
                    throw new FirerayaException("No /to detected in deadline");
                }

                String description = String.join(" ", Arrays.copyOfRange(all, 1, indexf));
                String from = String.join(" ", Arrays.copyOfRange(all, indexf + 1, indext));
                String to = String.join(" ", Arrays.copyOfRange(all, indext + 1, arrLen));
                return new EventCommand(description, from, to);
            }

            if (keyword.equals("delete")) {
                if (all.length > 2) {
                    throw new InvalidNumOfArgsException();
                }
                if (!isNumber(all[1])) {
                    throw new FirerayaException("Error: argument must be a number");
                }
                assert isNumber(all[1]);
                int curr = Integer.parseInt(all[1]) - 1;

                return new DeleteCommand(curr);
            }

            throw new FirerayaException("Sorry mate, I don't understand what that means");

        } catch (FirerayaException e) {
            throw e;
        }
    }

    /**
     * Checks if the input string is in a date format.
     *
     * @param str input string of the user.
     * @return Boolean representing if the format is a date.
     */
    public static boolean isDateCheck(String str) {

        String[] dates = str.split("/");

        if (!isNumber(dates[0]) || !isNumber(dates[1]) || dates.length != 3) {
            System.out.println("Notdate 1");
            return false;
        }

        String[] last = dates[2].split(" ");

        if (!isNumber(last[0]) || !isNumber(last[1]) || last.length != 2) {
            System.out.println("Notdate 2");
            return false;
        }
        return true;
    }

    /**
     * Checks if the input string is a number.
     *
     * @param str input string of the user.
     * @return Boolean representing if the format is a number.
     */
    public static boolean isNumber(String str) {
        return str.matches("-?\\d+(\\.\\d+)?");
    }

    /**
     * Parses the date of the input string into a readable format for the program.
     *
     * @param date input string of the user representing the date.
     * @return A Java utils Date object.
     */
    private static Date parseDate(String date) throws FirerayaException {
        try {
            SimpleDateFormat d = new SimpleDateFormat("dd/MM/yyyy HHmm");
            return d.parse(date);
        } catch (ParseException e) {
            throw new FirerayaException(e.getMessage());
        }
    }

}

