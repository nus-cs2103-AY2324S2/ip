import javax.swing.*;
import javax.swing.plaf.synth.SynthSpinnerUI;
import java.io.*;
import java.util.ArrayList;

public class Duke {
    protected static ArrayList<Task> taskList = new ArrayList<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String logo = "$$\\      $$\\  $$$$$$\\  $$\\   $$\\       $$\\      $$\\ $$$$$$\\ $$\\   $$\\ \n"
                + "$$$\\    $$$ |$$  __$$\\ $$ |  $$ |      $$ | $\\  $$ |\\_$$  _|$$$\\  $$ | \n"
                + "$$$$\\  $$$$ |$$ /  $$ |\\$$\\ $$  |      $$ |$$$\\ $$ |  $$ |  $$$$\\ $$ | \n"
                + "$$\\$$\\$$ $$ |$$$$$$$$ | \\$$$$  /       $$ $$ $$\\$$ |  $$ |  $$ $$\\$$ | \n"
                + "$$ \\$$$  $$ |$$  __$$ | $$  $$<        $$$$  _$$$$ |  $$ |  $$ \\$$$$ | \n"
                + "$$ |\\$  /$$ |$$ |  $$ |$$  /\\$$\\       $$$  / \\$$$ |  $$ |  $$ |\\$$$ | \n"
                + "$$ | \\_/ $$ |$$ |  $$ |$$ /  $$ |      $$  /   \\$$ |$$$$$$\\ $$ | \\$$ | \n"
                + "\\__|     \\__|\\__|  \\__|\\__|  \\__|      \\__/     \\__|\\______|\\__|  \\__| \n";
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
            try {
                if (command.equals("list")) {
                    Task.listTask();
                } else if (command.equals("mark")) {
                    Task.setDone(Integer.parseInt(tokens[0].split(" ")[1]));
                } else if (command.equals("unmark")) {
                    Task.setNotDone(Integer.parseInt(tokens[0].split(" ")[1]));
                } else if (command.equals("delete")) {
                    Task.removeTask(description);
                } else {
                    addingTask(command, description);
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
            Duke.line();
            description = br.readLine();
            tokens = description.split("/");
            command = tokens[0].split(" ")[0];
        }
        Duke.bye();
    }

    public static void addingTask(String command, String description) throws Exception{

            if (command.equals("todo")) {
                Todo.addTask(description);
            } else if (command.equals("deadline")) {
                Deadline.addTask(description);
            } else if (command.equals("event")) {
                Event.addTask(description);
            } else {
                throw new IllegalArgumentException("Please enter a valid command.");
            }

    }

    public static void bye() {
        Duke.line();
        System.out.println("Bye. Hope to see you again soon!");
        Duke.line();
    }

    public static void line() {
        System.out.println("____________________________________________________________");
    }
}
