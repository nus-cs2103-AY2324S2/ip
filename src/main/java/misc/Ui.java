package misc;

import java.util.Scanner;

public class Ui {
    private static final String linebreak = "____________________________________________________________\n";

    public Ui () {
        new Scanner(System.in);
    }


    public void start() {
        String start = linebreak
                + "Hello! I'm Irwyn\n"
                + "What can I do for you?\n"
                + linebreak;
        System.out.println(start);
    }

    public void end() {
        String end = linebreak
                + "Bye. Hope to see you again soon!\n"
                + linebreak;
        System.out.println(end);
    }

    public void reply (String reply) {
        System.out.println( linebreak + reply + linebreak);
    }

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