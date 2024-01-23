import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Duke {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line = "____________________________________________________________";
        ArrayList<Task> taskList = new ArrayList<>();

        System.out.printf(
                "    %s\n     Hello! I'm Buto\n     What can I do for you?\n    %s\n",
                line, line
        );

        String text = br.readLine();
        String[] splitText = text.split(" ");
        String command = splitText[0];

        while (!command.equals("bye")) {
            if (command.equals("list")) {
                System.out.printf("    %s\n     Here are the tasks in your list:\n", line);
                for (int i = 1; i <= taskList.size(); i++) {
                    System.out.printf("     %d.%s\n", i, taskList.get(i - 1).toString());
                }
                System.out.printf("    %s\n", line);
            } else if (command.equals("mark")) {
                int taskIndex = Integer.parseInt(splitText[1]) - 1;
                taskList.get(taskIndex).mark();
                System.out.printf(
                        "    %s\n     Nice! I've marked this task as done:\n       %s\n    %s\n",
                        line, taskList.get(taskIndex).toString(), line);
            } else if (command.equals("unmark")){
                int taskIndex = Integer.parseInt(splitText[1]) - 1;
                taskList.get(taskIndex).unmark();
                System.out.printf(
                        "    %s\n     OK, I've marked this task as not done yet:\n       %s\n    %s\n",
                        line, taskList.get(taskIndex).toString(), line);
            } else {
                taskList.add(new Task(command));
                System.out.printf("    %s\n     added: %s\n    %s\n", line, text, line
                );
            }
            text = br.readLine();
            splitText = text.split(" ");
            command = splitText[0];
        }

        br.close();

        System.out.printf(
                "    %s\n     Bye. Hope to see you again soon!\n    %s\n",
                line, line
        );


    }
}