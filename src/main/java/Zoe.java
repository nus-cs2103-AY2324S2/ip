import java.util.Scanner;
public class Zoe {
    public static void main(String[] args) {
        System.out.println("Hello! I'm Zoe");
        System.out.println("What can I do for you?");

        Scanner scanner = new Scanner(System.in);
        String command = scanner.nextLine();

        while (!command.equals("bye")) {
            System.out.println(command);
            command = scanner.nextLine();
        }

        System.out.println("Bye. Hope to see you again soon!");
        scanner.close();
    }
}
