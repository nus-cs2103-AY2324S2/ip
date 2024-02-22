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
    public String greet() {
        return "Hello! My name is Mitsuki, nice to meet you!\n"
                + "What can I do for you today?\n";
    }

    /**
     * Prints the user's previously saved list, if available.
     */
    public String loadList() {
        return "I have loaded your previously saved list for you. :)";
    }

    /**
     * Prints message to tell user that there is no previously saved list, so a new
     * list will be used.
     */
    public String loadError() {
        return "No saved list found. A new list will be used!";
    }

    /**
     * Prints message to inform user their command is invalid.
     */
    public String invalidCommandMessage() {
        return "Sorry, I am unable to do that at the current moment. "
                + "Please type 'help' for a list of commands you can give me! :)";
    }

    /**
     * Prints error message when an exception is thrown.
     *
     * @param ex the exception thrown.
     */
    public String printErrorMessage(Exception ex) {
        return "Something went wrong: " + ex.getMessage();
    }

    /**
     * Prints a list of commands that the user can give Mitsuki.
     *
     * @param commands the list of commands
     */
    public String printHelpList(ArrayList<String> commands) {
         //Lists all the commands the user can give Mitsuki.

            String message = "Here is a list of commands you can give me:\n";
            for (int i = 0; i < commands.size(); i++) {
                int j = i + 1;
                message = message + j + ". " + commands.get(i) + "\n";
            }
            return message;
        }

    /**
     * Prints the list of tasks related to keyWord in the user's current taskList.
     *
     * @param keyWord the keyWord that the tasks to be displayed must contain.
     * @param taskList the user's current todo list.
     */
    public String printFound(String keyWord, ArrayList<Task> taskList) {
        String message = "Here are the tasks related to: " + keyWord;
        for (int i = 0; i < taskList.size(); i++) {
            int j = i + 1;
            String task = taskList.get(i).toString();
            if (task.contains(keyWord)) {
                message = message + j + ". " + task + "\n";
            }
        }
        return message;
    }
    /**
     * Says goodbye to Mitsuki and exits the Chat bot.
     */
    public String printByeMessage() {
        return "Bye! Hope to see you again soon!\n";
    }
}
