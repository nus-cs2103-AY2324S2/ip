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

        while (true) {
            try {
                Scanner sc = new Scanner(System.in);

                String input = sc.next();
                if (input.equals("bye")) break;
                else if (input.equals("list")) {
                    list();
                } else if (input.equals("mark")) {
                    int index = sc.nextInt();
                    if (index > todo_list.size() || index <= 0) throw new Exception("Index has to be within list size!");
                    Task task = todo_list.get(index - 1);
                    task.mark();
                    System.out.println("_______________________________________________________\n " +
                            "Nice! I've marked this task as done:\n " + task +
                            "\n_______________________________________________________\n");
                } else if (input.toLowerCase().equals("unmark")) {
                    int index = sc.nextInt();
                    if (index > todo_list.size() || index <= 0) throw new Exception("Index has to be within list size!");
                    Task task = todo_list.get(index - 1);
                    task.unmark();
                    System.out.println("_______________________________________________________\n " +
                            "Ok, I've marked this task as not done yet:\n " + task +
                            "\n_______________________________________________________\n");
                } else if (input.toLowerCase().equals("delete")) {
                    int index = sc.nextInt();
                    if (index > todo_list.size() || index <= 0) throw new Exception("Index has to be within list size!");
                    Task task = todo_list.remove(index - 1);
                    System.out.println("_______________________________________________________\n " +
                            "Noted. I've removed this task from the list:\n " + task +
                            "\nNow you have " + todo_list.size() + " tasks in the list.\n" +
                            "_______________________________________________________\n");
                } else if (input.toLowerCase().equals("todo")) {
                    String str = sc.nextLine().trim();
                    if (str.length() == 0) throw new Exception("Todo task cannot be empty!");
                    addTodo(str);
                } else if (input.toLowerCase().equals("deadline")) {
                    String s = sc.nextLine();
                    if (s.split("/by").length != 2) {
                        throw new Exception("Please provide your deadline task in the following format:\n" +
                                "Deadline <description> /by <description>");
                    }
                    String name = s.split("/by")[0].trim();
                    String by = s.split("/by")[1].trim();
                    if (name.length() == 0 || by.length() == 0 ) {
                        throw new Exception(("Event names/to/from cannot be empty"));
                    }

                    addDeadline(name, by);
                } else if (input.toLowerCase().equals("event")) {
                    String s = sc.nextLine();
                    String[] split1 = s.split("/from");
                    if (split1.length != 2) {
                        throw new Exception("Please provide your event task in the following format:\n" +
                                "Event <description> /from <description> /to <description>");
                    }
                    String[] split2 = split1[1].split("/to");
                    if (split2.length != 2) {
                        throw new Exception("Please provide your event task in the following format:\n" +
                                "Event <description> /from <description> /to <description>");
                    }
                    String name = split1[0].trim();
                    String from = split2[0].trim();
                    String to = split2[1].trim();
                    if (name.length() == 0 || from.length() == 0 || to.length() == 0) {
                        throw new Exception(("Event names/to/from cannot be empty"));
                    }


                    addEvent(name, from, to);
                } else {
                    throw new Exception("Sorry! I do not understand what this means!");
                }
            } catch (Exception e) {
                System.out.println("_______________________________________________________\n " + e.getMessage() +
                        "\n_______________________________________________________\n ");
            }
        }

        String exit = "_______________________________________________________\n" +
                "Bye. Hope to see you soon!\n" +
                "_______________________________________________________\n";
        System.out.println(exit);
    }
}
