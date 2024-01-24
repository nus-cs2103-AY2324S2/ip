import java.util.*;

public class Duke {
    public static void main(String[] args) {
        String bot_name = "Calvin";
        String greeting_msg = "Hello! I'm " + bot_name + "\n"
                + "What can I do for you? \n\n";
        String exit_msg = "Bye. Hope to see you again soon!\n";
        System.out.println(greeting_msg);

        Scanner reader = new Scanner(System.in);

        List<Task> taskList = new ArrayList<Task>();

        while (true) {
            String user_input = reader.nextLine();
            String[] inputs = user_input.split(" ", 2);

            if (inputs[0].equals("bye")) {
                break;
            } else if (inputs[0].equals("list")) {
                for (int i = 0; i < taskList.size(); i++) {
                    int k = i + 1;
                    System.out.println(k + ". " + taskList.get(i));
                }
            } else if (inputs[0].equals("mark")) {
                int idx = Integer.parseInt(inputs[1]);
                Task tmp = taskList.get(idx-1);
                tmp.mark();
                System.out.println("Nice! I've marked this task as done:\n" + tmp);
            } else if (inputs[0].equals("unmark")) {
                int idx = Integer.parseInt(inputs[1]);
                Task tmp = taskList.get(idx-1);
                tmp.unmark();
                System.out.println("Nice! I've marked this task as done:\n" + tmp);
            } else {
                System.out.println("Got it. I've added this task:");
                Task tmp;
                if (inputs[0].equals("todo")) {
                    tmp = new Todo(inputs[1]);
                    taskList.add(tmp);
                    System.out.println(tmp);
                } else if (inputs[0].equals("deadline")) {
                    String[] str = inputs[1].split(" /by ");
                    tmp = new Deadline(str[0], str[1]);
                    taskList.add(tmp);
                    System.out.println(tmp);
                } else if (inputs[0].equals("event")) {
                    String[] str = inputs[1].split(" /from ");
                    String[] from_to = str[1].split(" /to ");
                    tmp = new Event(str[0], from_to[0], from_to[1]);
                    taskList.add(tmp);
                    System.out.println(tmp);
                }
                System.out.println("Now you have " + taskList.size() + " tasks in the list.");
            }
        }
        System.out.println(exit_msg);
    }
}
