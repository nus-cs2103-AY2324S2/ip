package duke.parser;

import duke.DukeException;
import duke.task.Manage;
import duke.ui.Ui;
import duke.task.Actions;
import duke.task.TaskManager;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *  Parser class to make sense of the input commands and decides the actions to give.
 */
public class Parser {
    //Change to support regex instead to make things neater
    private static boolean isDead = false;
    static final Pattern PATTERN_MANAGE = Pattern.compile("((?i)unmark|mark|delete) (\\d+)");
    static final Pattern PATTERN_ACTIONS = Pattern.compile("((?i)todo|deadline|event) (.+)");

    /**
     * Parses a String action and performs it on the TaskManager or decides when it is to exit the program.
     * @param command  A string command to indicate what to do.
     * @param manager A TaskManager to perform actions on.
     * @return An ArrayList of string to output to the Ui for the actions from the parsed input
     * @throws DukeException Invalid state in the command.
     */
    public static ArrayList<String> parse(String command, TaskManager manager) throws DukeException {
        Matcher manageMatch = PATTERN_MANAGE.matcher(command);
        Matcher actionMatch = PATTERN_ACTIONS.matcher(command);
        if (command.matches("((?i)bye)")) {
            isDead = true;
            ArrayList<String> returnString = new ArrayList<>();
            returnString.add(Ui.MESSAGE_BYE);
            return returnString;
        }
        if (command.matches("((?i)list)")) {
            return manager.ListItems();
        } else {
            if (manageMatch.matches()) {
                Manage act = Manage.valueOf(manageMatch.group(1).toUpperCase());
                return manager.manageTask(act, manageMatch.group(2));
            } else if (actionMatch.matches()) {
                Actions act = Actions.valueOf(actionMatch.group(1).toUpperCase());
                return manager.addTask(act, actionMatch.group(2));
            } else {
                throw new DukeException("invalid");
            }
        }
    }

    /**
     * @returns Checks if it is time to exit the program.
     */
    public static boolean isExit() {
        return isDead;
    }

}
