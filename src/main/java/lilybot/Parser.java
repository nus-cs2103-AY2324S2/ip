package lilybot;

/**
 * Parser class for helping the bot understand user commands.
 */
public class Parser {

    /**
     * Constructor of Parser class
     */
    public Parser() {

    }

    /**
     * Find the digit in the entered user command,
     * and convert it from a string to an integer.
     *
     * @param command The command that users entered.
     * @return The digit that users entered.
     */
    public static int parseInt(String command) {
        String[] cmd = command.split(" ", 2);
        //int taskNum = Integer.valueOf(command.replaceAll("[^0-9]", ""));
        int taskNum = Integer.valueOf(cmd[1]);
        return taskNum;
    }

    /**
     * This method helps the bot understand users' command
     * by splitting after the task type.
     *
     * @param command The command that users entered.
     * @return An array of String of processed user command.
     */
    public static String[] parseCommand(String command) {
        String[] cmd = command.split(" ", 2);
        return cmd;
    }


}
