import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

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

        String input;
        do {
            input = br.readLine();
            switch (input) {
                case "bye":
                    pw.println(divider + goodbye + '\n' + divider);
                    break;
                default:
                    pw.println(divider + input + '\n' + divider);
                    break;
            }
            pw.flush();
        } while (!input.equals("bye"));

        br.close();
        pw.close();
    }
}
