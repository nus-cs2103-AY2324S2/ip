import java.util.*;

public class Duke {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        List<Task> list = new ArrayList<>();
        System.out.println("Hello! I'm Lumiere\n" + "What can I do for you?\n");
        while (true) {
            String command = sc.nextLine();
            if (command.equals("list")) {
                System.out.println("Here are the tasks in your list:");
                for (int i = 0; i < list.size(); i++) {
                    System.out.println(Integer.toString(i + 1) + "." + list.get(i).stringify());
                }
            } else if (command.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                break;
            } else if (command.contains("unmark")) {
                System.out.println("OK, I've marked this task as not done yet:");
                int space = command.indexOf(" ");
                int num = Integer.parseInt(command.substring(space + 1)); // unmark X
                Task curr = list.get(num - 1);
                curr.unmark();
                list.set(num - 1, curr);
                System.out.println(list.get(num - 1).stringify());
            } else if (command.contains("mark")) {
                System.out.println("Nice! I've marked this task as done:");
                int space = command.indexOf(" ");
                int num = Integer.parseInt(command.substring(space + 1)); // mark X
                Task curr = list.get(num - 1);
                curr.mark();
                list.set(num - 1, curr);
                System.out.println(list.get(num - 1).stringify());
            } else { // to add new item
                System.out.println("Got it. I've added this task:");
                // create Task object with command
                int space = command.indexOf(" ");
                String type = command.substring(0, space);

                if (type.equals("todo")) {
                    Todo task = new Todo(command.substring(space + 1));
                    System.out.println(task.stringify());
                    list.add(task);
                } else if (type.equals("deadline")) {
                    String rest = command.substring(space + 1);
                    String[] description = rest.split(" /by ");
                    Deadline task = new Deadline(description[0], description[1]);
                    System.out.println(task.stringify());
                    list.add(task);
                } else if (type.equals("event")) {
                    String rest = command.substring(space + 1);
                    String[] description = rest.split(" /from ");
                    String[] time = description[1].split(" /to ");
                    Event task = new Event(description[0], time[0], time[1]);
                    System.out.println(task.stringify());
                    list.add(task);
                }

                System.out.println("Now you have " + Integer.toString(list.size()) + " tasks in the list.");
            }
        }
        sc.close();
    }
}
