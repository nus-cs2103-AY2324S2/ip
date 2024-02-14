package duke.ui;

import java.util.Scanner;

public class Ui {

    /** Scanner for user input. */
    private final Scanner input = new Scanner(System.in);

    /**
     * Prints a message to the terminal, decorated with the Louie icon.
     * Printing an empty string just outputs the icon
     *
     * @param message The message to print.
     */
    public void print(String message) {
        boolean isFirst = true;
        StringBuilder sb = new StringBuilder();
        sb.append("\n");
        for (String s : message.split("\n")) {
            sb.append("    ");
            if (isFirst) {
                sb.append("(>^.^<)");
                isFirst = false;
            } else {
                sb.append("       ");
            }
            sb.append(" ").append(s).append("\n");
        }
        sb.append("\n");
        System.out.print(sb);
    }

    public String readInput() {
        return input.nextLine();
    }
}
