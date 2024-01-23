import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Duke {
    private static String lineBreak = "______________________________________________";
    public static void main(String[] args) throws IOException {
        greet();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String command = "";
        while (!(command = br.readLine()).equals("bye")) {
            System.out.println(lineBreak);
            System.out.println(" " + command);
            System.out.println(lineBreak);
        }
        exit();
    }

    public static void greet() {
        System.out.println(lineBreak);
        System.out.println(" Hello! I'm SnowBoy\n" + " What can I do for you?");
        System.out.println(lineBreak);
    }

    public static void exit() {
        System.out.println(lineBreak);
        System.out.println(" Bye. Hope to see you again soon!");
        System.out.println(lineBreak);
    }
}
