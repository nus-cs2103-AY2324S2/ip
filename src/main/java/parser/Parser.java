package parser;

import command.*;
import exception.TobiasException;
import task.TaskList;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Parser {

    public static LocalDateTime dateFromString(String dateTime) throws TobiasException {
        try {
            return LocalDateTime.parse(dateTime.trim(), DateTimeFormatter.ofPattern("dd-MM-yyyy HHmm"));
        } catch (Exception e) {
            throw new TobiasException("    Kindly enter the date or time in this format : dd-MM-yyyy HHmm");
        }
    }

    public static Command parseCommands(String command, TaskList tasks) throws TobiasException {
        if(command.equals("list")) {
            ListCommand lc = new ListCommand();
            return lc;
        }
        else if (command.startsWith("todo")) {
            try {
                if (command.equals("todo")) {
                    throw new TobiasException("    Hey, please enter a description !");
                }

                String blank = command.substring(4,5);

                if (!blank.isBlank()) {
                    throw new TobiasException("    Kindly type your task a space after todo!");
                }

                String description = command.substring(5);

                TodoCommand td = new TodoCommand(description);
                return td;

            } catch(TobiasException e) {
                e.printMessage();
            }
        }
        else if (command.startsWith("deadline")) {
            try {
                if (command.equals("deadline")) {
                    throw new TobiasException("    Hey, please enter a description !");
                }

                String blank = command.substring(8,9);

                if (!blank.isBlank()) {
                    throw new TobiasException("    Kindly type your task a space after deadline!");
                }

                int byIndex = command.indexOf("/by");

                if (byIndex == -1) {
                    throw new TobiasException("    Hey, please use '/by' to specify the deadline!!");
                }

                String description = command.substring(9, byIndex);

                int deadlineIndex = byIndex+4;

                if (deadlineIndex > command.length()) {
                    throw new TobiasException("     Hey, please enter a date & time in this format : dd-MM-yyyy HHmm !");
                }

                String deadline = command.substring(byIndex + 4);

                LocalDateTime dd = dateFromString(deadline);

                if (deadline.isEmpty() || deadline.isBlank()) {
                    throw new TobiasException("     Hey, please enter a date & time in this format : dd-MM-yyyy HHmm !");
                }

                DeadlineCommand dc = new DeadlineCommand(description, dd);
                return dc;

            } catch (TobiasException e) {
                e.printMessage();
            }
        }
        else if (command.startsWith("event")) {
            try {

                if (command.equals("event")) {
                    throw new TobiasException("     Hey, please enter a description !");
                }

                String blank = command.substring(5,6);

                if (!blank.isBlank()) {
                    throw new TobiasException("    Kindly type your task a space after event!");
                }

                int fromIndex = command.indexOf("/from");
                int toIndex = command.indexOf("/to");

                if (fromIndex == -1 && toIndex == -1) {
                    throw new TobiasException("     Hey, please enter a /from and /to date/day/time!!");
                }

                if (fromIndex == -1) {
                    throw new TobiasException("     Hey, please enter a /from date/day/time!!");
                }

                if (toIndex == -1) {
                    throw new TobiasException("     Hey, please enter a /to date/day/time!!");
                }

                if (fromIndex > toIndex && toIndex > 0) {
                    throw new TobiasException("     Hey, you have to enter /from before /to !!");
                }

                String from = command.substring(fromIndex + 6, toIndex);

                if (from.isEmpty() || from.isBlank()) {
                    throw new TobiasException("     Hey, please enter a /from date/day/time!!");
                }

                if ((toIndex + 4) > command.length()) {
                    throw new TobiasException("     Hey, please enter a /to date/day/time!!");
                }

                String to = command.substring(toIndex + 4);

                if (to.isEmpty() || to.isBlank()) {
                    throw new TobiasException("     Hey, please enter a /to date/day/time!!");
                }

                String description = command.substring(6, fromIndex);

                LocalDateTime f = dateFromString(from);
                LocalDateTime t = dateFromString(to);

                EventCommand ec = new EventCommand(description, f, t);
                return ec;

            } catch (TobiasException e) {
                e.printMessage();
            }
        }
        else if (command.startsWith("mark")) {
            try {
                int size = tasks.taskNum();

                if (size == 0) {
                    throw new TobiasException("    Your list is empty at the moment, add some todos/events/deadlines to MARK them!");
                }

                if (command.equals("mark")) {
                    throw new TobiasException("    No index provided! Please give a valid index from 1 to " + size +" !!");
                }

                String blank = command.substring(4,5);

                if (!blank.isBlank()) {
                    throw new TobiasException("    Kindly type your task a space after mark!");
                }

                int index = Integer.parseInt(command.substring(5)) - 1;

                if (index >= 0 && index < size) {
                    MarkCommand mc = new MarkCommand(index);
                    return mc;
                } else {
                    throw new TobiasException("    Invalid number provided! Please give a valid index from 1 to " + size +" !!");
                }
            } catch (TobiasException e) {
                e.printMessage();
            }
        }
        else if (command.startsWith("unmark")) {
            try {
                int size = tasks.taskNum();

                if (size == 0) {
                    throw new TobiasException("    Your list is empty at the moment, add some todos/events/deadlines to UNMARK them!");
                }

                if (command.equals("unmark")) {
                    throw new TobiasException("    No index provided! Please give a valid index from 1 to " + size +" !!");
                }

                String blank = command.substring(6,7);

                if (!blank.isBlank()) {
                    throw new TobiasException("    Kindly type your task a space after unmark!");
                }

                int index = Integer.parseInt(command.substring(7)) - 1;

                if (index >= 0 && index < size) {
                    UnmarkCommand uc = new UnmarkCommand(index);
                    return uc;
                } else {
                    throw new TobiasException("    Invalid number provided! Please give a valid index from 1 to " + size +" !!");
                }
            } catch (TobiasException e) {
                e.printMessage();
            }
        }
        else if (command.startsWith("delete")) {
            try {
                int size = tasks.taskNum();

                if (size == 0) {
                    throw new TobiasException("    Your list is empty at the moment, add some todos/events/deadlines to DELETE them!");
                }

                if (command.equals("delete")) {
                    throw new TobiasException("    No index provided! Please give a valid index from 1 to " + size +" !!");
                }

                String blank = command.substring(6,7);

                if (!blank.isBlank()) {
                    throw new TobiasException("    Kindly type your task a space after delete!");
                }

                int index = Integer.parseInt(command.substring(7)) - 1;

                if (index >= 0 && index < size) {
                    DeleteCommand dc = new DeleteCommand(index);
                    return dc;
                } else {
                    throw new TobiasException("    Invalid number provided! Please give a valid index from 1 to " + size +" !!");
                }
            }   catch (TobiasException e) {
                e.printMessage();
            }
        }
        else if (command.equals("bye")) {
            ExitCommand ec = new ExitCommand();
            return ec;
        }
        throw new TobiasException(
                "    You can ask me  stuff like       : 'bye', 'list', 'mark', 'unmark','delete'\n" +
                "    You can ask me to create these   : 'todo', 'deadline', 'event'");

    }
}
