import java.util.Scanner;
import java.util.ArrayList;
public class Duke {
    String name = "XVX-016 Aerial";
    String command = "";
    String secondaryInput = "";
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
        horizontalLines();
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
        horizontalLines();
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
        horizontalLines();
        Task currentTask = taskList.get(index);
        indent();
        System.out.println("Oops, task unmarked!");
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
        System.out.println("Processing your request!");

        Task newTask;

        if (this.command.equals("todo")) {
            newTask = new ToDo(secondaryInput, "T");
            this.taskList.add(newTask);
            indent();
            System.out.println(newTask.announcement());
            indent();
            indent();
            System.out.println(newTask.toString());
            horizontalLines();
        } else if (this.command.equals("deadline")) {
            String[] secondaryInputSplit = secondaryInput.split("/");
            newTask = new Deadline(secondaryInputSplit[0], "D", secondaryInputSplit[1]);
            this.taskList.add(newTask);
            indent();
            System.out.println(newTask.announcement());
            indent();
            indent();
            System.out.println(newTask.toString());
            horizontalLines();
        } else if (this.command.equals("event")) {
            String[] secondaryInputSplit = secondaryInput.split("/");
            newTask = new Event(secondaryInputSplit[0], "E", secondaryInputSplit[1],
                    secondaryInputSplit[2]);
            this.taskList.add(newTask);
            indent();
            System.out.println(newTask.announcement());
            indent();
            indent();
            System.out.println(newTask.toString());
            horizontalLines();
        } else {
            indent();
            System.out.println("Invalid Task");
            horizontalLines();
        }
        input();
    }

    public void listTask() {
        horizontalLines();
        indent();
        System.out.println("\uD83D\uDD6E");
        indent();
        System.out.println("\uD83D\uDDE8️ These are the tasks we currently have: ");
        indent();

        for (int i = 0; i < taskList.size(); i++) {
            Task currentTask = taskList.get(i);
            System.out.println((i+1) + ". " + currentTask.toString());
            indent();
        }

        System.out.println("We have " + (taskList.size()) + " tasks.");
        horizontalLines();
        input();
    }

    public void indent() {
        System.out.print("    ");
    }

    public void input() {
        String commandInput = scanner1.nextLine();
        String[] inputSplit = commandInput.split(" ", 2);
        this.command = inputSplit[0];

        if (this.command.equals("bye")) {
            bye();
        } else if (this.command.equals("list")) {
            listTask();
        } else if (this.command.equals("mark")) {
            markTask(Integer.valueOf(inputSplit[1]) - 1);
        } else if (this.command.equals("unmark")) {
            unmarkTask(Integer.valueOf((inputSplit[1])) - 1);
        } else if ((this.command.equals("todo")) || (this.command.equals("deadline"))
                || (this.command.equals("event"))) {
            this.secondaryInput = inputSplit[1];
            addTask();
        } else {
            System.out.println("\tInvalid Command. Please try again.");
            horizontalLines();
            input();
        }
    }

    public static void main(String[] args) {
        Duke Duke1 = new Duke();
        Duke1.greeting();
        Duke1.scanner1 = new Scanner(System.in);
        Duke1.input();
    }
}
