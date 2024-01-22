import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.BufferedReader;
import java.io.IOException;

public class Duke {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);

        String chatBotName = "Nicky";
        pw.println("____________________________________________________________");
        pw.println("Hello! I'm " + chatBotName + "\nWhat can I do for you?");
        pw.println("____________________________________________________________");
        pw.flush();

        String userResponse = br.readLine();
        while (!userResponse.equals("bye")) {
            pw.println("____________________________________________________________");
            pw.println(userResponse);
            pw.println("____________________________________________________________");
            pw.flush();

            userResponse = br.readLine();
        }

        br.close();
        pw.println("____________________________________________________________");
        pw.println("Bye. Hope to see you again soon!");
        pw.println("____________________________________________________________");
        pw.flush();
        pw.close();
    }
}
