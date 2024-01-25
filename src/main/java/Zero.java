import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;

public class Zero {
    private static final String name = "Zero";
    private static final String divider = "____________________________________________________________\n";

    //filters input commands to give: [0] cmd, [1] name, [2] from/by, [3] to
    private static String[] filterCommand(String[] s) {
        String[] result = new String[4];
        int idx = 1;
        result[0] = s[0];
        result[1] = s[1];
        for (int i = 2; i < s.length; i++) {
            if (s[i].startsWith("/")) {
                i++;
                result[++idx] = s[i];
            } else {
                result[idx] += " " +  s[i];
            }
        }
        return result;
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));
        
        pw.println(divider + "Hello! I'm " + name + ".\nWhat can I do for you?\n" + divider);
        pw.flush();

        ArrayList<Task> tasks = new ArrayList<>();
        String[] cmd;
        int idx;
        do {
            pw.print(">>>");
            pw.flush();
            cmd = br.readLine().split(" ");
            switch (cmd[0]) {
                case "bye":
                    pw.println(divider + "Bye. Hope to see you again soon!\n" + divider);
                    break;
                case "list":
                    pw.println(divider + "Here are the tasks in your list:");
                    for (int i = 0; i < tasks.size(); i++) {
                        pw.println((i+1) + "." + tasks.get(i));
                    }
                    pw.println(divider);
                    break;
                case "mark":
                    idx = Integer.parseInt(cmd[1]) - 1;
                    if (idx < 0 || idx >= tasks.size()) {
                        pw.println(divider + "Invalid task selected.\nTask number must be between 1 to " + tasks.size());
                        pw.println(divider);
                        break;
                    }
                    tasks.set(idx, tasks.get(idx).mark());
                    pw.println("Nice! I've marked this task as done:\n  " + tasks.get(idx) + '\n' + divider);
                    break;
                case "unmark":
                    idx = Integer.parseInt(cmd[1]) - 1;
                    if (idx < 0 || idx >= tasks.size()) {
                        pw.println(divider + "Invalid task selected.\nTask number must be between 1 to " + tasks.size());
                        pw.println(divider);
                        break;
                    }
                    tasks.set(idx, tasks.get(idx).unmark());
                    pw.println("OK, I've marked this task as not done yet:\n  " + tasks.get(idx) + '\n' + divider);
                    break;
                case "todo":
                case "deadline":
                case "event":
                    String[] fs = filterCommand(cmd);
                    Task t;
                    if (fs[0].equals("todo")) t = new ToDo(fs[1]);
                    else if (fs[0].equals("deadline")) t = new Deadline(fs[1], fs[2]);
                    else t = new Event(fs[1], fs[2], fs[3]);
                    tasks.add(t);
                    pw.println(divider + "Got it. I've added this task:\n  " + t);
                    pw.println("Now you have " + tasks.size() + " tasks in the list.\n" + divider);
                    break;
                default:
                    pw.println(divider + "Sorry, I am currently unable to process your request.\n" + divider);
                    break;
            }
            pw.flush();
        } while (!cmd[0].equals("bye"));

        br.close();
        pw.close();
    }
}
