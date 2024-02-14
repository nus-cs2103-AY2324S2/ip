package academicweapon.parser;

import academicweapon.action.Action;
import academicweapon.exceptions.DukeExceptions;

import java.util.ArrayList;

/**
 * Utility class for parsing user commands in the Duke application.
 * The Parser class extracts the action and parameters from the user inputs.
 */
public class Parser {

    /**
     * Parses the full command provided by the user.
     *
     * @param fullCommand The complete user input command
     * @return An Arraylist containing the action and parameters
     * @throws DukeExceptions If there is an error in parsing or validating the input
     */
    public static ArrayList<String> parse(String fullCommand) throws DukeExceptions {
        Action action;
        String parameters;
        String[] inputParts = fullCommand.split(" ", 2);
        ArrayList<String> lst = new ArrayList<>();
        assert inputParts.length >= 1 : "Input should contain at least the action";

        try {
            String str = inputParts[0].toUpperCase();
            action = Action.valueOf(str);
            lst.add(str);
        } catch (IllegalArgumentException e) {
            throw new DukeExceptions("Invalid AcademicWeapon.Action. Please enter a valid command.");
        }

        assert action != null : "Action should not be null";

        if (inputParts.length == 2) {
            parameters = inputParts[1];
        } else {
            parameters = " ";
        }

        try {
            DukeExceptions.validateInput(action.toString(), parameters);
        } catch (DukeExceptions e) {
            throw new DukeExceptions(e.getMessage());
        }

        switch (action) {
        case FIND:
            String keyword = inputParts[1];
            lst.add(keyword);
            break;
        case LIST:
            break;
        case BYE:
            break;
        case MARK:
            String indexToMark = String.valueOf(Integer.parseInt(inputParts[1]) - 1);
            lst.add(indexToMark);
            break;
        case UNMARK:
            String indexToUnmark = String.valueOf(Integer.parseInt(inputParts[1]) - 1);
            lst.add(indexToUnmark);
            break;
        case TODO:
            String description = inputParts[1];
            lst.add(description);
            break;
        case DEADLINE:
            String[] splitAgain = inputParts[1].split(" /by ");
            lst.add(splitAgain[0]);
            lst.add(splitAgain[1]);
            break;
        case EVENT:
            String[] splitOnce = inputParts[1].split("/from ");
            String[] splitTwice = splitOnce[1].split("/to ");
            lst.add(splitOnce[0]);
            lst.add(splitTwice[0]);
            lst.add(splitTwice[1]);
            break;
        case DELETE:
            String index = String.valueOf(Integer.parseInt(inputParts[1]) - 1);
            lst.add(index);
            break;
        default:
            throw new DukeExceptions("Invalid AcademicWeapon.Action. Please enter a valid command.");
        }

        return lst;
    }
}
