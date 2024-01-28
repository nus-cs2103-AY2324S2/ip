package duke.utils;

import java.util.Scanner;

/**
 * This class implements the User Interface of the bot.
 * 
 * @author delishad21
 */
public class Ui {

    private static final String spacer = "    ____________________________________________________________\n";
    private Scanner s;

    /**
     * Basic constructor for a Ui objecy that includes a Scanner to take in user input.
     */
    public Ui() {
        this.s = new Scanner(System.in);
    }

    /**
     * Method for retrieving user input.
     * 
     * @return User input as string.
     */
    public String nextCommand() {
        return this.s.nextLine();
    }

    /**
     * Method for closing Ui by closing the user input scanner.
     */
    public void closeUi() {
        this.s.close();
    }

    /**
     * Method for displaying startup message to user.
     */
    public void startupMessage() {
        String name = "CBBW";
        botPrint("Hello! I'm " + name 
                           + "\nWhat can I do for you?");
    }

    /**
     * Method for displaying goodbye message to user.
     */
    public void goodbyeMessage() {
        botPrint("See you again soon!");
    }

    /**
     * Method for displaying message to user with indent and seperating lines.
     * 
     * @param s String to be displayed to user
     */
    public void botPrint(String s) {
        s = s.replace("\n", "\n    ");
        System.out.println(spacer + "    " + s + "\n" + spacer);
    }


}
