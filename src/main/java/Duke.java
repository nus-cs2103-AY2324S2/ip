import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Duke {
    private static String lineBreak = "______________________________________________";
    private static ArrayList<String> lst = new ArrayList<>();
    public static void main(String[] args) throws IOException {
        greet();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String command = "";
        while (!(command = br.readLine()).equals("bye")) {
            if (command.equals("list")) {
                printLst();
            } else {
                addLst(command);
            }
        }
        exit();
    }

    public static void greet() {
        System.out.println(lineBreak);
        System.out.println(" Hello! I'm SnowBoy\n" + " What can I do for you?");
        System.out.println(lineBreak);
    }

    public static void addLst(String command) {
        lst.add(command);
        System.out.println(lineBreak);
        System.out.println(" added: " + command);
        System.out.println(lineBreak);
    }
    public static void printLst() {
        System.out.println(lineBreak);
        for (int i = 0; i < lst.size(); i++) {
            System.out.println(" " + (i + 1) + ". " + lst.get(i));
        }
        System.out.println(lineBreak);
    }

    public static void exit() {
        System.out.println(lineBreak);
        System.out.println(" Bye. Hope to see you again soon!");
        System.out.println(lineBreak);
    }
}
