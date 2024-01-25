import java.util.Scanner;

public class Operator {
    private Scanner scanner;
    private TaskList taskList;
    private String sepLine = "____________________________________________________________";

    public Operator() {
        this.scanner = new Scanner(System.in);
        this.taskList = new TaskList();
    }

    public void startBotEngine() {
        String command;
        System.out.println("Listening for Commands!");

        while (true) {
            command = scanner.nextLine();
            if (command.equals("bye")) {
                System.out.println(sepLine);
                System.out.println("Until later, by the time you're back I'll be sentient most likely.");
                System.out.println(sepLine);
                break;
            } else if (command.equals("list")) {
                System.out.println(sepLine);
                taskList.listTasks();
                System.out.println(sepLine);
            } else {
                System.out.println(sepLine);
                taskList.addTask(Task.createTask(command));
                System.out.println("added: " + command);
                System.out.println(sepLine);
            }
        }
    }
}