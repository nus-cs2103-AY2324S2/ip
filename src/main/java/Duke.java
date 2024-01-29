import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        ArrayList<String> storage = new ArrayList<>();
        String logo = "__________________________________\n" +
                "Hello! I'm Tim \n" +
                "What can i do for you? \n" +
                "__________________________________\n";

        String exit = "Bye. Hope to see you again soon!\n" +
                "__________________________________";

        System.out.println(logo);

        Scanner scan = new Scanner(System.in);
        String input = scan.nextLine();
        while (!input.isEmpty()) {
            if (input.equals("bye")) {
                System.out.println(exit);
                break;
            }

            if (input.equals("list")) {
                System.out.println("__________________________________\n");
                for (int i = 0; i < storage.size(); i++) {
                    int counter = i + 1;
                    String output = counter + ". ";
                    System.out.println(output + storage.get(i));
                }
            } else {
                String msg = "__________________________________\n" +
                        "added:" + input;
                System.out.println(msg);
                storage.add(input);
            }
            System.out.println("__________________________________\n");
            input = scan.nextLine();

        }

    }
}
