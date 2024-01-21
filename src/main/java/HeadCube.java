import java.util.Scanner;
public class HeadCube {
    public static void main(String[] args) {
        greet();
        Scanner scanner = new Scanner(System.in);
        while (true) {
            String input = scanner.nextLine();

            if (input.equals("bye")) {
                exit();
                break;
            }

            echo(input);
        }
    }

    public static void greet() {
        System.out.println("Hello! I'm HeadCube\n" + "What can I do for you?\n");
    }

    public static void exit() {
        System.out.println("Bye. Hope to see you again soon!\n");
    }

    public static void echo(String input) {
        System.out.println(input);
        System.out.println();
    }
}
