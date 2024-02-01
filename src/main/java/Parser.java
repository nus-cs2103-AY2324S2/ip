public class Parser {
    /** All types of commands that is handled by the parser. */
    public enum UserCommands {
        B, // Exit the program
        D, // Create a deadline
        E, // Create a event
        L, // List all tasks
        M, // Mark a task
        R, // Remove/Delete a task
        T, // Create a todo
        U, // Unmark a task
        INVALID_CMD // Invalid command
    }

    /**
     * Checks if the given command is a valid command and returns the type of the given command.
     * 
     * @param cmd the command to check.
     * 
     * @return the type of command.
     */
    public UserCommands checkCommand(String cmd) {
        if (cmd.equals("bye") || cmd.equals("b")) {
            return UserCommands.B;
        } else if (cmd.equals("deadline") || cmd.equals("d")) {
            return UserCommands.D;
        } else if (cmd.equals("event") || cmd.equals("e")) {
            return UserCommands.E;
        } else if (cmd.equals("list") || cmd.equals("l")) {
            return UserCommands.L;
        } else if (cmd.equals("remove") || cmd.equals("r")) {
            return UserCommands.R;
        } else if (cmd.equals("mark") || cmd.equals("m")) {
            return UserCommands.M;
        } else if (cmd.equals("todo") || cmd.equals("t")) {
            return UserCommands.T;
        } else if (cmd.equals("unmark") || cmd.equals("u")) {
            return UserCommands.U;
        } else {
            return UserCommands.INVALID_CMD;
        }
    }
}
