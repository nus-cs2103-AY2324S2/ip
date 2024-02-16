package seedu.chatteroo.parser;

import seedu.chatteroo.commands.AddCommand;
import seedu.chatteroo.commands.Command;
import seedu.chatteroo.commands.DeleteCommand;
import seedu.chatteroo.commands.ExitCommand;
import seedu.chatteroo.commands.FindCommand;
import seedu.chatteroo.commands.MarkCommand;
import seedu.chatteroo.commands.PrintCommand;
import seedu.chatteroo.commands.UnmarkCommand;


import seedu.chatteroo.tasks.Task;
import seedu.chatteroo.tasks.ToDo;
import seedu.chatteroo.tasks.Deadline;
import seedu.chatteroo.tasks.Event;

/**
 * Parses the user input and returns the corresponding command.
 */
public class Parser {
    /**
     * Constructor for the Parser class.
     */
    public Parser() {
    }

    /**
     * Parses the user input and returns the corresponding command using a switch statement.
     * @param input The user input.
     * @return The corresponding command.
     * @throws Exception If the input is invalid.
     */
    public static Command parseInput(String input) throws Exception {
        assert input != null : "Input should not be null";

        String[] inputArr = input.split(" ");
        String command = inputArr[0].toUpperCase();
        switch (command) {
        case "LIST":
            return new PrintCommand();
        case "MARK":
            int taskNum = Integer.parseInt(inputArr[1]);
            return new MarkCommand(taskNum);
        case "UNMARK":
            taskNum = Integer.parseInt(inputArr[1]);
            return new UnmarkCommand(taskNum);
        case "DELETE":
            taskNum = Integer.parseInt(inputArr[1]);
            return new DeleteCommand(taskNum);
        case "FIND":
            if (input.length() < 5) {
                throw new Exception("ChatterOOHNOO! A find's description cannot be empty!");
            }
            String keyword = input.substring(5);
            return new FindCommand(keyword);
        case "TODO":
            if (input.length() < 5) {
                throw new Exception("ChatterOOHNOO! A todOO's description cannot be empty!");
            }
            input = input.substring(5);
            Task newTodo = new ToDo(input);
            return new AddCommand(newTodo);
        case "DEADLINE":
            if (input.length() < 10) {
                throw new Exception("ChatterOOHNOO! A deadline's description cannot be empty!");
            }
            String[] deadlineInputArr = input.substring(9).split("/by");
            input = deadlineInputArr[0];
            String by = deadlineInputArr[1];
            Task newDeadline = new Deadline(input, by);
            return new AddCommand(newDeadline);
        case "EVENT":
            if (input.length() < 7) {
                throw new Exception("ChatterOOHNOO! An event's description cannot be empty!");
            }
            input = input.substring(6);
            String[] eventInputArr = input.split("/from");
            input = eventInputArr[0];
            String[] timeArr = eventInputArr[1].split("/to");
            String from = timeArr[0];
            String to = timeArr[1];
            Task newEvent = new Event(input, from, to);
            return new AddCommand(newEvent);
        case "BYE":
            return new ExitCommand();
        default:
            throw new Exception("ChatterOOHNOO! I'm sorry, but Chatteroo don't know what that means :-(");
        }
    }

}
