import java.io.PrintWriter;
public class Duke {
    private static final PrintWriter pw = new PrintWriter(System.out);
    public static void main(String[] args){
        greet();
        exit();
        pw.flush();
    }

    private static void greet() {
        pw.print("_______________________________________\n"
                + "Hello, Notnow here!\n"
                + "What can I do for you?\n"
                + "_______________________________________\n");
    }

    private static void exit() {
        pw.print("_______________________________________\n"
                + "Bye. Hope to see u again soon!\n"
                + "_______________________________________\n");
    }

}