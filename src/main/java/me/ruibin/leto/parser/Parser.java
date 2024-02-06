package me.ruibin.leto.parser;

import java.util.Scanner;

import me.ruibin.leto.ui.Ui;

public class Parser {
    private static Scanner sc = new Scanner(System.in);

    public Parser() {
    }

    public static void readCommandAndExecute() {
        boolean isBye = false;

        while (!isBye) {
            String inputs = sc.nextLine();
            String[] commands = inputs.split(" ");

            Commands cmd;
            try {
                cmd = Commands.valueOf(commands[0].toUpperCase());
                Results r = cmd.run(inputs);
                if (r.equals(Results.EXIT)) {
                    isBye = true;
                } else if (r.equals(Results.ERROR)) {
                    Ui.shortSay("Error encountered!");
                }
            } catch (ArrayIndexOutOfBoundsException | IllegalArgumentException e) {
                Commands.HELP.run("");
            } // End try
        } // End command while loop
        System.out.println("  <<Duke Leto>>\n  > I bid you farewell");
    }
}
