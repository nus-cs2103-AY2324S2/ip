package Parser;

import CustomExceptions.MalformedUserInputException;
import TaskList.Deadline;
import TaskList.Event;
import TaskList.Task;
import TaskList.Todo;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EventParser {
    public static Task toDoParser(String userInput) throws MalformedUserInputException {
        // Solution below adapted from https://www.w3schools.com/java/java_regex.asp
        Pattern pattern = Pattern.compile("^todo (.+)$");
        Matcher matcher = pattern.matcher(userInput);

        if (matcher.find()) {
            String task = matcher.group(1);
            Todo newTodoTask = new Todo(task);

            return newTodoTask;
        } else {
            throw new MalformedUserInputException();
        }
    }

    public static Task deadlineParser(String userInput) throws MalformedUserInputException {
        // Solution below adapted from https://www.w3schools.com/java/java_regex.asp
        Pattern pattern = Pattern.compile("^deadline (.+) \\/by (.+)$");
        Matcher matcher = pattern.matcher(userInput);

        if (matcher.find()) {
            String task = matcher.group(1);
            String deadline = matcher.group(2);

            Deadline newDeadline = new Deadline(task, deadline);

            return newDeadline;
        } else {
            throw new MalformedUserInputException();
        }
    }

    public static Task eventParser(String userInput) throws MalformedUserInputException {
        // Solution below adapted from https://www.w3schools.com/java/java_regex.asp
        Pattern pattern = Pattern.compile("^event (.+) \\/from (.+) \\/to (.+)$");
        Matcher matcher = pattern.matcher(userInput);

        if (matcher.find()) {
            String task = matcher.group(1);
            String from = matcher.group(2);
            String to = matcher.group(3);

            Event newEventTask = new Event(task, from, to);
            return newEventTask;
        } else {
            throw new MalformedUserInputException();
        }
    }


}
