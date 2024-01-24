import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) throws IOException {
        String bar = "____________________________________________________________";
        String indent = "   ";
        String first = "Hello! I'm Pluiexo";
        String second = "What can I do for you?";
        String third = "Bye. Hope to see you again soon!";
        String[] greet = new String[]{bar, first, second, bar};
        String[] bye = new String[]{bar, third, bar};

        //For Managing input
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        String next;

        //Greet first
        for (String l : greet) {
            System.out.println(indent + l);
        }
        while (!(next = input.readLine()).equals("bye")) {
            String[] output = new String[]{bar, next, bar};
            for (String l : output) {
                System.out.println(indent + l);
            }
            System.out.println();
        }

        //Goodbye
        for (String l : bye) {
            System.out.println(indent + l);
        }

    }
}
