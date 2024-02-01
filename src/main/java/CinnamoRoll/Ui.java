package CinnamoRoll;

import java.time.format.DateTimeFormatter;
class Ui {
    private final DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    private static final String logo = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";

    /**
     * Greet the user by printing out the logo and saying hi!
     */
    void greet() {
        System.out.println(logo);
        System.out.println("Hello! I'm CinnamoRoll!" + "\n" + "What can I do for you? \n");
    }

    /**
     * Saying bye to the user who attempts to exit the chat!
     */
    void exit() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    /**
     * Process and respond to the user's request in terms of input string
     */
    void respond(TaskList tasks, String str) throws Exception {
        tasks.respond(str);
    }
}