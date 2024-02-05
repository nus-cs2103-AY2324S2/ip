package seedu.duke;

import java.util.Scanner;
public class UI {
    private Storage store;
    private Scanner scanner;
    public UI(Storage store) {
        this.scanner = new Scanner(System.in);
        this.store = store;
    }
    private static final String line = "      ________________________________________________________\n";
    private String logo = "     _______       ______     _______    _______    ___  ___\n"
            + "    |   _  \"\\     /    \" \\   |   _  \"\\  |   _  \"\\  |\"  \\/\"  |\n"
            + "    (. |_)  :)   // ____  \\  (. |_)  :) (. |_)  :)  \\   \\  /\n"
            + "    |:     \\/   /  /    ) :) |:     \\/  |:     \\/    \\\\  \\/\n"
            + "    (|  _  \\\\  (: (____/ //  (|  _  \\\\  (|  _  \\\\    /   /\n"
            + "    |: |_)  :)  \\        /   |: |_)  :) |: |_)  :)  /   /\n"
            + "    (_______/    \\\"_____/    (_______/  (_______/  |___/";
    public void intro() {
        System.out.println("Hello! I'm\n" + this.logo + "\n\n What can I do for you today? :)\n");
    }

    public static void emptyDesc(String tasktype) throws DukeException {
        if (tasktype.equals("todo")) {
            throw new DukeException("todo task.");
        } else if (tasktype.equals("deadline")) {
            throw new DukeException("task and the deadline.");
        } else if (tasktype.equals("event")) {
            throw new DukeException("event and provide the start and end timing.");
        }
    }

    public void bye() {
        System.out.print(line + "      Bye! Have a great day ahead :>\n" + line);
    }
}
