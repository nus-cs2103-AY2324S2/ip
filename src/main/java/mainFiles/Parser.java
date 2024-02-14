package mainfiles;

import actions.DeadlineAction;
import actions.DeleteAction;
import actions.EventAction;
import actions.FindAction;
import actions.HelpAction;
import actions.MarkAction;
import actions.SortAction;
import actions.TodoAction;
import actions.UnmarkAction;

/**
 * Parser class for Steven
 * Mainly used to understand the input given by the user and interpreting what the user wants
 */
public class Parser {
    private final UserInput userInput;
    private TaskList taskList;
    /**
     * Constructor for the Parser class
     * @param userInput The input of the user, broken down into arguments
     * @param taskList The list of tasks currently
     */
    public Parser(UserInput userInput, TaskList taskList) {
        this.userInput = userInput;
        this.taskList = taskList;
    }

    /**
     * Processes a given input and carries out the appropriate action
     * @return True, if the program exits ("bye"). False otherwise
     */
    public String processInput() {
        String outputString = "";
        switch (userInput.getInputName()) {
        case "list":
            outputString += "This is your list so far:\n" + taskList.printListString();
            break;
        case "unmark":
            outputString += new UnmarkAction().execute(userInput, taskList);
            break;
        case "mark":
            outputString += new MarkAction().execute(userInput, taskList);
            break;
        case "todo":
            outputString += new TodoAction().execute(userInput, taskList);
            break;
        case "deadline":
            outputString += new DeadlineAction().execute(userInput, taskList);
            break;
        case "event":
            outputString += new EventAction().execute(userInput, taskList);
            break;
        case "delete":
            outputString += new DeleteAction().execute(userInput, taskList);
            break;
        case "find":
            outputString += new FindAction().execute(userInput, taskList);
            break;
        case "sort":
            outputString += new SortAction().execute(userInput, taskList);
            break;
        case "help":
            outputString += new HelpAction().execute(userInput, taskList);
            break;
        case "bye":
            outputString += "I'll see you soon then!";
            break;
        default:
            outputString += "Hm, this doesn't seem like something can do for you. Try something else?"
                    + "\nSteven's advice: try typing \"help\" to see what user inputs are available.";
            break;
        }
        return outputString;
    }
}
