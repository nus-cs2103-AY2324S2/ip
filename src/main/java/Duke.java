import java.io.*;
import java.util.LinkedList;

public class Duke {
    private static final PrintWriter pw = new PrintWriter(System.out);
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final String SEPERATOR = "_______________________________________\n";
    private static final String INDENTATION = "    ";
    private static LinkedList<String> list = new LinkedList<String>();

    public static void main(String[] args) throws IOException{
        String line;

        greet();

        while (!(line = br.readLine()).equals("bye")){
            if (line.equals("list")){
                list();
            } else {
                add(line);
            }
        }

        exit();
        pw.close();
        br.close();
    }

    private static void greet(){
        pw.print(INDENTATION + SEPERATOR
                + INDENTATION + "Hello, Notnow here!\n"
                + INDENTATION + "What can I do for you?\n"
                + INDENTATION + SEPERATOR);
        pw.flush();
    }

    private static void exit(){
        pw.print(INDENTATION + SEPERATOR
                + INDENTATION + "Bye. Hope to see u again soon!\n"
                + INDENTATION + SEPERATOR);
        pw.flush();
    }

    private static void echo(String input) {
        pw.print(INDENTATION + SEPERATOR
                + INDENTATION + input + "\n"
                + INDENTATION + SEPERATOR);
        pw.flush();
    }

    private static void add(String input){
        echo("added: " + input);
        list.add(input);
    }

    private static void list(){
        pw.print(INDENTATION + SEPERATOR);
        for (int i = 0; i < list.size(); i += 1) {
            pw.print(INDENTATION + String.format("%d. %s\n", i + 1, list.get(i)));
        }
        pw.print(INDENTATION + SEPERATOR);
        pw.flush();
    }

}