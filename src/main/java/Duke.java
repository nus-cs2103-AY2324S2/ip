import javax.swing.*;
import javax.swing.plaf.synth.SynthSpinnerUI;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

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
        String command = br.readLine();
        while (!command.equals("bye")) {
            Duke.line();
            System.out.println(command);
            Duke.line();
            command = br.readLine();
        }
        Duke.line();
        System.out.println("Bye. Hope to see you again soon!");
        Duke.line();
    }

    public static void line() {
        System.out.println("____________________________________________________________");
    }
}
