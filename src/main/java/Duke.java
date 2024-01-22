import java.util.Scanner;
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
        for (int i = 0; i < this.taskCounter; ++i) {
            Task t = this.tasks[i];
            String desc = t.getDesc();
            String msg = String.valueOf(i+1) + ". " + desc;
            this.sendSystemMessage(msg);
        }
        this.sendSystemMessage(TextTemplate.LINE_BREAK);
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
            this.addTask(input);
        }
        this.bye();
    }

    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.run();
    }
}
