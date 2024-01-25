import java.util.Scanner;
import java.util.ArrayList;
public class Duke {
    private static Scanner sc = new Scanner(System.in);
    static void breakLine() {
        System.out.println("---------------------------------------");
    }
    static void greet() {
        System.out.println("Hello! I'm teletubby"  + "\nWhat can I do for you?");
        breakLine();
    }
    static void exit() {
        System.out.println("Bye. Hope to see you again soon!");
        breakLine();
    }
    static void echo() {

        while (true) {
            String input = sc.nextLine();

            if (input.equals("bye")) {
                Duke.breakLine();
                Duke.exit();
                break;
            } else {
                Duke.breakLine();
                System.out.println(input);
                Duke.breakLine();
            }
        }

        sc.close();
    }

    public static void main(String[] args) {
        Duke.breakLine();
        Duke.greet();
        Duke.echo();
    }
}
