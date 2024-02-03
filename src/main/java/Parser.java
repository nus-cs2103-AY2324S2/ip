import java.util.ArrayList;

public class Parser {

    public static ArrayList<String> parse(String fullCommand) throws DukeExceptions{
        Action action;
        String parameters;
        String[] inputParts = fullCommand.split(" ", 2);
        ArrayList<String> lst = new ArrayList<>();

        try {
            String str = inputParts[0].toUpperCase();
            action = Action.valueOf(str);
            System.out.println(action);
            lst.add(str);
        } catch (IllegalArgumentException e) {
            throw new DukeExceptions("Invalid Action. Please enter a valid command.");
        }

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
                throw new DukeExceptions("Invalid Action. Please enter a valid command.");
        }

        return lst;
    }
}
