package remi.io;

import java.util.Scanner;

/**
 * This takes messages from standard input
 */
public class Inputter {
    private static final Scanner sc = new Scanner(System.in);

    /**
     * This gets the last line of input from the user.
     *
     * @return a message containing the last input of the user
     */
    public static Message inputMessage() {
        return new Message(sc.nextLine());
    }
}
