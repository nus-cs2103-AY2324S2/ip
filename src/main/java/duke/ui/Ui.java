package duke.ui;

import duke.DukeException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Ui {
    public static final String INDENT = "   ";
    public static final String BAR = "____________________________________________________________";
    private static final String FIRST = " Hello! I'm Pluiexo";
    private static final String SECOND = " What can I do for you?";
    public static final String MESSAGE_BYE = " Bye. Hope to see you again soon!";
    private static final String[] GREET = new String[]{BAR, FIRST, SECOND, BAR};
    private static final String[] BYE = new String[]{BAR, MESSAGE_BYE, BAR};

    private final BufferedReader READER = new BufferedReader(new InputStreamReader(System.in));
    ;

    public Ui() {

    }

    public void showLine() {
        System.out.println(INDENT + BAR);
    }

    public void spacer() {
        System.out.println();
    }

    public void showWelcome() {
        for (String l : GREET) {
            System.out.println(INDENT + l);
        }
    }

    public void showGoodbye() {
        for (String l : BYE) {
            System.out.println(INDENT + l);
        }
    }

    public String readInstructions() throws DukeException {
        String test = "";
        try {
            test = READER.readLine();
        } catch (IOException e) {
            throw new DukeException("Gigaboom");
        }
        return test;
    }

    public void printOutput(ArrayList<String> data) {
        for (String item : data) {
            System.out.println(INDENT + item);
        }
    }

    public void printOutput(String[] data) {
        for (String item : data) {
            System.out.println(item);
        }
    }

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
            case "invalid":
                errorMsg = "OOPS!!! I'm sorry, incorrect command or input";
                break;
            default:
                errorMsg = "invalid application commence self-destruct";
        }
        System.out.println(INDENT + errorMsg);
    }
}
