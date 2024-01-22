import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        List<String> tasksList = new ArrayList<>();
        System.out.println("DevGPT:\n\tHello! I'm DevGPT\nWhat can I do for you?");
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("User:");
            String input = scanner.nextLine();

            if (input.equals("bye")) {
                break;
            } else if (input.equals("list")) {
                System.out.println("DevGPT:");
                for (int i = 0; i < tasksList.size(); i++) {
                    System.out.println("\t" + (i + 1) + ". " + tasksList.get(i));
                }
            } else {
                tasksList.add(input);
                System.out.println("DevGPT:\n\t" + "added:" + input);
            }

        }
        scanner.close();
        System.out.println("DevGPT:\n\tBye. Hope to see you again soon!");

    }
}
