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

        String[] userResponses = new String[100];
        int i = 0;

        while (true) {
            String currentResponse = br.readLine();
            pw.println("____________________________________________________________");

            if (currentResponse.equals("bye")) {
                break;
            } else if (currentResponse.equals("list")) {
                for (int j = 0; j < i; j++) {
                    pw.println((j + 1) + ". " + userResponses[j]);
                }
            } else {
                pw.println("added: " + currentResponse);
                userResponses[i] = currentResponse;
                i++;
            }

            pw.println("____________________________________________________________");
            pw.flush();
        }

        br.close();
        pw.println("Bye. Hope to see you again soon!");
        pw.println("____________________________________________________________");
        pw.flush();
        pw.close();
    }
}