package leto.ui;

import java.io.PrintWriter;

public class Ui {
    private static final PrintWriter pw = new PrintWriter(System.out);
    public static void letoSpeak(String message) {
        pw.write("  << Duke Leto >>\n  > " + message.replaceAll("\n", "\n  > "));
        pw.println();
        pw.print("========================================\n");
        pw.flush();
    }

    public static void shortSay(String message) {
        pw.write("  << Duke Leto>> : " + message + "\n");
        pw.flush();
    }

    public static void letoLogo() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|   Leto\n";
        pw.write("Good day from\n" + logo);
        pw.print("========================================\n");
        pw.flush();
    }

    public static void letoHelp() {
        letoSpeak("Supported commands:\n" +
                "list     -  list all tasks\n" +
                "mark     -  mark task as done\n" +
                "              usage: mark <index>\n" +
                "unmark   -  unmark task as done, :(\n" +
                "              usage: unmark <index>\n" +
                "todo     -  create a task of type todo \n" +
                "              usage: todo <details>\n" +
                "deadline -  create a task of type deadline\n" +
                "              usage: deadline <description> /by <date>,\n" +
                "              <date> should be in the format YYYY-MM-DD. \n" +
                "event    -  create a task of type event    \n" +
                "               usage: event <details> /from <start_time YYYY-MM-DD> /to <end_time YYYY-MM-DD>\n" +
                "save     -  save tasks to persistent storage" +
                "bye      -  exit");

    }
}
