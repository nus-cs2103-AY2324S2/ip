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
    public static final String MESSAGE_BYE = " Bye. Hope to see you again soon!";
    private static final String INDENT = " ";
    private static final String BAR = "____________________________________________________________";
    private static final String FIRST = " Hello! I'm Pluiexo";
    private static final String SECOND = " What can I do for you?";
    private static final String[] GREET = new String[]{FIRST, SECOND};
    private static final String[] BYE = new String[]{BAR, MESSAGE_BYE, BAR};

    private static final BufferedReader INPUT_READER = new BufferedReader(new InputStreamReader(System.in));

    /**
     * Creates the GUI handler.
     */
    public Ui() {

    }

    /**
     * Displays the welcome greeting of the chatbot.
     */
    public static String sayWelcome() {
        StringBuilder returnString = new StringBuilder();
        for (String l : GREET) {
            returnString.append(INDENT).append(l);
            returnString.append("\n");
        }
        return returnString.toString();
    }

    /**
     * Make the ui say goodbye.
     *
     * @return returns the by message.
     */
    public static String sayBye() {
        return MESSAGE_BYE;
    }

    public static String convertToString(ArrayList<String> items) {
        StringBuilder ret = new StringBuilder();
        for (String item : items) {
            ret.append(INDENT).append(item);
            ret.append("\n");
        }
        return ret.toString();

    }

    /**
     * Decides what error message to show based on the DukeException error message
     *
     * @param msg DukeException error message.
     * @return The error message.
     */
    public static String handleError(String msg) {

        String errorResponse;
        switch (msg) {
        case "description":
            errorResponse = " OOPS!!! The description of this command cannot be empty.";
            break;
        case "from":
            errorResponse = " OOPS!!! the from date .";
            break;
        case "by":
            errorResponse = " OOPS!!! the by date cannot be empty.";
            break;
        case "dateError":
            errorResponse = "OOPS!!! Incorrect date format!!!";
            break;
        case "number":
            errorResponse = "OOPS!!! This is missing your index number";
            break;
        case "outOfRange":
            errorResponse = "Opps!!!!! Your index in out of range!";
            break;
        case "empty":
            errorResponse = "Opps!!!!! Your list is empty!!!You can't do any of these actions yet!";
            break;
        case "DateOutOfRange":
            errorResponse = "Ooops!!! Yoo Your date numbers ain't right";
            break;
        case "invalid":
            errorResponse = "OOPS!!! I'm sorry, incorrect command or input";
            break;
        default:
            errorResponse = "invalid application commence self-destruct";
        }
        return errorResponse;
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
}
