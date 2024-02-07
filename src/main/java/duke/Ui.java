package duke;

import java.util.Scanner;

/**
 * Obtains the user input from the user and passes it into the relevant functions.
 */
public class Ui {

    Scanner scanObj = new Scanner(System.in);
    private String userInput = "";

    /**
     * saves the next input given by the user in userInput.
     */
    public void inputMessage() {
        userInput = scanObj.nextLine();
    }

    /**
     * Sends the saved userInput as a String.
     * @return User input as a String.
     */
    public String getUserInput() {
        return userInput;
    }
}
