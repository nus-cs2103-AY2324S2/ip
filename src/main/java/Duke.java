import java.util.Objects;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String greeting = "____________________________________________________________\n" +
                " Hello! I'm steve\n" +
                " What can I do for you?\n" +
                "____________________________________________________________";

        String goodbye = "____________________________________________________________\n" +
                        " Bye. Hope to see you again soon!\n" +
                        "____________________________________________________________\n";

        System.out.println(greeting);

        Task[] myList = new Task[100];
        int listIndex = 0;

        Scanner scanner = new Scanner(System.in);  // Create a Scanner object
        String cmd = scanner.nextLine(); // Get first input

        while (!Objects.equals(cmd, "bye")) {
            String border = "____________________________________________________________";

            System.out.println(border);
            switch (cmd.split(" ")[0]) {
                case "list":
                    for (int i = 0; i < listIndex; i++) {
                        System.out.println((i + 1) + ". " + myList[i]);
                    }
                    break;
                case "mark":
                    int num = Integer.valueOf(cmd.split(" ")[1]);
                    Task t = myList[num - 1];
                    t.markAsDone();

                    System.out.println("Nice! I've marked this task as done:");
                    System.out.println(t);
                    break;
                case "unmark":
                    num = Integer.valueOf(cmd.split(" ")[1]);
                    t = myList[num - 1];
                    t.unmarkAsDone();

                    System.out.println("OK, I've marked this task as not done yet:");
                    System.out.println(t);
                    break;
                default:
                    Task newTask = new Task(cmd);
                    myList[listIndex++] = newTask;
                    System.out.println("added: " + cmd);
                    break;
            }
            System.out.println(border);

            cmd = scanner.nextLine();
        }
        System.out.println(goodbye);

    }
}
