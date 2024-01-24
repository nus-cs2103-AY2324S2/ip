import java.io.*;
import java.util.*;
public class Duke {
    public static void main(String[] args) throws IOException {
        String logo = "   __     __   _  _    _  _           \n"
                    + " / __ \\  |  \\ | | \\ \\/ / | |            \n"
                    + "| |__| | |   \\| |  \\  /  | |     \n"
                    + "|  __  | | |\\   |  /  \\  | |\n"
                    + "|_|  |_| |_| \\__| /_/\\_\\ |_|\n";
        String name = "Anxi";
        String line = "--------------------------------------";

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        ArrayList<Task> tasks = new ArrayList<>();

        System.out.println(line);
        System.out.println(logo);
        System.out.println("Hello! I'm " + name + "\r\nWhat can I do for you? \r\n" + line);

        String action = br.readLine();
        StringTokenizer st = new StringTokenizer(action, " ");
        String temp = st.nextToken();

        while(!temp.equalsIgnoreCase("bye")) {
            System.out.println(line);

            switch (temp.toLowerCase()) {
                case "list":
                    System.out.println("Here are the tasks in your list:");

                    for (int i = 0; i < tasks.size(); ++i) {
                        Task t = tasks.get(i);
                        System.out.print(i + 1);
                        System.out.println("." + t.toString());
                    }
                    break;

                case "mark":
                    int num = Integer.parseInt(st.nextToken());
                    tasks.get(num - 1).updateIsDone(true);

                    System.out.println("Nice! I've marked this task as done:");
                    System.out.println(" " + tasks.get(num - 1).toString());
                    break;

                case "unmark":
                    int n = Integer.parseInt(st.nextToken());
                    tasks.get(n - 1).updateIsDone(false);

                    System.out.println("OK, I've marked this task as not done yet:");
                    System.out.println(" " + tasks.get(n - 1).toString());
                    break;

                case "todo":
                    tasks.add(new ToDo(action));
                    System.out.println("Got it. I've added this task:\r\n " + tasks.get(tasks.size() - 1));
                    System.out.println("Now you have " + tasks.size() + " tasks in the list");
                    break;

                default:
                    tasks.add(new Task(action));
                    System.out.println("added: " + action);
                    break;
            }

            System.out.println(line);
            action = br.readLine();
            st = new StringTokenizer(action, " ");
            temp = st.nextToken();
        }

        System.out.println(line + "\r\nBye. Hope to see you again soon!\r\n" + line);
        br.close();
    }
}
