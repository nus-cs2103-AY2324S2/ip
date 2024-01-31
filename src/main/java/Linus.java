import java.util.*;

public class Linus {
    public static void main(String[] args) {
        System.out.println("Hello! I'm Linus!\nWhat can I do for you?\n\n");

        Scanner sc = new Scanner(System.in);
        ArrayList<String> tasks = new ArrayList<>();

        // while loop to repeat printing of multiple Scanner inputs
        while (true) {
            String input = sc.nextLine();

            // Check if the user wants to exit.
            // When comparing strings for equality, you should use the equals() method, not the == operator.
            if (input.equals("bye")) { // exit chat
                System.out.println("Bye. It's been a pleasure chatting with you!");
                break;
            } else if (input.equals("list")) { // list tasks
                for (int i = 0; i < tasks.size(); i++) {
                    System.out.println((i + 1) + ". " + tasks.get(i));
                }
            } else { // add tasks
                System.out.println("added: " + input); // println automatically inserts new line
                tasks.add(input);
            }
        }

        // Close the scanner
        sc.close();
    }
}
