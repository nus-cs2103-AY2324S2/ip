import java.util.ArrayList;
import java.util.Scanner;
public class Duke {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<String> userInputList = new ArrayList<>();
        String name = "James";
        System.out.println("Hello! I'm " + name + "\n");
        System.out.println("What can I do for you? \n");

        while(true) {
            String input = scanner.nextLine();

            if (input.equalsIgnoreCase("bye")) {
                System.out.println("Bye. Hope to see you again soon! \n");
                break;
            }

            else if (input.equalsIgnoreCase("list")) {
                for (int i = 0; i < userInputList.size(); i++) {
                    System.out.println((i + 1) + ". " + userInputList.get(i));
                }
            }

            else if (input.equalsIgnoreCase("blah")) {
                System.out.println("blah");
            }

            else {
                userInputList.add(input);
            }
        }
    }
}
