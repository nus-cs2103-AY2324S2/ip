import java.util.HashMap;
import java.util.Scanner;

public class Taylor {
    public static void main(String[] args) {
        System.out.println("Hello! I'm Taylor");
        System.out.println("What can I do for you?");

        HashMap<Integer, String> listing = new HashMap<>();
        Integer pos = 1;

        while(true) {
            Scanner type = new Scanner(System.in);
            String input = type.nextLine();

            if (input.equals("bye")) {
                break;
            } else if (input.equals("list")) {
                // Lambda Function: Print the entire list together with their indexing
                listing.forEach((key, value) -> {
                    System.out.println(key + ". " + value);
                });
            } else {
                listing.put(pos, input);
                pos++;
                System.out.println("added: " + input);
            }
        }

        System.out.println("Bye. Hope to see you again soon!");
    }
}