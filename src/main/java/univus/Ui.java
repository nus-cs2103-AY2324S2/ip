package univus;

import java.util.Scanner;


public class Ui {
    private final String LINE = "____________________________________________________________";
    private Scanner scanner;
    public Ui() {
        this.scanner = new Scanner(System.in);
    }
    public void greet() {
        System.out.println(LINE);
        System.out.println("Hello! I'm Univus");
        System.out.println("What can I do for you?");
        System.out.println(LINE);
    }
    public void bye() {
        System.out.println(LINE);
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(LINE);
    }


}
