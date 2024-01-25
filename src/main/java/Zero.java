import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Zero {
    private static final String name = "Zero";
    private static final String divider = "____________________________________________________________\n";
    private static final String greeting = "Hello! I'm " + name + ".\nWhat can I do for you?";
    private static final String goodbye = "Bye. Hope to see you again soon!";
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));
        
        pw.println(divider + greeting + '\n' + divider);
        pw.flush();

        ArrayList<String> inputs = new ArrayList<>();
        String cmd;
        do {
            cmd = br.readLine();
            switch (cmd) {
                case "bye":
                    pw.println(divider + goodbye + '\n' + divider);
                    break;
                case "list":
                    pw.print(divider);
                    for (int i = 0; i < inputs.size(); i++) {
                        pw.println((i+1) + ". " + inputs.get(i));
                    }
                    pw.println(divider);
                    break;
                default:
                    inputs.add(cmd);
                    pw.println(divider + "Added: " + cmd + '\n' + divider);
                    break;
            }
            pw.flush();
        } while (!cmd.equals("bye"));

        br.close();
        pw.close();
    }
}
