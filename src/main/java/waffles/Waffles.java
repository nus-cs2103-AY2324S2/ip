package waffles;

import waffles.exceptions.WafflesUnknownCommandException;
import waffles.parser.Parser;
import waffles.tasks.TaskList;
import waffles.ui.Launcher;

/**
 *  Waffles the chatbot.
 */
public class Waffles {
    private static final String GOODBYE_MESSAGE = "Bye. Hope to see you again soon!";
    private static final String UNKNOWN_COMMAND_MESSAGE = "The command '%s' is unknown. Please try again!";
    private static final TaskList taskList = new TaskList("/data/tasks.txt");
    private static boolean isOpen = true;

    public static void main(String[] args) {
        Launcher.main(args);
    }

    private static String runWaffles(String userInput) {

        if (isOpen) {
            Parser p = new Parser(userInput);
            Parser.Command command = p.getCommand();
            String arguments = p.getArgument();
            String output = "";

            switch (command) {
            case BYE:
                output = sayGoodbye();
                isOpen = false;
                break;
            case LIST:
                output = taskList.toString();
                break;
            case TODO:
                output = taskList.addToDoTask(arguments);
                break;
            case MARK:
                output = taskList.markTask(arguments);
                break;
            case UNMARK:
                output = taskList.unmarkTask(arguments);
                break;
            case DEADLINE:
                output = taskList.addDeadlineTask(arguments);
                break;
            case EVENT:
                output = taskList.addEventTask(arguments);
                break;
            case DELETE:
                output = taskList.deleteTask(arguments);
                break;
            case FIND:
                output = taskList.findTask(arguments);
                break;
            case INVALID:
                throw new WafflesUnknownCommandException(
                        String.format(UNKNOWN_COMMAND_MESSAGE, p.getUnknownCommand()));
            default:
                break;
            }

            return output;

        } else {
            return "Chat has already ended!";
        }
    }

    /**
     * Returns the response based on the input.
     * @param input Input from user.
     * @return String return message to user.
     */
    public String getResponse(String input) {
        try {
            return runWaffles(input);
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    /**
     * Greets the user when the chatbot is launched.
     */
    public String greet() {
        return String.format("Hello! I'm Waffles!%nWhat can I do for you?");
    }

    /**
     * Says bye to the user when the chatbot is closed.
     */
    public static String sayGoodbye() {
        return GOODBYE_MESSAGE;
    }

}
