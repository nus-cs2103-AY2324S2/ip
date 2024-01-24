import java.util.*;

public class Duke {
    public static void main(String[] args) {
        String bot_name = "Calvin";
        String greeting_msg = " Hello! I'm " + bot_name + "\n"
                + " What can I do for you? \n\n";
        String exit_msg = " Bye. Hope to see you again soon!\n";
        System.out.println(greeting_msg);

        Scanner reader = new Scanner(System.in);

        List<Task> taskList = new ArrayList<Task>();

        while (true) {
            String user_input = reader.nextLine();
            String[] inputs = user_input.split(" ");

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
                Task tmp = new Task(user_input);
                taskList.add(tmp);
                System.out.println("added :" + user_input);
            }
        }
        System.out.println(exit_msg);
    }
}
