import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayList;

public class Zero {
    private static final String name = "Zero";
    private static final String divider = "____________________________________________________________\n";
    private static final String unmarked = "";
    private static final String goodbye = "";
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
                default:
                    String item = String.join(" ", cmd);
                    tasks.add(new Task(item));
                    pw.println(divider + "Added: " + item + '\n' + divider);
                    break;
            }
            pw.flush();
        } while (!cmd[0].equals("bye"));

        br.close();
        pw.close();
    }
}
