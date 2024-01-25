import java.util.Scanner;
import java.util.ArrayList;
public class Duke {
    private static Scanner sc = new Scanner(System.in);
    private static ArrayList<String> messages = new ArrayList<>();
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

    static void add(String input) {
        Duke.breakLine();
        messages.add(input);
        System.out.println("added: " + input);
        Duke.breakLine();
    }
    static void list() {
        Duke.breakLine();
        for (int i = 0; i < messages.size(); i++) {
            System.out.printf("%d. %s\n", i + 1, messages.get(i));
        }
        Duke.breakLine();
    }

    public static void main(String[] args) {
        Duke.breakLine();
        Duke.greet();
        while (true) {
            String input = sc.nextLine();
            if (input.equals("bye")) {
                Duke.breakLine();
                Duke.exit();
                break;
            } else if (input.equals("list")) {
                Duke.list();
            } else {
                Duke.add(input);
            }
        }
    }
}
