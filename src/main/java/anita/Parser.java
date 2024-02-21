package anita;

import java.util.Arrays;

/**
 * The Parser class handles all methods used to parse user commands.
 */
public class Parser {

    /**
     * Takes in the raw user input and returns the command.
     *
     * @param description The raw user input.
     * @return Command or action to be done.
     */
    public String parseCommand(String description) {
        String[] tokens = description.split("/", 2);
        String command = tokens[0].split(" ")[0];
        return command;
    }

    /**
     * Used for specific commands where an index is expected.
     * Parses the String input into an int and returns it.
     *
     * @param description The raw user input.
     * @return Index specified by the user.
     */
    public int indexParser(String description) {
        String[] tokens = description.split("/", 2);
        return Integer.parseInt(tokens[0].split(" ")[1]);
    }

    /**
     * Used to obtain the index of the task to be removed inputted by the user.
     * @param description The raw user input.
     * @return Int value of task to be removed.
     */
    public int removeParser(String description) {
        String[] tokens = description.split(" ");
        if (tokens.length == 1) {
            throw new ArrayIndexOutOfBoundsException("Please enter the index of the task to be deleted.");
        }
        return Integer.parseInt(tokens[1]);
    }

    /**
     * Used to obtain the substring entered by the user when using find.
     *
     * @param description The raw user input.
     * @return String of substring specified by user.
     */
    public String findParser(String description) {
        String[] tokens = description.split(" ", 2);
        return tokens[1];
    }

    /**
     * Custom parser for the Todo task.
     *
     * @param description The raw user input.
     * @return A String[] containing parts of the user input to be used for instantiation.
     */
    public String[] todoParser(String description) {
        String[] tokens = description.split("\\|", 2);
        String[] tokens1 = tokens[0].split(" ");
        String[] res = new String[2];
        String taskName = "";
        if (tokens1.length == 1) {
            throw new ArrayIndexOutOfBoundsException("The description of a todo cannot be empty.");
        }
        for (int i = 1; i < tokens.length; i++) {
            taskName += tokens1[i] + " ";
        }
        res[0] = taskName;
        res[1] = tokens[1];
        return res;
    }
    /**
     * Custom parser for the Deadline task.
     *
     * @param description The raw user input.
     * @return A String[] containing parts of the user input to be used for instantiation.
     */
    public String[] deadlineParser(String description) {
        String[] tokens = description.split("\\|", 2);
        String[] tokens1 = tokens[0].split("/", 2);
        String[] tokens2 = tokens1[0].split(" ", 2);
        String[] res = new String[3];
        String taskName = "";
        if (tokens2.length == 1) {
            throw new ArrayIndexOutOfBoundsException("The description of a deadline cannot be empty.");
        }
        for (int i = 1; i < tokens2.length; i++) {
            taskName += tokens2[i] + " ";
        }
        res[0] = taskName;
        res[1] = tokens1[1].split(" ")[1];
        res[2] = tokens[1];
        return res;
    }

    /**
     * Custom parser for the Event task.
     *
     * @param description The raw user input.
     * @return A String[] containing parts of the user input to be used for instantiation.
     */
    public String[] eventParser(String description) {
        String[] tokens = description.split("\\|", 2);
        String[] tokens1 = tokens[0].split("/", 3);
        String[] tokens2 = tokens1[0].split(" ", 2);
        String[] res = new String[4];
        String taskName = "";

        if (tokens2.length == 1) {
            throw new ArrayIndexOutOfBoundsException("The description of a deadline cannot be empty.");
        }
        for (int i = 1; i < tokens2.length; i++) {
            taskName += tokens2[i] + " ";
        }
        res[0] = taskName;
        res[1] = tokens1[1].split(" ")[1];
        res[2] = tokens1[2].split(" ")[1];
        res[3] = tokens[1];
        return res;
    }
}
