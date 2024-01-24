import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class Duke {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out, true);

        String[] myList = new String[100];
        int pointer = 0;

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

            if (io.equals("list")) {
                for (int i = 0; i < pointer; i++) {
                    int show = i+ 1;
                    pw.println(show + ". " + myList[i]);
                }

                pw.println("____________________________________________________________\n");
            } else {
            myList[pointer] = io;
            pointer++;
            pw.println("added: " + io);
            pw.println("____________________________________________________________\n");
            }
        }
    }
}
