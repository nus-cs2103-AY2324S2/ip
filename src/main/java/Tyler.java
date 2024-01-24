import java.util.*;

public class Tyler {
    public static void main(String[] args) {
        System.out.println("    Hello from Tyler");
        System.out.println("    What can I do for you?");
        System.out.println("    --------------------------------------------------");

        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();

        while(input.equals("bye") != true) {
            System.out.println("    --------------------------------------------------");
            System.out.println("    " + input);
            System.out.println("    --------------------------------------------------");
            input = sc.nextLine();
        }
        System.out.println("    Bye. Hope to see you again");
    }
}
