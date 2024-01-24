import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class Duke {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out, true);

        String prompt = "Hello! I'm TFamilyBot\n"
                + "What can I do for you? \n"
                + "____________________________________________________________\n";   
        pw.println(prompt);

        while (true) {
            String io = br.readLine();

            pw.println("____________________________________________________________\n");

            if (io.equals("bye")) {
                pw.println("Bye. Hope to see you again soon!");
                pw.println("____________________________________________________________\n");
                break;
            }

            pw.println(io);
            pw.println("____________________________________________________________\n");
        }
    }
}
