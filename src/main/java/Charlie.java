import java.util.Scanner;
public class Charlie {
    public static void main(String[] args) {
        System.out.println("Hello, I'm Charlie");
        System.out.println("What can I do for you?");

        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();

        while (true) {
            if (input.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                break;
            }
            break;
        }
    }
}
