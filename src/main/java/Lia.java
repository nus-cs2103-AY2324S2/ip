import java.util.*;
public class Lia {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Hello! I'm Lia :)");
        System.out.println("What can I do for you?");
        while (true) {
            String readInput = sc.nextLine();
            if (readInput.equals("bye")) {
                break;
            } else {
                System.out.println(readInput);
            }
        }
        System.out.println("Bye. Hope to see you again soon!");
    }
}
