package me.ruibin.leto.ui;

import java.io.PrintWriter;
import java.io.StringWriter;

/** Wraps output with custom formatting before sending to stdout using PrintWriter. */
public class Ui {
    private static final StringWriter sw = new StringWriter();
    private static final PrintWriter pw = new PrintWriter(sw);

    /**
     * Prints message in multiple line. Auto indenting on linebreak.
     *
     * @param message Message to print.
     * @return String printed.
     */
    public static String letoSpeak(String message) {
        pw.write("  << Duke Leto >>\n  > " + message.replaceAll("\n", "\n  > "));
        pw.println();
        pw.print("========================================\n");
        pw.flush();
        String toReturn = sw.toString();
        sw.getBuffer().setLength(0);
        return toReturn;
    }

    /**
     * Prints message ine a single line.
     *
     * @param message To print.
     * @return String printed.
     */
    public static String shortSay(String message) {
        pw.write("  << Duke Leto >> : " + message + "\n");
        pw.flush();
        String toReturn = sw.toString();
        sw.getBuffer().setLength(0);
        return toReturn;
    }

    /**
     * Prints the logo.
     *
     * @return String printed.
     */
    public static String letoLogo() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|   Leto\n";
        pw.write("Good day from\n" + logo);
        pw.print("========================================\n");
        pw.flush();
        String toReturn = sw.toString();
        sw.getBuffer().setLength(0);
        return toReturn;
    }

    /**
     * Print the standard help message.
     *
     * @return String printed.
     * */
    public static String letoHelp() {
        return letoSpeak("Supported commands:\n"
            + "list     -  list all tasks\n"
            + "mark     -  mark task as done\n"
            + "              usage: mark <index>\n"
            + "unmark   -  unmark task as done, :(\n"
            + "              usage: unmark <index>\n"
            + "todo     -  create a task of type todo \n"
            + "              usage: todo <details>\n"
            + "deadline -  create a task of type deadline\n"
            + "              usage: deadline <description> /by <date>,\n"
            + "              <date> should be in the format YYYY-MM-DD. \n"
            + "event    -  create a task of type event    \n"
            + "               usage: event <details> /from <start_time YYYY-MM-DD> /to <end_time YYYY-MM-DD>\n"
            + "save     -  save tasks to persistent storage\n"
            + "find     -  find a task by keyword, searches within their entire CSV entry\n"
            + "                usage: event <keyword>\n"
            + "                case sensitive\n"
            + "snooze   -  snooze as task deadline by specified days\n"
            + "                usage: snooze <task number> /by <number> d[ays]\n"
            + "bye      -  exit");
    }
}
