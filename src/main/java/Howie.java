import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

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

    public static void main(String[] args) throws IOException {
        intro();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        while (true) {
            String s = br.readLine();
            if (!s.equals("bye")) {
                printVLine();
                System.out.println(s);
                printVLine();
            } else {
                break;
            }
        }
        printVLine();
        System.out.println("Bye! I'll see you when I see you!");
        printVLine();
    }
}
