package me.ruibin.leto.parser;

import java.util.Scanner;

import me.ruibin.leto.ui.Ui;

/**
 * Class for Parsing command and carrying out commands.
 */
public class Parser {
    private static Scanner sc = new Scanner(System.in);

    public Parser() {
    }

    /**
     * Reads user inputs and parse it, running the resulting command.
     * Loops until it encounters a command that returns <code>ResultTypes.EXIT</code>
     */
    public static void readExecuteLoop() {
        boolean isBye = false;

        while (!isBye) {
            String inputs = sc.nextLine();
            try {
                Result r = readAndExecute(inputs);
                if (r.getType().equals(ResultTypes.EXIT)) {
                    isBye = true;
                } else if (r.getType().equals(ResultTypes.ERROR)) {
                    Ui.shortSay("Error encountered!\n" + r.getLatestException().getMessage());
                }
            } catch (ArrayIndexOutOfBoundsException | IllegalArgumentException e) {
                Commands.HELP.run("");
            } // End try

        } // End command while loop
        System.out.println("  <<Duke Leto>>\n  > I bid you farewell");
    }

    /**
     * Read user inputs and run the command.
     *
     * @param inputs String of the input
     * @return Result containing type, message and exception if any.
     */
    public static Result readAndExecute(String inputs) {
        String[] commands = inputs.split(" ");
        Commands cmd;
        try {
            cmd = Commands.valueOf(commands[0].toUpperCase());
            assert cmd != null : "Command is null, should never happen";
            return cmd.run(inputs);
        } catch (ArrayIndexOutOfBoundsException | IllegalArgumentException e) {
            return Commands.HELP.run("");
        }
    }
}
