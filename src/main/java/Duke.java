import java.util.Scanner;
public class Duke {
    public static void main(String[] args) {
        String line = "__________________________________________________";
        System.out.println(line);
        System.out.println("Hello! I'm Floofy");
        System.out.println("What can I do for you?");
        System.out.println(line);
        Scanner scanner = new Scanner(System.in);
        while (true) {
            String userInput = scanner.nextLine(); // Read user input
            if ("bye".equalsIgnoreCase(userInput)) {
                break;
            } else {
                System.out.println(line);
                System.out.println(userInput);
                System.out.println(line);
            }
        }
        scanner.close();
        System.out.println(line);
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(line);
    }
}
