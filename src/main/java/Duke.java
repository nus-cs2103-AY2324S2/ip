import java.util.Scanner;
public class Duke {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String name = "James";
        System.out.println("Hello! I'm " + name + "\n");
        System.out.println("What can I do for you? \n");

        while(true) {
            String input = scanner.nextLine();

            if (input.equalsIgnoreCase("bye")) {
                System.out.println("Bye. Hope to see you again soon! \n");
                break;
            }

            if (input.equalsIgnoreCase("list")) {
                System.out.println("list");
            }

            if (input.equalsIgnoreCase("blah")) {
                System.out.println("blah");
            }
        }
    }
}
