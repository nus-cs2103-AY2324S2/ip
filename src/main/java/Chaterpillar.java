import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Chaterpillar {
    public static boolean exited = false;
    public static void greet() {
        print_horizontal_line();
        System.out.println("Hello! I'm Chaterpillar");
        System.out.println("What can I do for you?");
        print_horizontal_line();
    }
    public static void exit() {
        System.out.println("Bye. Hope to see you again soon!");
    }
    public static void echo(BufferedReader reader) throws IOException {
        String input = reader.readLine();
        print_horizontal_line();
        if (!input.equals("bye")) {
            System.out.println(input);
        } else {
            exited = true;
            exit();
        }
        print_horizontal_line();
    }
    public static void print_horizontal_line() {
        String line = "-".repeat(50);
        System.out.println(line);
    }
    public static void main(String[] args) {
        greet();
        BufferedReader reader = new BufferedReader(
                new InputStreamReader((System.in)));

        while (!exited) {
            try {
                echo(reader);
            } catch (IOException e) {
                System.out.println("IOException occurred.");
            }
        }
    }
}
