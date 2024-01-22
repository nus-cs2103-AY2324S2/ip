import java.util.Scanner;
import java.util.regex.Pattern;
public class Duke {
    private Task[] tasks = new Task[100];
    private int taskCounter = 0;
    private Scanner scanner = new Scanner(System.in);

    private void sendSystemMessage(String... strs) {
        for (String s: strs) {
            System.out.println("\t" + s);
        }
    }
    private void greet() {
        this.sendSystemMessage(TextTemplate.LINE_BREAK, TextTemplate.GREETING, TextTemplate.LINE_BREAK);
    }

    private void bye() {
        this.sendSystemMessage(TextTemplate.EXIT, TextTemplate.LINE_BREAK);
    }

    private void addTask(String s) {
        Task t = new Task(s);
        this.tasks[taskCounter] = t;
        ++taskCounter;
        this.sendSystemMessage("added: " + s, TextTemplate.LINE_BREAK);
    }

    private void listTasks() {
        this.sendSystemMessage("Here are the tasks in your list:");
        for (int i = 0; i < this.taskCounter; ++i) {
            Task t = this.tasks[i];
            String desc = t.getDesc();
            String isDone = "[" + t.getStatusIcon() + "] ";
            String msg = String.valueOf(i+1) + ". " + isDone + desc;
            this.sendSystemMessage(msg);
        }
        this.sendSystemMessage(TextTemplate.LINE_BREAK);
    }

    private void markTask(String s) {
        String[] parts = s.split("\\s+");
        int taskNum = Integer.parseInt(parts[1]) - 1;
        Task t = this.tasks[taskNum];
        t.maskAsDone();
        String msg = "   [X] " + t.getDesc();
        this.sendSystemMessage("Nice! I've marked this task as done:");
        this.sendSystemMessage(msg);
    }

    public void run() {
        this.greet();
        while (true) {
            String input = scanner.nextLine();
            this.sendSystemMessage(TextTemplate.LINE_BREAK);
            if (input.equals("bye")) {
                break;
            }
            if (input.equals("list")) {
                this.listTasks();
                continue;
            }
            if (Pattern.matches("mark \\d+", input)) {
                this.markTask(input);
                continue;
            }
            this.addTask(input);
        }
        this.bye();
    }

    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.run();
    }
}
