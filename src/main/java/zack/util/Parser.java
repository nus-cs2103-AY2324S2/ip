package zack.util;

import zack.ZackException;
import zack.commands.*;
import zack.Zack;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Parser {
    /**
     * Parses the user input into a command object.
     *
     * @param input The full user input string.
     * @return The command to be executed.
     * @throws ZackException If the command is unknown or invalid.
     */
    public static Command parse(String input) throws ZackException {
        String[] sections = input.split(" ", 2);
        String commandWord = sections[0].toLowerCase();

        try {
            Zack.TaskType taskType = Zack.TaskType.valueOf(commandWord.toUpperCase());

            switch (taskType) {
            case BYE:
                if (sections.length > 1) {
                    throw new ZackException("Please only type 'bye' if you want to quit.");
                }
                return new ByeCommand();
            case MARK:
            // fallthrough
            case UNMARK:
                if (sections.length < 2) {
                    throw new ZackException("No task index provided. Please specify the task index to mark.");
                }
                int index;
                try {
                    index = Integer.parseInt(sections[1].trim()) - 1;
                } catch (NumberFormatException e) {
                    throw new ZackException("Invalid task index. Please enter a valid number.");
                }
                boolean isDone = taskType == Zack.TaskType.MARK;
                return new MarkCommand(index, isDone);
            case LIST:
                if (sections.length > 1) {
                    throw new ZackException("Please only type 'list' to view the list of tasks.");
                }
                return new ListCommand();
            case TODO:
            // fallthrough
            case DEADLINE:
            // fallthrough
            case EVENT:
                if (sections.length < 2) {
                    throw new ZackException("The description of a " + sections[0] + " cannot be empty.");
                }
                return new AddTaskCommand(sections[1], taskType);
            case DELETE:
                if (sections.length < 2) {
                    throw new ZackException("No task index provided. Please specify the task index to delete.");
                }
                int deleteIndex;
                try {
                    deleteIndex = Integer.parseInt(sections[1].trim()) - 1;
                } catch (NumberFormatException e) {
                    throw new ZackException("Invalid task index. Please enter a valid number.");
                }
                return new DeleteCommand(deleteIndex);
            case DATE:
                if (sections.length < 2) {
                    throw new ZackException("No date provided. Please specify a date.");
                }

                LocalDate specificDate;
                try {
                    specificDate = LocalDate.parse(sections[1].trim(), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                } catch (DateTimeParseException e) {
                    throw new ZackException("Invalid date format. Please enter a date in yyyy-MM-dd format.");
                }
                return new DateCommand(specificDate);
            case FIND:
                if (sections.length < 2) {
                    throw new ZackException("No keyword provided. Please specify a keyword.");
                }
                return new FindCommand(sections[1]);
            default:
                throw new ZackException("I'm sorry, but I don't know what that means :-(");
            }
        } catch (IllegalArgumentException e) {
            throw new ZackException("I'm sorry, but I don't know what that means :-(");
        }
    }
}

