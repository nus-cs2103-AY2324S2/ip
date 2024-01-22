import java.io.PrintWriter;

public class Duke {
    public static void main(String[] args) {
        PrintWriter pw = new PrintWriter(System.out);

        String chatBotName = "Nicky";
        pw.println("____________________________________________________________");
        pw.println("Hello! I'm " + chatBotName + "\nWhat can I do for you?");
        pw.println("____________________________________________________________");
        pw.println("Bye. Hope to see you again soon!");
        pw.println("____________________________________________________________");
        pw.flush();
        pw.close();
    }
}
