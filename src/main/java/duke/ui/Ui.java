package duke.ui;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import duke.DukeException;


/**
 * Commandline GUI class to handle all the printing in the application.
 */

public class Ui {
    public static final String INDENT = "   ";
    public static final String BAR = "____________________________________________________________";
    public static final String MESSAGE_BYE = " Bye. Hope to see you again soon!";
    private static final String FIRST = " Hello! I'm Pluiexo";
    private static final String SECOND = " What can I do for you?";
    private static final String[] GREET = new String[]{BAR, FIRST, SECOND, BAR};
    private static final String[] BYE = new String[]{BAR, MESSAGE_BYE, BAR};

    private static final BufferedReader INPUT_READER = new BufferedReader(new InputStreamReader(System.in));

    /**
     * Creates the GUI handler.
     */
    public Ui() {

    }

    /**
     * Displays a break line bar of dashes with indentation.
     */
    public void showLine() {
        System.out.println(INDENT + BAR);
    }

    /**
     * Draws an empty line in output.
     */
    public void spacer() {
        System.out.println();
    }

    /**
     * Displays the welcome greeting of the chatbot.
     */
    public void showWelcome() {
        for (String l : GREET) {
            System.out.println(INDENT + l);
        }
    }

    /**
     * Scans the next line for instructions to the chatbot.
     *
     * @return String value of the user input.
     * @throws DukeException
     */
    public String readInstructions() throws DukeException {
        String test = "";
        try {
            test = INPUT_READER.readLine();
        } catch (IOException e) {
            throw new DukeException("Gigaboom");
        }
        return test;
    }

    /**
     * Prints to the output console of all the items in the list object.
     *
     * @param data An ArrayList of string to print everything to the output with indentation.
     */
    public void printOutput(ArrayList<String> data) {
        for (String item : data) {
            System.out.println(INDENT + item);
        }
    }

    /**
     * Prints to the output console of all the items in the array object.
     *
     * @param data A String array to print everything to the output with indentation.
     */
    public void printOutput(String[] data) {
        for (String item : data) {
            System.out.println(item);
        }
    }

    /**
     * Decides what error message to show based on the DukeException error message
     *
     * @param error DukeException error message.
     */
    public void showError(String error) {
        //Used when your user does not know how to use the application
        String errorMsg;
        switch (error) {
        case "description":
            errorMsg = " OOPS!!! The description of this command cannot be empty.";
            break;
        case "from":
            errorMsg = " OOPS!!! the from date .";
            break;
        case "by":
            errorMsg = " OOPS!!! the by date cannot be empty.";
            break;
        case "dateError":
            errorMsg = "OOPS!!! Incorrect date format!!!";
            break;
        case "number":
            errorMsg = "OOPS!!! This is missing your index number";
            break;
        case "outOfRange":
            errorMsg = "Opps!!!!! Your index in out of range!";
            break;
        case "empty":
            errorMsg = "Opps!!!!! Your list is empty!!!You can't do any of these actions yet!";
            break;
        case "DateOutOfRange":
            errorMsg = "Ooops!!! Yoo Your date numbers ain't right";
            break;
        case "invalid":
            errorMsg = "OOPS!!! I'm sorry, incorrect command or input";
            break;
        default:
            errorMsg = "invalid application commence self-destruct";
        }
        System.out.println(INDENT + errorMsg);
    }
}
