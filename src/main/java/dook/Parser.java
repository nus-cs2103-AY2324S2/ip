package dook;

import java.util.ArrayList;
import java.util.Arrays;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import command.AddCommand;
import command.ByeCommand;
import command.Command;
import command.DanceCommand;
import command.DeleteCommand;
import command.FindCommand;
import command.ListCommand;
import command.MarkCommand;
import command.MeowCommand;
import command.UnmarkCommand;

import task.*;

public class Parser {

    public static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
    private static final ArrayList<String> TASK_TYPES = new ArrayList<>(Arrays
            .asList("todo", "deadline", "event", "doafter"));

    /**
     * Parses a given command as a String.
     *
     * @param input The String command.
     * @return The corresponding command, if valid.
     * @throws DookException If an invalid command is detected.
     */
    public static Command parse(String input) throws DookException {
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
            case "doafter":
                return new AddCommand(getTask(TaskType.DOAFTER, secondWord));
            case "event":
                return new AddCommand(getTask(TaskType.EVENT, secondWord));
            default:
                throw new DookException("UhOH!!! illegal task type! :(");
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
        } else if (firstWord.equals("delete")) {
            try {
                String secondWord;
                secondWord = split[1];
                return new DeleteCommand(Integer.valueOf(secondWord));
            } catch (NumberFormatException | IndexOutOfBoundsException e) {
                throw new DookException("Ohnoo! Please enter a number after \"delete\"!");
            }
        } else if (firstWord.equals("find")) {
            try {
                String secondWord;
                secondWord = split[1];
                return new FindCommand(secondWord);
            } catch (IndexOutOfBoundsException e) {
                throw new DookException("Ohnoo! Please enter a phrase after \"find\"!");
            }
        } else if (input.equals("meow")) {
            return new MeowCommand();
        } else if (input.equals("dance")) {
            return new DanceCommand();
        } else {
            throw new DookException("I don't understand this command :( Try again!");
        }
    }

    /**
     * Parses a given AddCommand as a String.
     *
     * @param taskType The specific type of the task.
     * @param taskDetails The remaining details as a String.
     * @return The corresponding Task, if valid.
     * @throws DookException If an invalid format is detected.
     */
    public static Task getTask(TaskType taskType, String taskDetails) throws DookException {
        String name;
        String[] details;
        try {
            if (taskDetails.isBlank()) {
                throw new DookException(":( Task description cannot be empty!");
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
            case DOAFTER:
                details = taskDetails.split(" /after ", 2);
                name = details[0];
                LocalDateTime doAfter = LocalDateTime.parse(details[1], formatter);
                return new DoAfter(name, doAfter);
            default:
                throw new DookException("Oh nyo! Wrong format for " + taskType + " command!");
            }
        } catch (Exception e) {
            throw new DookException("Oh nyo! Wrong format for " + taskType + " command!");
        }
    }

    /**
     * Parses a given Task as a String.
     *
     * @param s The input line from the file.
     * @return The corresponding Task, if valid.
     * @throws DookException If an invalid format is detected.
     */
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
        case "A":
            taskType = TaskType.DOAFTER;
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
            case DOAFTER:
                details = description.split(" \\| ", 2);
                name = details[0];
                LocalDateTime doAfter = LocalDateTime.parse(details[1].split("after: ", 2)[1], formatter);
                return new DoAfter(name, doAfter, isDone);
            default:
                throw new DookException("Oh nyo! Wrong format for " + taskType + " command in the file... :(");
            }
        } catch (Exception e) {
            throw new DookException("Oh nyo! Wrong format for " + taskType + " command in the file... :(");
        }
    }
}
