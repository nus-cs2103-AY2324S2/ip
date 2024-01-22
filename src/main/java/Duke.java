import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        greet();
        echo();
        bye();
    }

    public static void indent() {
        System.out.print("    ");
    }

    public static void drawLine() {
        indent();
        System.out.println("____________________________________________________________");
    }

    public static void output(String ... sentences) {
        drawLine();
        for (String sentence : sentences) {
            indent();
            System.out.println(sentence);
        }
        drawLine();
    }

    public static void echo() {
        Scanner sc = new Scanner(System.in);
        String sentence = sc.nextLine();
        while (!sentence.equals("bye")) {
            output(sentence);
            sentence = sc.nextLine();
        }
        sc.close();
    }

    public static void greet() {
        String name = "Cortana";
        String sentence1 = "Hello! I'm " + name;
        String sentence2 = "What can I do for you?";
        output(sentence1, sentence2);
    }

    public static void bye() {
        String sentence = "Bye. Hope to see you again soon!";
        output(sentence);
    }

}
