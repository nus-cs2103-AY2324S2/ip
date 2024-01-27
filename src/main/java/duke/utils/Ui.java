package duke.utils;

import java.util.Scanner;

public class Ui {

    private static final String spacer = "    ____________________________________________________________\n";
    private Scanner s;

    public Ui() {
        this.s = new Scanner(System.in);
    }

    public String nextCommand() {
        return this.s.nextLine();
    }

    public void closeUi() {
        this.s.close();
    }

    public void startupMessage() {
        String name = "CBBW";
        botPrint("Hello! I'm " + name 
                           + "\nWhat can I do for you?");
    }

    public void goodbyeMessage() {
        botPrint("See you again soon!");
    }

    public void botPrint(String s) {
        s = s.replace("\n", "\n    ");
        System.out.println(spacer + "    " + s + "\n" + spacer);
    }


}
