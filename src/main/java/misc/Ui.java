package misc;

import java.util.Scanner;

/**
 * This class represents the UI addition for running the chatbot.
 */
public class Ui {
    private static final String linebreak = "____________________________________________________________\n";

    /**
     * Constructor for Ui object.
     */
    public Ui() {
        new Scanner(System.in);
    }

    /**
     * Prints the starting line for the chatbot.
     */
    public void start() {
        String start = linebreak
                + "Hello! I'm Irwyn\n"
                + "What can I do for you?\n"
                + linebreak;
        System.out.println(start);
    }

    /**
     * Prints the ending line for the chatbot.
     */
    public void end() {
        String end = linebreak
                + "Bye. Hope to see you again soon!\n"
                + linebreak;
        System.out.println(end);
    }
    /**
     * Prints the message throughout the chatbot.
     * @param reply description of the reply.
     */
    public void reply(String reply) {
        System.out.println(linebreak + reply + linebreak);
    }

    /**
     * Gives the GUI the ending statement.
     * @return String to print for the GUI.
     */
    public String getEnd() {
        String end = linebreak
                + "Bye. Hope to see you again soon!\n"
                + linebreak;
        return end;
    }

    /**
     * Returns an output message to the user base on the input reply.
     *
     * @param reply Input reply by the execution of a command.
     */
    public String getReply(String reply) {
        return "Irwyn: \n"
                + reply;
    }
}
