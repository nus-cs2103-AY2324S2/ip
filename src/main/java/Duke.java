import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Duke Class
 * This is the main class for the chatbot.
 */
public class Duke {
    public static void main(String[] args) {
        System.out.println("Hello! I'm GHBot");
        System.out.println("What can I do for you?");
        List<Task> lst = new ArrayList<>();

        while (true) {
            Scanner sc = new Scanner(System.in);
            String str = sc.nextLine();
            String[] subStr = str.split(" ", 2);
            String instr = subStr[0];

            if (instr.equalsIgnoreCase("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                break;
            } else if (instr.equalsIgnoreCase("list")) {
                System.out.println("Here are the tasks in your list:");
                for (int i = 0; i < lst.size(); i++) {
                    System.out.println(i + 1 + "." + lst.get(i));
                }
            } else if (instr.equalsIgnoreCase("mark")) {
                int lstNo = Integer.parseInt(subStr[1]);
                for (int i = 0; i < lst.size(); i++) {
                    if (i + 1 == lstNo) {
                        lst.get(i).isCompleted();
                        System.out.println("Nice! I've marked this task as done:\n" + lst.get(i));
                    }
                }
            } else if (instr.equalsIgnoreCase("unmark")) {
                int lstNo = Integer.parseInt(subStr[1]);
                for (int i = 0; i < lst.size(); i++) {
                    if (i + 1 == lstNo) {
                        lst.get(i).isNotCompleted();
                        System.out.println("OK, I've marked this task as not done yet:\n" + lst.get(i));
                    }
                }
            } else if (instr.equalsIgnoreCase("todo")) {
                Todo todo = new Todo(subStr[1]);
                lst.add(todo);
                System.out.println("Got it. I've added this task:\n" + todo);
                System.out.println("Now you have " + lst.size() + " tasks in the list.");

            } else if (instr.equalsIgnoreCase("deadline")) {
                String[] ss = subStr[1].split("/by");
                Deadline deadline = new Deadline(ss[0], ss[1]);
                lst.add(deadline);
                System.out.println("Got it. I've added this task:\n" + deadline);
                System.out.println("Now you have " + lst.size() + " tasks in the list.");

            } else if (instr.equalsIgnoreCase("event")) {
                String[] ss = subStr[1].split("/from");
                String[] ss2 = ss[1].split("/to");
                Event event = new Event(ss[0], ss2[0], ss2[1]);
                lst.add(event);
                System.out.println("Got it. I've added this task:\n" + event);
                System.out.println("Now you have " + lst.size() + " tasks in the list.");
            }
        }
    }
}
