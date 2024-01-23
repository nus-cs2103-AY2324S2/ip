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
                        "____________________________________________________________";

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
                case "todo":
                    String newTaskString = cmd.substring("todo".length());

                    Task newTask = new Todo(newTaskString);
                    myList[listIndex++] = newTask;

                    System.out.println("Got it. I've added this task:");
                    System.out.println(newTask);
                    System.out.println("Now you have " + listIndex + " tasks in the list");
                    break;
                case "deadline":
                    newTaskString = cmd.substring("deadline".length());
                    String desc = newTaskString.split("/by")[0].trim();
                    String by = newTaskString.split("/by")[1].trim();

                    newTask = new Deadline(desc, by);
                    myList[listIndex++] = newTask;

                    System.out.println("Got it. I've added this task:");
                    System.out.println(newTask);
                    System.out.println("Now you have " + listIndex + " tasks in the list");
                    break;
                case "event":
                    newTaskString = cmd.substring("event".length());
                    desc = newTaskString.split("/from")[0];
                    String from = newTaskString.split("/from")[1].split("/to")[0].trim();
                    String to = newTaskString.split("/to")[1].trim();

                    newTask = new Event(desc, from, to);
                    myList[listIndex++] = newTask;

                    System.out.println("Got it. I've added this task:");
                    System.out.println(newTask);
                    System.out.println("Now you have " + listIndex + " tasks in the list");
                    break;
                default:
                    System.out.println("I'm not sure what that means :3");
                    break;
            }
            System.out.println(border);

            cmd = scanner.nextLine();
        }
        System.out.println(goodbye);

    }
}
