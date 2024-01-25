import java.util.Scanner;
public class Duke {
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
        Scanner sc = new Scanner(System.in);

        while (true) {
            String input = sc.nextLine();

            if (input.equals("bye")) {
                Duke.exit();
                break;
            } else {
                System.out.println(input);
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
