import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Howie {
    public static void printVLine() {
        System.out.println("---------------------------");
    }

    public static void intro() {
        printVLine();
        System.out.println("Hello! I'm Howie");
        System.out.println("What can I do for you?");
        printVLine();
    }

    public static void exit() {
        printVLine();
        System.out.println("Bye! I'll see you when I see you!");
        printVLine();
    }

    public static void added(String item) {
        printVLine();
        System.out.println("Ok! Added: " + item);
        printVLine();
    }

    public static void main(String[] args) throws IOException {
        intro();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        List<String> todo = new ArrayList<>();
        while (true) {
            String s = br.readLine();
            switch (s) {
                case "list":
                    int count = 1;
                    printVLine();
                    for (String item : todo) {
                        System.out.println(count + ". " + item);
                        count++;
                    }
                    printVLine();
                    break;
                case "bye":
                    exit();
                    break;
                default:
                    todo.add(s);
                    added(s);
                    break;
            }
            if (s.equals("bye")) {
                System.exit(0);
            }
        }
    }
}
