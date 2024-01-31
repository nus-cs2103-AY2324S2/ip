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
}
