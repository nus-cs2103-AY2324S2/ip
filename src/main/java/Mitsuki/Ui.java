package Mitsuki;

import java.util.ArrayList;
import java.util.Scanner;


/**
 * Deals with interactions with the user.
 *
 * @author Tee Chu Jie
 */
public class Ui {
    public Ui() {}

    /**
     * Prints the greeting message when user opens chatbot.
     */
    public void greet() {
        System.out.println("Hello! My name is Mitsuki, nice to meet you!\n"
                + "What can I do for you today?\n");
    }

    /**
     * Prints the user's previously saved list, if available.
     */
    public void loadList() {
        System.out.println("I have loaded your previously saved list for you. :)");
    }

    /**
     * Prints message to tell user that there is no previously saved list, so a new
     * list will be used.
     */
    public void loadError() {
        System.out.println("No saved list found. A new list will be used!");
    }

    /**
     * Prints message to inform user their command is invalid.
     */
    public void invalidCommandMessage() {
        System.out.println("Sorry, I am unable to do that at the current moment.");
        System.out.println("Please type 'help' for a list of commands you can give me! :)");
    }

    /**
     * Prints error message when an exception is thrown.
     *
     * @param ex the exception thrown.
     */
    public void printErrorMessage(Exception ex) {
        System.out.println("Something went wrong: " + ex.getMessage());
    }

    /**
     * Prints a list of commands that the user can give Mitsuki.
     *
     * @param commands the list of commands
     */
    public void printHelpList(ArrayList<String> commands) {
         //Lists all the commands the user can give Mitsuki.

            System.out.println("Here is a list of commands you can give me:");
            for (int i = 0; i < commands.size(); i++) {
                int j = i + 1;
                System.out.println(j + ". " + commands.get(i));
            }
        }

    /**
     * Says goodbye to Mitsuki and exits the Chat bot.
     */
    public void printByeMessage(Scanner scan) {
        System.out.println("Bye! Hope to see you again soon!\n");
        scan.close();
        System.exit(0);
    }
}
