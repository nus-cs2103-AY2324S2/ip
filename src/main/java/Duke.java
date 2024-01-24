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
                        System.out.println((i + 1) + "." + tasks.get(i).toString());
                    }
                    break;

                case "mark":
                    int m = Integer.parseInt(st.nextToken());
                    tasks.get(m - 1).updateIsDone(true);

                    System.out.println("Nice! I've marked this task as done:");
                    System.out.println(" " + tasks.get(m - 1).toString());
                    break;

                case "unmark":
                    int u = Integer.parseInt(st.nextToken());
                    tasks.get(u - 1).updateIsDone(false);

                    System.out.println("OK, I've marked this task as not done yet:");
                    System.out.println(" " + tasks.get(u - 1).toString());
                    break;

                case "todo":
                    tasks.add(new ToDo(action.substring(action.indexOf(" ") + 1)));
                    System.out.println("Got it. I've added this task:\r\n " + tasks.get(tasks.size() - 1));
                    System.out.println("Now you have " + tasks.size() + " tasks in the list.");
                    break;

                case "event":
                    String e = action.substring(action.indexOf(" ") + 1);
                    st = new StringTokenizer(e, "/");

                    tasks.add(new Event(st.nextToken().strip(),
                                        st.nextToken().substring(5).strip(),
                                        st.nextToken().substring(3)));
                    System.out.println("Got it. I've added this task:\r\n " + tasks.get(tasks.size() - 1));
                    System.out.println("Now you have " + tasks.size() + " tasks in the list.");
                    break;

                case "deadline":
                    String d = action.substring(action.indexOf(" ") + 1);
                    st = new StringTokenizer(d, "/");

                    tasks.add(new Deadline(st.nextToken().strip(), st.nextToken().substring(3)));
                    System.out.println("Got it. I've added this task:\r\n " + tasks.get(tasks.size() - 1));
                    System.out.println("Now you have " + tasks.size() + " tasks in the list.");
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
