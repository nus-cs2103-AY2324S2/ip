import java.util.Scanner;

public class Luke {
    public static void main(String[] args) {
        // Greetings
        System.out.println("__________________________________________");
        System.out.println("Hello! I'm Luke");
        System.out.println("What can I do for you?");
        System.out.println("__________________________________________");

        // Echo
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();

        while (!input.equals("bye")) {
            System.out.println("__________________________________________");
            System.out.println(input);
            System.out.println("__________________________________________");
            input = sc.nextLine();
        }

        // Bye and exits
        System.out.println("__________________________________________");
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println("__________________________________________");

    }
}
