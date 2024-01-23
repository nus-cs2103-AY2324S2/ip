import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

public class Mona {
    public static void main(String[] args) {
        String introduction = "  ____________________________________________________________\n"
                + "   Hello! I'm Mona\n"
                + "   What can I do for you?\n"
                + "  ____________________________________________________________\n";
        String farewell = "  ____________________________________________________________\n"
                + "   Bye. Hope to see you again soon!\n"
                + "  ____________________________________________________________";
        Scanner sc = new Scanner(System.in);
        List<String> tasks = new ArrayList<>();
        System.out.println(introduction);
        while (true) {
            String input = sc.nextLine();
            if (input.equals("bye")) {
                System.out.println(farewell);
                break;
            }
            if (input.equals("list")) {
                System.out.println("  ____________________________________________________________");
                for (int i = 0; i < tasks.size(); i++) {
                    System.out.println("    " + (i + 1) + ". " + tasks.get(i));
                }
                System.out.println("  ____________________________________________________________");
            } else {
                String response = "  ____________________________________________________________\n"
                        + "     added: " + input + "\n"
                        + "  ____________________________________________________________\n";
                tasks.add(input);
                System.out.println(response);
            }
        }

    }
}
