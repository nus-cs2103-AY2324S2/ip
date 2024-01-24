import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Demon {
    public static void main(String[] args) {
        Scanner sc= new Scanner(System.in);
        System.out.println("Hello Master, I'm Demon 👿\nWhat can I do for you today?");
        String input = sc.nextLine();
        // list to store actions specified by user.
        List<String> list = new ArrayList<>();

        System.out.println("Entered: '" + input + "'");
        while (!input.equalsIgnoreCase("bye")) {
            if (input.equalsIgnoreCase("list")) {
                System.out.println("---------------------------------------------------------");
                System.out.print("List of things to do 📑:\n");
                for (int i = 1; i <= list.size(); i++) {
                    System.out.println("\t" + i + ". " + list.get(i-1));
                }
                System.out.println("--------------------------------------------------------");
                System.out.println("Anything else? Please let me know: ");
                input = sc.nextLine();
            } else {
                list.add(input);
                System.out.println("--------------------------------------------------------");
                System.out.println("🔖 Added to list of to-do: " + input);
                System.out.println("--------------------------------------------------------");
                System.out.println("Anything else? Please let me know: ");
                input = sc.nextLine();

            }
        }
        System.out.println("--------------------------------------------------------");
        System.out.println("Bye Master 👋, hope you had a great time, see you ♥!");
        System.out.println("--------------------------------------------------------");
    }
}
