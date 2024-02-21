package duke.util;

import duke.command.Command;
import duke.command.ByeCommand;
import duke.command.ListCommand;
import duke.command.MarkCommand;
import duke.command.TodoCommand;
import duke.command.DeadlineCommand;
import duke.command.DefaultCommand;
import duke.command.EventCommand;
import duke.command.DeleteCommand;
import duke.command.FindCommand;
import duke.command.HelpCommand;
import duke.command.HelloCommand;
import duke.exception.DukeException;
import duke.exception.UnknownCommandException;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * The class representing the user command parser.
 * Note that this class should not be instantiated.
 * */
public class Parser {
    /* Enum of all possible command types. */
    enum Commands {
        bye, list, mark, unmark, todo, deadline, event, delete, find, help, hello
    }

    /**
     * Separates the user command into its respective components.
     *
     * @param command The user-entered command.
     * @return Array of constituent strings.
     * */
    public static String[] splitCommand(String command) {
        String words[] = command.split("\\s+");
        String currentString = "";
        ArrayList<String> result = new ArrayList<String>();
        for (int i = 0; i < words.length; i++) {
            if (i == 0) {
                result.add(words[i]);
                continue;
            }
            if (words[i].charAt(0) == '/') {
                result.add(currentString);
                currentString = "";
                continue;
            }
            currentString += words[i] + " ";
        }
        if (!currentString.equals("")) {
            result.add(currentString);
        }
        return result.toArray(new String[0]);
    }

    /**
     * Parses the user command.
     *
     * @param command The user-entered command.
     * @return The corresponding command with the parameters gathered from the original command.
     * @throws DukeException If command is invalid or incomplete.
     * */
    public static Command parseCommand(String command) throws DukeException {
        Command cmd = new DefaultCommand();
        try {
            String[] commandList = splitCommand(command);
            switch (Commands.valueOf(commandList[0])) {
            case bye:
                cmd =  new ByeCommand();
                break;
            case list:
                cmd = new ListCommand();
                break;
            case mark:
                cmd = new MarkCommand(commandList, "mark");
                break;
            case unmark:
                cmd = new MarkCommand(commandList, "unmark");
                break;
            case todo:
                cmd = new TodoCommand(commandList);
                break;
            case deadline:
                cmd = new DeadlineCommand(commandList);
                break;
            case event:
                cmd = new EventCommand(commandList);
                break;
            case delete:
                cmd = new DeleteCommand(commandList);
                break;
            case find:
                cmd = new FindCommand(commandList);
                break;
            case help:
                cmd = new HelpCommand();
                break;
            case hello:
                cmd = new HelloCommand();
                break;
            }

        } catch (IllegalArgumentException e) {
            throw new UnknownCommandException();
        }
        return cmd;
    }


}
