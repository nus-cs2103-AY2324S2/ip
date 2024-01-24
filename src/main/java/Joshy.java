import java.util.Objects;
import java.util.Scanner;

public class Joshy {
    public static void main(String[] args) {
        System.out.println("   ______________________________________________");
        System.out.println("   Hello! I'm Joshy");
        System.out.println("   What can I do for you?");
        System.out.println("   ______________________________________________");

        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        while (!Objects.equals(input, "bye")) {
            System.out.println("   ______________________________________________");
            System.out.println("   " + input);
            System.out.println("   ______________________________________________");
            input = scanner.nextLine();
        }

        System.out.println("   ______________________________________________");
        System.out.println("   Bye. Hope to see you again soon!");
        System.out.println("   ______________________________________________");
    }
}
