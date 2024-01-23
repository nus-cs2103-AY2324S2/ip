import java.lang.reflect.Array;
import java.util.Scanner;
import java.util.ArrayList;
public class Duke {
    String name = "XVX-016 Aerial";
    String command = "";
    Scanner scanner1;
    ArrayList<Task> taskList = new ArrayList<>();
    public Duke() {
    }

    public void greeting() {
        horizontalLines();
        indent();
        System.out.println("HELLO, Nice to meet you. I am " + this.name + "! \uD83E\uDD16");
        indent();
        System.out.println("What are we doing today?");
        horizontalLines();
    }

    public void bye() {
        indent();
        System.out.println("See you next time! ♥( ˆ⌣ ˆԅ)");
        horizontalLines();
        this.scanner1.close();
    }

    public void horizontalLines() {
        System.out.println("\n    ____________________________________________________________");
    }

    public void echo() {
        horizontalLines();
        indent();
        System.out.println("╭( ๐ _๐)╮");
        indent();
        System.out.println("\uD83D\uDDE8️ You said THIS: ");
        indent();
        System.out.println(this.command);
        horizontalLines();
        input();
    }

    public void markTask(Integer index) {
        Task currentTask = taskList.get(index);
        indent();
        System.out.println("We have completed this task!");
        currentTask.mark();
        indent();
        System.out.println(currentTask.getStatus() + " " + currentTask.getTask());
        horizontalLines();
        input();
    }

    public void unmarkTask(Integer index) {
        Task currentTask = taskList.get(index);
        indent();
        System.out.println("Oops, time to redo the task!");
        currentTask.unmark();
        indent();
        System.out.println(currentTask.getStatus() + " " + currentTask.getTask());
        horizontalLines();
        input();
    }

    public void addTask()  {
        horizontalLines();
        indent();
        System.out.println("\uD83E\uDD14");
        indent();
        System.out.println("\uD83D\uDDE8️ You have added THIS: ");
        indent();
        System.out.println(this.command);
        horizontalLines();
        this.taskList.add(new Task(this.command));
        input();
    }

    public void listTask() {
        horizontalLines();
        indent();
        System.out.println("\uD83D\uDD6E");
        indent();
        System.out.println("\uD83D\uDDE8️ This are the tasks we currently have: ");
        indent();

        for (int i = 0; i < taskList.size(); i++) {
            Task currentTask = taskList.get(i);
            System.out.println((i+1) + ". " + currentTask.getStatus() + " " + currentTask.getTask());
            indent();
        }
        horizontalLines();
        input();
    }

    public void indent() {
        System.out.print("    ");
    }

    public void input() {
        String commandInput = scanner1.nextLine();
        String[] inputSplit = commandInput.split(" ");
        this.command = inputSplit[0];

        if (this.command.equals("bye")) {
            bye();
        } else if (this.command.equals("list")) {
            listTask();
        } else if (this.command.equals("mark")) {
            markTask(Integer.valueOf(inputSplit[1]) - 1);
        } else if (this.command.equals("unmark")) {
            unmarkTask(Integer.valueOf((inputSplit[1])) - 1);
        } else {
            addTask();
        }
    }

    public static void main(String[] args) {
        Duke Duke1 = new Duke();
        Duke1.greeting();
        Duke1.scanner1 = new Scanner(System.in);
        Duke1.input();
    }
}
