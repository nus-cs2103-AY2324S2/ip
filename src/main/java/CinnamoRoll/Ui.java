package CinnamoRoll;

import java.time.format.DateTimeFormatter;
class Ui {
    private final DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    private static final String logo = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";

    void greet() {
        System.out.println(logo);
        System.out.println("Hello! I'm CinnamoRoll!" + "\n" + "What can I do for you? \n");
    }

    void exit() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    void respond(TaskList tasks, String str) throws Exception {
        tasks.respond(str);
    }
}