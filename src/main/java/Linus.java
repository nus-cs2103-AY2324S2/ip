import java.util.*;

public class Linus {
    public static void main(String[] args) {
        System.out.println("Hello! I'm Linus!\nWhat can I do for you?\n\n");

        Scanner sc = new Scanner(System.in);

        // while loop to repeat printing of multiple Scanner inputs
        while (true) {
            String input = sc.nextLine();

            // Check if the user wants to exit.
            // When comparing strings for equality, you should use the equals() method, not the == operator.
            // The == operator checks if two string references point to the same object in memory.
            if (input.equals("bye")) {
                System.out.println("Bye. It's been a pleasure chatting with you!");
                break; // Exit the loop
            } else {
                System.out.println(input);
            }
        }
    }
}
