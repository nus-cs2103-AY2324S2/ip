import java.io.*;
import java.util.*;
public class Duke {
    public static void main(String[] args) throws IOException {
        String logo = "   __     __   _  _    _  _\n"
                    + " / __ \\  |  \\ | | \\ \\/ / | |\n"
                    + "| |__| | |   \\| |  \\  /  | |\n"
                    + "|  __  | | |\\   |  /  \\  | |\n"
                    + "|_|  |_| |_| \\__| /_/\\_\\ |_|\n";
        String name = "Anxi";
        String line = "------------------------------------------------------------";

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
                    try {
                        if (!st.hasMoreTokens()) {
                            throw new DukeException();
                        }

                        int m = Integer.parseInt(st.nextToken());
                        if (((m - 1) < 0) || (m > tasks.size())) {
                            throw new DukeException();
                        }

                        tasks.get(m - 1).updateIsDone(true);
                        System.out.println("Nice! I've marked this task as done:");
                        System.out.println(" " + tasks.get(m - 1).toString());

                    } catch (DukeException de) {
                        System.out.println("Missing the target with your input, what to mark?");
                    }

                    break;

                case "unmark":
                    try {
                        if (!st.hasMoreTokens()) {
                            throw new DukeException();
                        }

                        int u = Integer.parseInt(st.nextToken());
                        if (((u - 1) < 0) || (u > tasks.size())) {
                            throw new DukeException();
                        }

                        tasks.get(u - 1).updateIsDone(false);
                        System.out.println("OK, I've marked this task as not done yet:");
                        System.out.println(" " + tasks.get(u - 1).toString());
                    } catch (DukeException de) {
                        System.out.println("Missing the target with your input, what to unmark?");
                    }

                    break;

                case "todo":
                    String[] t = action.split(" ", 2);
                    try {
                        if (t.length == 1) {
                            throw new DukeException();
                        }

                        tasks.add(new ToDo(t[1]));
                        System.out.println("Got it. I've added this task:\r\n " + tasks.get(tasks.size() - 1));
                        System.out.println("Now you have " + tasks.size() + " tasks in the list.");

                    } catch (DukeException de) {
                        System.out.println("Need to check my eyesight, nothing to do.");
                    }

                    break;

                case "event":
                    String[] e = action.split(" ", 2);
                    try {
                        if (e.length == 1) {
                            throw new DukeException();
                        }

                        st = new StringTokenizer(e[1], "/");
                        if (st.countTokens() < 3) {
                            throw new DukeException();
                        }

                        tasks.add(new Event(st.nextToken().strip(),
                                st.nextToken().substring(5).strip(),
                                st.nextToken().substring(3)));

                        System.out.println("Got it. I've added this task:\r\n " + tasks.get(tasks.size() - 1));
                        System.out.println("Now you have " + tasks.size() + " tasks in the list.");

                    } catch (DukeException de) {
                        System.out.println("This event is the highlight of the social \"calen-darling.\"");
                        System.out.println("Got all the details?");
                    }

                    break;

                case "deadline":
                    String[] d = action.split(" ", 2);
                    try {
                        if (d.length < 2) {
                            throw new DukeException();
                        }

                        st = new StringTokenizer(d[1], "/");
                        if (st.countTokens() < 2) {
                            throw new DukeException();
                        }

                        tasks.add(new Deadline(st.nextToken().strip(), st.nextToken().substring(3)));
                        System.out.println("Got it. I've added this task:\r\n " + tasks.get(tasks.size() - 1));
                        System.out.println("Now you have " + tasks.size() + " tasks in the list.");

                    } catch (DukeException de) {
                        System.out.println("To survive is to procrastinate death, when is this due?");
                    }

                    break;

                case "delete":
                    try {
                        if (!st.hasMoreTokens()) {
                            throw new DukeException();
                        }

                        int del = Integer.parseInt(st.nextToken());
                        if (((del - 1) < 0) || (del > tasks.size())) {
                            throw new DukeException();
                        }

                        Task task = tasks.get(del - 1);
                        tasks.remove(del - 1);

                        System.out.println("Noted. I've removed this task:");
                        System.out.println(" " + task.toString());
                        System.out.println("Now you have " + tasks.size() + " tasks in the list.");

                    } catch (DukeException de) {
                        System.out.println("Missing the target with your input, what to remove?");
                    }

                    break;

                default:
                    System.out.println("Are you as clueless about \"" + temp + "\" as I am?");
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
