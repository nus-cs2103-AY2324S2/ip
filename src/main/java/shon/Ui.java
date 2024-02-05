package shon;

import java.util.Scanner;

public class Ui {

    private Scanner scanner;

    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    public void greet() {
        print("Hello! I'm Shon", "What can I do for you?");
    }

    public void exit() {
        print("Bye. Hope to see you again soon!");
        this.scanner.close();
    }

    public String readInput() {
        String input = this.scanner.nextLine();
        return input.strip();
    }

    public void print(String... messages) {
        String line = "    ____________________________________________________________";
        System.out.println(line);
        for (String msg : messages) {
            System.out.println("     " + msg);
        }
        System.out.println(line);
        System.out.println();
    }
}
