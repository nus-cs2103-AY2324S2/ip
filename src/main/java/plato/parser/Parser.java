package plato.parser;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import plato.PlatoException;
import plato.task.Actions;
import plato.task.Manage;
import plato.task.Query;
import plato.task.TaskManager;
import plato.ui.Ui;

/**
 * Parser class to make sense of the input commands and decides the actions to give.
 */
public class Parser {
    private static final Pattern PATTERN_MANAGE = Pattern.compile("((?i)unmark|mark|delete) (\\d+)");
    private static final Pattern PATTERN_ACTIONS = Pattern.compile("((?i)todo|deadline|event) (.+)");
    private static final Pattern PATTERN_QUERY = Pattern.compile("((?i)find|view) (.+)");

    private static final String[] CLEAR = new String[]{"clear"};

    private static boolean isDead = false;

    /**
     * Parses a String action and performs it on the TaskManager or decides when it is to exit the program.
     *
     * @param command A string command to indicate what to do.
     * @param manager A TaskManager to perform actions on.
     * @return A String to output to the Ui for the actions from the parsed input
     * @throws PlatoException Invalid state in the command.
     */

    public static String[] parse(String command, TaskManager manager) throws PlatoException {
        Matcher manageMatch = PATTERN_MANAGE.matcher(command);
        Matcher actionMatch = PATTERN_ACTIONS.matcher(command);
        Matcher queryMatch = PATTERN_QUERY.matcher(command);


        if (command.matches("((?i)bye)")) {
            isDead = true;
            return new String[]{Ui.sayBye()};
        } else if (command.matches("((?i)list)")) {
            return manager.listItems();
        } else if (command.matches("((?i)clear)")) {
            return CLEAR;
        } else if (manageMatch.matches()) {
            Manage act = Manage.valueOf(manageMatch.group(1).toUpperCase());
            return manager.manageTask(act, manageMatch.group(2));
        } else if (actionMatch.matches()) {
            Actions act = Actions.valueOf(actionMatch.group(1).toUpperCase());
            return manager.addTask(act, actionMatch.group(2));
        } else if (queryMatch.matches()) {
            Query act = Query.valueOf(queryMatch.group(1).toUpperCase());
            return manager.queryTasks(act, queryMatch.group(2).trim());
        } else {
            throw new PlatoException("invalid");
        }
    }


    /**
     * @return Whether plato should close the chat-bot.
     */
    public static boolean isExit() {
        return isDead;
    }

}
