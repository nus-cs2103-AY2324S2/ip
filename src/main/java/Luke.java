import java.util.ArrayList;
import java.util.Scanner;

public class Luke {
    public static void main(String[] args) {
        // Greetings
        System.out.println("__________________________________________");
        System.out.println("Hello! I'm Luke");
        System.out.println("What can I do for you?");
        System.out.println("__________________________________________");

        // user inputs
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        ArrayList<String> list = new ArrayList<String>();

        // Conditions
        while (!input.equals("bye")) {
            if (input.equals("list")) {
                System.out.println("__________________________________________");
                for (int i = 0; i < list.size(); i++) {
                    if (list.get(i) != null) {
                        System.out.println(i + 1 + ". " + list.get(i));
                    }
                }
                System.out.println("__________________________________________");

            } else {
                list.add(input);
                System.out.println("__________________________________________");
                System.out.println("added: " + input);
                System.out.println("__________________________________________");
            }
            input = sc.nextLine();
        }

        // Bye and exits
        System.out.println("__________________________________________");
        System.out.println("Bye! Hope to see you again soon!");
        System.out.println("__________________________________________");

    }
}
