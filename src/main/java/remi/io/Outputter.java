package remi.io;

/**
 * Class that takes in messages and outputs them in the proper format.
 */
public class Outputter {

    private static final int BORDER_LENGTH = 50;

    /**
     * Display message in a certain format.
     *
     * @param msg message to be outputted, you do not need to end it with a newline.
     */
    public static void outputMessage(Message msg) {
        System.out.println("-".repeat(BORDER_LENGTH));
        System.out.println(msg.getMessage());
        System.out.println("-".repeat(BORDER_LENGTH));
    }
}
