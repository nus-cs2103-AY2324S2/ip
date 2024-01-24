package Parser;

import CustomExceptions.BlankEventException;
import CustomExceptions.MalformedUserInputException;
import TaskList.Deadline;
import TaskList.Event;
import TaskList.Task;
import TaskList.Todo;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EventParser {
    public static Task toDoParser(String userInput) throws MalformedUserInputException, BlankEventException {
        // Solution below adapted from https://www.w3schools.com/java/java_regex.asp
        Pattern pattern = Pattern.compile("^todo (.+)$");
        Matcher matcher = pattern.matcher(userInput);

        if (matcher.find()) {
            String task = matcher.group(1).trim();

            if (task.isEmpty()) {
                throw new BlankEventException("Please do not enter an empty task.");
            } else {
                Todo newTodoTask = new Todo(task);
                return newTodoTask;
            }


        } else {
            throw new MalformedUserInputException();
        }
    }

    public static Task deadlineParser(String userInput) throws MalformedUserInputException, BlankEventException {
        // Solution below adapted from https://www.w3schools.com/java/java_regex.asp
        Pattern pattern = Pattern.compile("^deadline (.+) \\/by (.+)$");
        Matcher matcher = pattern.matcher(userInput);

        if (matcher.find()) {
            String task = matcher.group(1).trim();
            String deadline = matcher.group(2).trim();

            if (task.isEmpty()) {
                throw new BlankEventException("Please do not enter an empty task.");
            } else if (deadline.isEmpty()) {
                throw new BlankEventException("Please do not enter an empty deadline.");
            } else {
                Deadline newDeadline = new Deadline(task, deadline);
                return newDeadline;
            }

        } else {
            throw new MalformedUserInputException();
        }
    }

    public static Task eventParser(String userInput) throws MalformedUserInputException, BlankEventException {
        // Solution below adapted from https://www.w3schools.com/java/java_regex.asp
        Pattern pattern = Pattern.compile("^event (.+) \\/from (.+) \\/to (.+)$");
        Matcher matcher = pattern.matcher(userInput);

        if (matcher.find()) {
            String task = matcher.group(1).trim();
            String from = matcher.group(2).trim();
            String to = matcher.group(3).trim();

            if (task.isEmpty()) {
                throw new BlankEventException("Please do not enter an empty task.");
            } else if (from.isEmpty()) {
                throw new BlankEventException("Please do not enter an empty from date.");
            } else if (to.isEmpty()) {
                throw new BlankEventException("Please do not enter an empty to date.");
            } else {
                Event newEventTask = new Event(task, from, to);
                return newEventTask;
            }
        } else {
            throw new MalformedUserInputException();
        }
    }


}
