import java.util.ArrayList;
import java.util.Scanner;
import java.util.List;

public class Duke {
    static List<Task> todo_list = new ArrayList<>();
    private static void list() {
        System.out.println("_______________________________________________________\n");

        for (int i = 0; i < todo_list.size(); i++) {
            System.out.println(i + 1 + "." + todo_list.get(i));
        }

        System.out.println("_______________________________________________________\n");
    }
    private static void addTodo(String name) {
        ToDo todo = new ToDo(name);
        todo_list.add(todo);
        System.out.println("_______________________________________________________\n added: " +
                 todo + "\n Now you have " + todo_list.size() + " tasks in the list\n" +
                "_______________________________________________________\n");
    }

    private static void addDeadline(String name, String by) {
        Deadline deadline = new Deadline(name, by);
        todo_list.add(deadline);
        System.out.println("_______________________________________________________\n added: " +
                deadline + "\n Now you have " + todo_list.size() + " tasks in the list\n" +
                "_______________________________________________________\n");
    }

    private static void addEvent(String name, String from, String to) {
        Event event = new Event(name, from, to);
        todo_list.add(event);
        System.out.println("_______________________________________________________\n added: " +
                event + "\n Now you have " + todo_list.size() + " tasks in the list\n" +
                "_______________________________________________________\n");
    }

    public static void main(String[] args) {
        String greeting = "_______________________________________________________\n" +
                "Hello! I'm Thames and I'll be your assistant chatbot.\n" +
                "What can I do for you today?\n" +
                "_______________________________________________________\n";
        System.out.println(greeting);

        while(true) {
            Scanner sc = new Scanner(System.in);

            String input = sc.next();
            if (input.equals("bye")) break;
            else if (input.equals("list")) {
                list();
            }
            else if (input.equals("mark")) {
                int index = sc.nextInt();
                Task task = todo_list.get(index - 1);
                task.mark();
                System.out.println("_______________________________________________________\n " +
                        "Nice! I've marked this task as done:\n " + task +
                        "\n_______________________________________________________\n");
            }
            else if (input.equals("unmark")) {
                int index = sc.nextInt();
                Task task = todo_list.get(index - 1);
                task.unmark();
                System.out.println("_______________________________________________________\n " +
                        "Ok, I've marked this task as not done yet:\n " + task +
                        "\n_______________________________________________________\n");
            }
            else if (input.equals("todo")) {
                addTodo(sc.nextLine().trim());
            }
            else if (input.equals("deadline")) {
                String s = sc.nextLine();
                String name = s.split("/by")[0].trim();
                String by = s.split("/by")[1].trim();
                addDeadline(name, by);
            }
            else if (input.equals("event")) {
                String s = sc.nextLine();
                String[] split = s.split("/from");
                String name = split[0].trim();
                String from = split[1].split("/to")[0].trim();
                String to = split[1].split("/to")[1].trim();
                addEvent(name, from, to);
            }
        }

        String exit = "_______________________________________________________\n" +
                "Bye. Hope to see you soon!\n" +
                "_______________________________________________________\n";
        System.out.println(exit);
    }
}
