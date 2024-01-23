import java.util.Scanner;
public class Duke {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String[] List = new String[100];
        int length = 0;
        System.out.println("Hello! I'm Dukey.");
	    System.out.println("What can I do for you?");

        String input ;

        while (true) {
            input = scanner.nextLine();
            if (input.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                break;
            } else if (input.equals("list")) {
                if (length == 0) {
                    System.out.println("You have no items in your list!");
                    continue;
                } else {
                    for (int x = 0; x < length; x++) {
                        String item = List[x];
                        int numeric = x + 1;
                        System.out.println(numeric + ". " + item);
                    }
                    continue;
                }
            }
            List[length] = input;
            length = length + 1;
            System.out.println("added: " + input);
        }

    }
}
