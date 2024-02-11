package parser;

import command.*;
import exception.TobiasException;
import task.TaskList;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Parser {

    
    /**
     * Takes in a string representation of date/time and returns it as a LocalDateTime object.
     *
     * @param dateTime String of the required date/time information.
     * @return a LocalDateTime object with the provided date/time information.
     * @throws TobiasException if the dateTime string does not follow format of dd-MM-yyyy HHmm.
     * */
    public static LocalDateTime dateFromString(String dateTime) throws TobiasException {
        try {
            return LocalDateTime.parse(dateTime.trim(), DateTimeFormatter.ofPattern("dd-MM-yyyy HHmm"));
        } catch (Exception e) {
            throw new TobiasException("Kindly enter the date or time in this format : dd-MM-yyyy HHmm");
        }
    }

    /**
     * Takes in a command as a String and a TaskList.
     * Interprets that command and returns the relevant Command object.
     *
     * @param command Type of command given as a String
     * @param tasks TaskList
     * @throws TobiasException for each possible type of commands and/or if command is not valid.
     * @return The relevant type of Command object.
     * */
    public static Command parseCommands(String command, TaskList tasks) throws TobiasException {
        if(command.equals("list")) {
            return new ListCommand();
        } else if (command.startsWith("todo")) {
            if (command.equals("todo")) {
                throw new TobiasException("Hey, please enter a description !");
            }

            String blank = command.substring(4,5);

            if (!blank.isBlank()) {
                throw new TobiasException("Kindly type your task a space after todo!");
            }

            String description = command.substring(5);

            return new TodoCommand(description);
        } else if (command.startsWith("deadline")) {
            if (command.equals("deadline")) {
                throw new TobiasException("Hey, please enter a description !");
            }

            String blank = command.substring(8,9);

            if (!blank.isBlank()) {
                throw new TobiasException("Kindly type your task a space after deadline!");
            }

            int byIndex = command.indexOf("/by");

            if (byIndex == -1) {
                throw new TobiasException("Hey, please use '/by' to specify the deadline!!");
            }

            String description = command.substring(9, byIndex);

            int deadlineIndex = byIndex+4;

            if (deadlineIndex > command.length()) {
                throw new TobiasException("Hey, please enter a date & time in this format : dd-MM-yyyy HHmm !");
            }

            String deadline = command.substring(byIndex + 4);

            LocalDateTime dd = dateFromString(deadline);

            if (deadline.isEmpty() || deadline.isBlank()) {
                throw new TobiasException("Hey, please enter a date & time in this format : dd-MM-yyyy HHmm !");
            }

            return new DeadlineCommand(description, dd);
        } else if (command.startsWith("event")) {
            if (command.equals("event")) {
                throw new TobiasException("Hey, please enter a description !");
            }

            String blank = command.substring(5,6);

            if (!blank.isBlank()) {
                throw new TobiasException("Kindly type your task a space after event!");
            }

            int fromIndex = command.indexOf("/from");
            int toIndex = command.indexOf("/to");

            if (fromIndex == -1 && toIndex == -1) {
                throw new TobiasException("Hey, please enter a /from and /to date/day/time!!");
            }

            if (fromIndex == -1) {
                throw new TobiasException("Hey, please enter a /from date/day/time!!");
            }

            if (toIndex == -1) {
                throw new TobiasException("Hey, please enter a /to date/day/time!!");
            }

            if (fromIndex > toIndex && toIndex > 0) {
                throw new TobiasException("Hey, you have to enter /from before /to !!");
            }

            String from = command.substring(fromIndex + 6, toIndex);

            if (from.isEmpty() || from.isBlank()) {
                throw new TobiasException("Hey, please enter a /from date/day/time!!");
            }

            if ((toIndex + 4) > command.length()) {
                throw new TobiasException("Hey, please enter a /to date/day/time!!");
            }

            String to = command.substring(toIndex + 4);

            if (to.isEmpty() || to.isBlank()) {
                throw new TobiasException("Hey, please enter a /to date/day/time!!");
            }

            String description = command.substring(6, fromIndex);

            LocalDateTime f = dateFromString(from);
            LocalDateTime t = dateFromString(to);

            return new EventCommand(description, f, t);
        } else if (command.startsWith("mark")) {
            int size = tasks.taskNum();

            if (size == 0) {
                throw new TobiasException("Your list is empty at the moment, add some todos/events/deadlines to MARK them!");
            }

            if (command.equals("mark")) {
                throw new TobiasException("No index provided! Please give a valid index from 1 to " + size +" !!");
            }

            String blank = command.substring(4,5);

            if (!blank.isBlank()) {
                throw new TobiasException("Kindly type your task a space after mark!");
            }

            int index = Integer.parseInt(command.substring(5)) - 1;

            if (index >= 0 && index < size) {
                return new MarkCommand(index);
            } else {
                throw new TobiasException("Invalid number provided! Please give a valid index from 1 to " + size +" !!");
            }
        } else if (command.startsWith("unmark")) {
            int size = tasks.taskNum();

            if (size == 0) {
                throw new TobiasException("Your list is empty at the moment, add some todos/events/deadlines to UNMARK them!");
            }

            if (command.equals("unmark")) {
                throw new TobiasException("No index provided! Please give a valid index from 1 to " + size +" !!");
            }

            String blank = command.substring(6,7);

            if (!blank.isBlank()) {
                throw new TobiasException("Kindly type your task a space after unmark!");
            }

            int index = Integer.parseInt(command.substring(7)) - 1;

            if (index >= 0 && index < size) {
                return new UnmarkCommand(index);
            } else {
                throw new TobiasException("Invalid number provided! Please give a valid index from 1 to " + size +" !!");
            }
        } else if (command.startsWith("delete")) {
            int size = tasks.taskNum();

            if (size == 0) {
                throw new TobiasException("Your list is empty at the moment, add some todos/events/deadlines to DELETE them!");
            }

            if (command.equals("delete")) {
                throw new TobiasException("No index provided! Please give a valid index from 1 to " + size +" !!");
            }

            String blank = command.substring(6,7);

            if (!blank.isBlank()) {
                throw new TobiasException("Kindly type your task a space after delete!");
            }

            int index = Integer.parseInt(command.substring(7)) - 1;

            if (index >= 0 && index < size) {
                return new DeleteCommand(index);
            } else {
                throw new TobiasException("Invalid number provided! Please give a valid index from 1 to " + size +" !!");
            }
        } else if (command.startsWith("find")) {
            if (command.equals("find")) {
                throw new TobiasException("Please enter what you are finding for after 'find' !!!");
            }

            String blank = command.substring(4,5);

            if (!blank.isBlank()) {
                throw new TobiasException("Kindly enter what you are finding a space after find !!!");
            }

            String keyWord = command.substring(5);
            return new FindCommand(keyWord);
        } else if (command.equals("bye")) {
            return new ExitCommand();
        } else {
            throw new TobiasException(
                    "You can ask me  stuff like : 'bye', 'list', 'mark', 'unmark','delete'"
                            + System.lineSeparator()
                            + "You can ask me to create these  : 'todo', 'deadline', 'event'");
        }
    }
}
