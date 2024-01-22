import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        System.out.println("DevGPT:\n\tHello! I'm DevGPT\nWhat can I do for you?");
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("User:");
            String input = scanner.nextLine();
            if (input.equals("bye")) {
                break;
            } else {
                System.out.println("DevGPT:\n\t" + input);
            }
        }
        scanner.close();

        System.out.println("DevGPT:\n\tBye. Hope to see you again soon!");

    }
}
