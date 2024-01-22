import java.util.Scanner;
public class Toothless {
    public static void main(String[] args) {
        Toothless.greet();
        Toothless.echo();
        Toothless.bye();
    }

    static void greet() {
        System.out.println("Hello! I'm Toothless!\n What can I do for you?");
    }

    static void bye() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    static void echo() {
        Scanner scanner = new Scanner(System.in);
        String input = "";

        while (!input.equals("bye")) {
            input = scanner.nextLine();
            if (!input.equals("bye")) {
                System.out.println(input);
            } else {
                break;
            }

        }
    }

}
