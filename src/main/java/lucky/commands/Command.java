package lucky.commands;

import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import lucky.tasks.Task;
import lucky.ui.Ui;
/**
 * The Command class is a Java class that represents a general command.
 */
public class Command {
    protected String commandResponse;

    /**
     * Executes the command given by the input. It is meant to be overridden by subclasses of `Command` to perform
     * specific actions based on the input.
     *
     * @param tasks ArrayList of tasks.
     * @param input the details of the task being executed.
     * @throws CommandException if there is some invalid input.
     * @throws IOException if there is some error in reading/writing to file.
     */
    public void execute(ArrayList<Task> tasks, String[] input)
            throws CommandException, IOException {
        this.commandResponse = Ui.printOutput("I'm sorry, but I have zero idea what you're asking from me...");
    };


    protected boolean isValidCommand(String pattern, String[] input) {
        return isValidCommandLength(input) && isValidCommandFormat(pattern, input);
    }

    /**
     * Checks if the size of the array of string is >= 2.
     *
     * @param input The String[] input containing the command.
     * @return true if size >= 2.
     */
    protected boolean isValidCommandLength(String[] input) {
        return input.length >= 2;
    }

    /**
     * Checks if the command in the String[] input matches the correct format.
     *
     * @param pattern The pattern to check the format against with.
     * @param input The String[] input containing the command.
     * @return true if command matches the format.
     */
    protected boolean isValidCommandFormat(String pattern, String[] input) {
        Pattern regex = Pattern.compile(pattern);
        Matcher matcher = regex.matcher(input[1]);
        return matcher.matches();
    }

    /**
     * Returns the corresponding response to the command executed.
     *
     * @return Returns a String, containing the response.
     */
    public String getCommandResponse() {
        return this.commandResponse;
    }
}
