import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) throws IOException {

        //Initialiser for the programss
        String bar = "____________________________________________________________";
        String indent = "   ";
        String first = " Hello! I'm Pluiexo";
        String second = " What can I do for you?";
        String third = " Bye. Hope to see you again soon!";
        String[] greet = new String[]{bar, first, second, bar};
        String[] bye = new String[]{bar, third, bar};

        //For data storage
        ArrayList<String> items = new ArrayList<>();

        //For Managing input
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        String next;

        //Greet first
        for (String l : greet) {
            System.out.println(indent + l);
        }
        while (!(next = input.readLine()).equals("bye")) {
            ArrayList<String> output = new ArrayList<>();
            output.add(bar);
            if (next.equals("list")) {
                int i = 1;
                for (String item : items) {
                    output.add(" " + i + ". " + item);
                    i++;
                }
            } else {
                output.add(" added: " + next);
                items.add(next);
            }
            output.add(bar);

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
