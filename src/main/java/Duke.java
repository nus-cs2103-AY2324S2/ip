import java.util.*;

public class Duke {
    private static List<Task> taskList = new ArrayList<>();

    public static void main(String[] args) {
        String bot_name = "Calvin";
        String greeting_msg = "Hello! I'm " + bot_name + "\n"
                + "What can I do for you?";
        String exit_msg = "Bye. Hope to see you again soon!\n";
        System.out.println(greeting_msg);

        Scanner reader = new Scanner(System.in);

        while (true) {
            String user_input = reader.nextLine();
            String[] inputs = user_input.split(" ", 2);

            try {
                if (inputs[0].isEmpty()) throw new DukeException("Please enter something");
                else if (inputs[0].equals("bye")) {
                    break;

                } else if (inputs[0].equals("list")) {
                    System.out.println("\nHere are the tasks in your list:");
                    for (int i = 0; i < Duke.taskList.size(); i++) {
                        int k = i + 1;
                        System.out.println(k + ". " + Duke.taskList.get(i));
                    }

                } else if (inputs[0].equals("mark")) {
                    if (inputs.length == 1 || inputs[1].isEmpty()) throw new DukeException("Sorry. Please specify the item no. you want to mark");
                    int idx = Integer.parseInt(inputs[1]);
                    Task tmp = Duke.taskList.get(idx-1);
                    tmp.mark();
                    System.out.println("\nNice! I've marked this task as done:\n" + tmp);

                } else if (inputs[0].equals("unmark")) {
                    if (inputs.length == 1 || inputs[1].isEmpty())
                        throw new DukeException("Sorry. Please specify the item no. you want to unmark");
                    int idx = Integer.parseInt(inputs[1]);
                    Task tmp = Duke.taskList.get(idx - 1);
                    tmp.unmark();
                    System.out.println("\nNice! I've marked this task as done:\n" + tmp);

                } else if (inputs[0].equals("delete")) {
                    if (inputs.length == 1 || inputs[1].isEmpty())
                        throw new DukeException("Sorry. Please specify the item no. you want to delete");
                    int idx = Integer.parseInt(inputs[1]);
                    if (idx < 1 || idx > taskList.size())
                        throw new DukeException("Sorry that item no. is not the list");
                    Task tmp = Duke.taskList.get(idx - 1);
                    System.out.println("\nNoted. I've removed this task:\n" + tmp);
                    Duke.taskList.remove(idx - 1);
                } else if (inputs[0].equals("todo")) {
                    if (inputs.length == 1 || inputs[1].isEmpty()) throw new DukeException("Sorry. Please specify a description");
                    Task tmp = new Todo(inputs[1]);
                    Duke.taskList.add(tmp);
                    System.out.println("\nGot it. I've added this task:");
                    System.out.println(tmp);
                    System.out.println("Now you have " + Duke.taskList.size() + " tasks in the list.");
                } else if (inputs[0].equals("deadline")) {
                    if (inputs.length == 1 || inputs[1].isEmpty()) throw new DukeException("Sorry. Please specify a description");
                    String[] str = inputs[1].split(" /by ");
                    Task tmp = new Deadline(str[0], str[1]);
                    Duke.taskList.add(tmp);
                    System.out.println("\nGot it. I've added this task:");
                    System.out.println(tmp);
                    System.out.println("Now you have " + Duke.taskList.size() + " tasks in the list.");
                } else if (inputs[0].equals("event")) {
                    if (inputs.length == 1 || inputs[1].isEmpty()) throw new DukeException("Sorry. Please specify a description");
                    String[] str = inputs[1].split(" /from ");
                    String[] from_to = str[1].split(" /to ");
                    Task tmp = new Event(str[0], from_to[0], from_to[1]);
                    Duke.taskList.add(tmp);
                    System.out.println("\nGot it. I've added this task:");
                    System.out.println(tmp);
                    System.out.println("Now you have " + Duke.taskList.size() + " tasks in the list.");
                } else {
                    throw new DukeException("Sorry. I don't know what that means.");
                }
            } catch (DukeException e) {
                System.out.println(e.toString());
            }
        }
        System.out.println(exit_msg);
    }
}
