import java.util.*;
public class Cro {
    public static void handleInput(String input) {

        System.out.println("-----------------------------------");
        System.out.println(input);
        System.out.println("-----------------------------------");
    }
    public static void main(String[] args) {
        System.out.println("-----------------------------------");
        System.out.println("Hello! I'm Cro!");
        System.out.println("What can I do for you?");
        System.out.println("-----------------------------------");
        Scanner sc = new Scanner(System.in);
        while (true) {
            String input = sc.nextLine();
            if (input.equals("bye")) {
                System.out.println("-----------------------------------");
                System.out.println("Bye. Hope to see you again soon!");
                System.out.println("-----------------------------------");
                break;
            } else {
                handleInput(input);
            }
        }

    }
}
