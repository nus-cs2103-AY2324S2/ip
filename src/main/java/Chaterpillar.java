import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Chaterpillar {
    public static boolean exited = false;
    public static ArrayList<String> listofitems = new ArrayList<String>();
    public static void greet() {
        print_horizontal_line();
        System.out.println("Hello! I'm Chaterpillar");
        System.out.println("What can I do for you?");
        print_horizontal_line();
    }
    public static void exit() {
        System.out.println("Bye. Hope to see you again soon!");
    }
    public static void echo(String s) {
        System.out.println(s);
    }
    public static void parse_input(BufferedReader reader) throws IOException {
        String input = reader.readLine();
        print_horizontal_line();

        if (input.equals("list")) {
            // if asked to list the items stored
            int i = 1;
            for (String each_item : listofitems) {
                echo(i++ + ". " + each_item);
            }

        } else if (!input.equals("bye")) {
            // if not asked to exit the chatbot
            // add to array list and
            // prints what is added
            listofitems.add(input);
            echo("added: " + input);
        } else {
            // exits the program
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
                parse_input(reader);
            } catch (IOException e) {
                System.out.println("IOException occurred.");
            }
        }
    }
}
