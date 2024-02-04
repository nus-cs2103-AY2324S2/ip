package drew.ui;

import java.util.Scanner;

public class Ui {
    public static final String DELIMITER = "______________________________________";
    Scanner sc;

    public Ui() {
        this.sc = new Scanner(System.in);
    }
    public void greetUser() {
        System.out.println(DELIMITER);
        System.out.println("Hello! I'm Drew");
        System.out.println("What can I do for you?");
        System.out.println(DELIMITER);
    }
    public void bidFarewell() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    public void reply(String input) {
        System.out.println(DELIMITER);
        System.out.print(input);
        System.out.println(DELIMITER);
    }

    public String readInput(){
        return sc.nextLine();
    }

}
