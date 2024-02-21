package parser;

import command.Command;
import command.DeadlineCommand;
import command.DeleteCommand;
import command.EventCommand;
import command.ExitCommand;
import command.FindCommand;
import command.ListCommand;
import command.MarkCommand;
import command.ReplyCommand;
import command.TagCommand;
import command.TodoCommand;
import command.UnmarkCommand;
import command.UntagCommand;
import command.SaveCommand;

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

    public static Command helloParser() {
        return new ReplyCommand("Howdy there partner !");
    }

    public static Command listParser() {
        return new ListCommand();
    }

    public static Command saveParser() {
        return new SaveCommand();
    }

    public static Command byeParser() throws TobiasException {
        return new ExitCommand();
    }

    public static Command todoParser(String command) throws TobiasException {
        if (command.equals("todo")) {
            throw new TobiasException("Hey, please enter a description !");
        }

        String blank = command.substring(4,5);

        if (!blank.isBlank()) {
            throw new TobiasException("Kindly type your task a space after todo!");
        }

        String description = command.substring(5);

        return new TodoCommand(description);
    }

    public static Command deadlineParser(String command) throws TobiasException {
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
    }

    public static Command eventParser(String command) throws TobiasException {
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
    }

    public static Command markParser(String command, TaskList tasks) throws TobiasException {
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
    }

    public static Command unmarkParser(String command, TaskList tasks) throws TobiasException {
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
    }

    public static Command deleteParser(String command, TaskList tasks) throws TobiasException {
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
    }

    public static Command findParser(String command) throws TobiasException {
        if (command.equals("find")) {
            throw new TobiasException("Please enter what you are finding for after 'find' !!!");
        }

        String blank = command.substring(4,5);

        if (!blank.isBlank()) {
            throw new TobiasException("Kindly enter what you are finding a space after find !!!");
        }

        String keyWord = command.substring(5);
        return new FindCommand(keyWord);
    }

    public static Command tagParser(String command, TaskList tasks) throws TobiasException {
        int size = tasks.taskNum();

        if (size == 0) {
            throw new TobiasException("Your list is empty at the moment, add some todos/events/deadlines to TAG them!");
        }

        if (command.equals("tag")) {
            throw new TobiasException("No index provided! Please give a valid index from 1 to " + size +" !!");
        }

        if (command.length() == 5) {
            throw new TobiasException("No tag provided! Please give a valid tag !!");
        }

        String blank = command.substring(3,4);

        if (!blank.isBlank()) {
            throw new TobiasException("Kindly type your task a space after tag!");
        }

        String blankAgain = command.substring(5,6);

        if (!blankAgain.isBlank()) {
            throw new TobiasException("Kindly type your task a space after the task index!");
        }

        int index = Integer.parseInt(command.substring(4, 5)) - 1;
        String taskTag = command.substring(6);


        if (index < 0 || index >= size) {
            throw new TobiasException("Invalid number provided! Please give a valid index from 1 to " + size +" !!");
        }

        return new TagCommand(index, taskTag);
    }

    public static Command untagParser(String command, TaskList tasks) throws TobiasException {
        int size = tasks.taskNum();

        if (size == 0) {
            throw new TobiasException("Your list is empty at the moment, add some todos/events/deadlines to UNTAG them!");
        }

        if (command.equals("untag")) {
            throw new TobiasException("No index provided! Please give a valid index from 1 to " + size +" !!");
        }

        if (command.length() == 7) {
            throw new TobiasException("Kindly type the tag index you want to untag!");
        }

        String blank = command.substring(5,6);

        if (!blank.isBlank()) {
            throw new TobiasException("Kindly type your task a space after untag!");
        }

        int index = Integer.parseInt(command.substring(6,7)) - 1;

        blank = command.substring(7,8);

        if (!blank.isBlank()) {
            throw new TobiasException("Kindly type your task a space after task index!");
        }

        int tagIndex = Integer.parseInt(command.substring(8)) - 1;

        if (index >= 0 && index < size) {
            return new UntagCommand(index, tagIndex);
        } else {
            throw new TobiasException("Invalid number provided! Please give a valid index from 1 to " + size +" !!");
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
        if (command.equals("hello")) {
            return helloParser();
        } else if(command.equals("list")) {
            return listParser();
        } else if (command.startsWith("todo")) {
            return todoParser(command);
        } else if (command.startsWith("deadline")) {
            return deadlineParser(command);
        } else if (command.startsWith("event")) {
           return eventParser(command);
        } else if (command.startsWith("mark")) {
            return markParser(command, tasks);
        } else if (command.startsWith("unmark")) {
            return unmarkParser(command, tasks);
        } else if (command.startsWith("delete")) {
            return deleteParser(command, tasks);
        } else if (command.startsWith("find")) {
            return findParser(command);
        } else if (command.equals("bye")) {
            return byeParser();
        } else if (command.startsWith("tag")) {
            return tagParser(command, tasks);
        } else if (command.startsWith("untag")) {
            return untagParser(command, tasks);
        } else if (command.equals("save")) {
            return saveParser();
        } else {
            throw new TobiasException("I apologise my sire... " +
                    "\nWhat you have entered is not something I yet understand :( " +
                    "\nCheck the user guide in the help menu for more info about my powers....");
        }
    }
}
