import javax.swing.*;
import javax.swing.plaf.synth.SynthSpinnerUI;
import java.io.*;

public class Duke {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        Duke.greet();
        Duke.echo(br);
    }

    public static void greet() {
        Duke.line();
        System.out.println("Hello! I'm Anita MaxWynn");
        System.out.println("What can I do for you?");
        Duke.line();
    }

    public static void echo(BufferedReader br) throws IOException {
        String[] tokens = br.readLine().split(" ");
        while (!tokens[0].equals("bye")) {
            Duke.line();
            if (tokens[0].equals("list")) {
                Task.listTask();
            } else if (tokens[0].equals("mark")) {
                Task.setDone(Integer.parseInt(tokens[1]));
            } else if (tokens[0].equals("unmark")) {
                Task.setNotDone(Integer.parseInt(tokens[1]));
            } else {
                Task.addTask(tokens[0]);
            }
            Duke.line();
            tokens = br.readLine().split(" ");
        }
        Duke.line();
        System.out.println("Bye. Hope to see you again soon!");
        Duke.line();
    }

    public static void line() {
        System.out.println("____________________________________________________________");
    }
}
