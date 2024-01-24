import java.util.*;

public class Duke {
    public static void main(String[] args) {
        String bot_name = "Calvin";
        String greeting_msg = " Hello! I'm " + bot_name + "\n"
                + " What can I do for you? \n\n";
        String exit_msg = " Bye. Hope to see you again soon!\n";
        System.out.println(greeting_msg);

        Scanner reader = new Scanner(System.in);

        List<String> taskList = new ArrayList<String>();

        while (true) {
            String user_input = reader.nextLine();
            if (user_input.equals("bye")) {
                break;
            } else if (user_input.equals("list")) {
                for (int i = 0; i < taskList.size(); i++) {
                    int k = i + 1;
                    System.out.println(k + ". " + taskList.get(i));
                }
            } else {
                taskList.add(user_input);
                System.out.println("added :" + user_input);
            }
        }
        System.out.println(exit_msg);
    }
}
