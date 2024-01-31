package Dook;

import Command.AddCommand;
import Command.Command;
import Command.DeleteCommand;
import Command.MarkCommand;
import Command.ByeCommand;
import Command.ListCommand;
import Command.UnmarkCommand;
import Task.Deadline;
import Task.Event;
import Task.TaskType;
import Task.ToDo;
import Task.Task;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.time.LocalDateTime;

public class Parser {

    public static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
    private static final ArrayList<String> TASK_TYPES = new ArrayList<String>(Arrays.asList("todo", "deadline", "event"));
    public Command parse(String input) throws DookException {
        String[] split = input.split(" ", 2);
        String firstWord = split[0];
        if (input.equals("bye")) {
            return new ByeCommand();
        } else if (firstWord.equals("list")) {
            return new ListCommand();
        } else if (TASK_TYPES.contains(firstWord)) {
            String secondWord;
            try {
                secondWord = split[1];
            } catch (IndexOutOfBoundsException e) {
                throw new DookException("Noo!! task description cannot be empty!!");
            }
            switch (firstWord) {
                case "todo":
                    return new AddCommand(getTask(TaskType.TODO, secondWord));
                case "deadline":
                    return new AddCommand(getTask(TaskType.DEADLINE, secondWord));
                default:
                    return new AddCommand(getTask(TaskType.EVENT, secondWord));
            }
        } else if (firstWord.equals("mark")) {
            try {
                String secondWord;
                secondWord = split[1];
                return new MarkCommand(Integer.valueOf(secondWord));
            } catch (NumberFormatException | IndexOutOfBoundsException e) {
                throw new DookException("Ohnoo! Please enter a number after \"mark\"!");
            }
        } else if (firstWord.equals("unmark")) {
            try {
                String secondWord;
                secondWord = split[1];
                return new UnmarkCommand(Integer.valueOf(secondWord));
            } catch (NumberFormatException | IndexOutOfBoundsException e) {
                throw new DookException("Ohnoo! Please enter a number after \"unmark\"!");
            }
        } else if (firstWord.equals("delete")){
            try {
                String secondWord;
                secondWord = split[1];
                return new DeleteCommand(Integer.valueOf(secondWord));
            } catch (NumberFormatException | IndexOutOfBoundsException e) {
                throw new DookException("Ohnoo! Please enter a number after \"delete\"!");
            }
        } else {
            throw new DookException("I don't understand this command :( Try again!");
        }
    }

    public static Task getTask(TaskType taskType, String taskDetails) throws DookException {
        String name;
        String[] details;
        try {
            if (taskDetails.isBlank()) {
                throw new DookException(":( Task.Task description cannot be empty!");
            }
            switch (taskType) {
            case TODO:
                return new ToDo(taskDetails);
            case DEADLINE:
                details = taskDetails.split(" /by ", 2);
                name = details[0];
                LocalDateTime doBy = LocalDateTime.parse(details[1], formatter);
                return new Deadline(name, doBy);
            case EVENT:
                details = taskDetails.split(" /from ", 2);
                name = details[0];
                String[] startAndEnd = details[1].split(" /to ", 2);
                LocalDateTime start = LocalDateTime.parse(startAndEnd[0], formatter);
                LocalDateTime end = LocalDateTime.parse(startAndEnd[1], formatter);
                return new Event(name, start, end);
            default:
                throw new DookException("Oh nyo! Wrong format for " + taskType + " command!");
            }
        } catch (IndexOutOfBoundsException e) {
            throw new DookException("Oh nyo! Wrong format for " + taskType + " command!");
        }
    }

    public static Task parseFileLineToTask(String s) throws DookException {
        String[] split = s.split(" \\| ", 3);
        String taskTypeString = split[0];
        String isDoneString = split[1];
        String description = split[2];
        TaskType taskType;
        String[] details;
        String name;
        boolean isDone;
        switch (taskTypeString) {
        case "D":
            taskType = TaskType.DEADLINE;
            break;
        case "E":
            taskType = TaskType.EVENT;
            break;
        case "T":
            taskType = TaskType.TODO;
            break;
        default:
            throw new DookException("Invalid task type at \"" + s + "\".");
        }
        switch (isDoneString) {
        case "X":
            isDone = true;
            break;
        case " ":
            isDone = false;
            break;
        default:
            throw new DookException("Oh nyo!! Invalid completion indicator at \"" + s + "\"!");
        }
        try {
            switch (taskType) {
                case TODO:
                    return new ToDo(description, isDone);
                case DEADLINE:
                    details = description.split(" \\| ", 2);
                    name = details[0];
                    LocalDateTime doBy = LocalDateTime.parse(details[1].split("by: ", 2)[1], formatter);
                    return new Deadline(name, doBy, isDone);
                case EVENT:
                    details = description.split(" \\| ", 2);
                    name = details[0];
                    String[] startAndEnd = details[1].split(" to: ", 2);
                    LocalDateTime start = LocalDateTime.parse(startAndEnd[0].split("from: ", 2)[1], formatter);
                    LocalDateTime end = LocalDateTime.parse(startAndEnd[1], formatter);
                    return new Event(name, start, end, isDone);
                default:
                    throw new DookException("Oh nyo! Wrong format for " + taskType + " command in the file... :)");
            }
        } catch (Exception e) {
            throw new DookException("Oh nyo! Wrong format for " + taskType + " command in the file... :(");
        }
    }
}
