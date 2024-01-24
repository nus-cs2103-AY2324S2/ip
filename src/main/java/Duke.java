import java.io.*;

public class Duke {
    private static final PrintWriter pw = new PrintWriter(System.out);
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final String SEPERATOR = "_______________________________________\n";
    public static void main(String[] args) throws IOException{
        String line;

        greet();

        while (!(line = br.readLine()).equals("bye")){
            echo(line);
        }

        exit();
        pw.close();
        br.close();
    }

    private static void greet(){
        pw.print(SEPERATOR
                + "Hello, Notnow here!\n"
                + "What can I do for you?\n"
                + SEPERATOR);
        pw.flush();
    }

    private static void exit(){
        pw.print(SEPERATOR
                + "Bye. Hope to see u again soon!\n"
                + SEPERATOR);
        pw.flush();
    }

    private static void echo(String input) {
        pw.print(SEPERATOR
                + input + "\n"
                + SEPERATOR);
        pw.flush();
    }

}