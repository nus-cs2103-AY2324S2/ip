import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Harvard {
    public static void main(String[] args) {
        String initial = "____________________________________________________________\n"
                + "Hello! I'm Harvard\n"
                + "What can I do for you?\n"
                + "____________________________________________________________\n";
        System.out.println(initial);

        List<String> tasks = new ArrayList<String>();

        while (true) {
            Scanner scanner = new Scanner(System.in);
            String echoInput = scanner.nextLine();

            if (echoInput.equals("bye")) {
                break;
            }

            switch (echoInput) {

                case "list":
                    System.out.println("____________________________________________________________\n");
                    for (int i = 1; i <= tasks.size(); i++) {
                        System.out.println(i + ". " + tasks.get(i - 1));
                    }
                    System.out.println("____________________________________________________________\n");
                    continue;

                default:
                    tasks.add(echoInput);
                    System.out.println("____________________________________________________________\n");
                    System.out.println("added: " + echoInput);
                    System.out.println("____________________________________________________________\n");
            }

        }

        String exit = "____________________________________________________________\n"
                + "Bye. Hope to see you again soon!\n"
                + "____________________________________________________________\n";
        System.out.println(exit);
    }
}
