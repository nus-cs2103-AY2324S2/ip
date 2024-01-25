import javax.swing.*;
import javax.swing.plaf.synth.SynthSpinnerUI;
import java.io.*;
import java.util.ArrayList;

public class Duke {
    protected static ArrayList<Task> taskList = new ArrayList<>();
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
        String description = br.readLine();
        String[] tokens = description.split("/");
        String command = tokens[0].split(" ")[0];
        while (!command.equals("bye")) {
            Duke.line();
            if (command.equals("list")) {
                Task.listTask();
            } else if (command.equals("mark")) {
                Task.setDone(Integer.parseInt(tokens[0].split(" ")[1]));
            } else if (command.equals("unmark")) {
                Task.setNotDone(Integer.parseInt(tokens[0].split(" ")[1]));
            } else {
                handleTask(command, description);
            }
            Duke.line();
            description = br.readLine();
            tokens = description.split("/");
            command = tokens[0].split(" ")[0];
        }
        Duke.line();
        System.out.println("Bye. Hope to see you again soon!");
        Duke.line();
    }

    public static void handleTask(String command, String description) {
        if (command.equals("todo")) {
            Todo.addTask(description);
        } else if (command.equals("deadline")) {
            Deadline.addTask(description);
        } else if (command.equals("event")) {
            Event.addTask(description);
        }
    }

    public static void line() {
        System.out.println("____________________________________________________________");
    }
}
