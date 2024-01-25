import java.util.Scanner;

public class Operator {
    private Scanner scanner;
    private TaskList taskList;

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
                System.out.println(TerminalUI.wrapWithSepLine("Cya, by the time you're back I'll be sentient most likely."));
                break;
            } else if (command.equals("list")) {
                System.out.println(TerminalUI.wrapWithSepLine(taskList.listTasks()));
            } else {
                TerminalUI.printSepLine();
                taskList.addTask(Task.createTask(command));
                System.out.println("added: " + command);
                TerminalUI.printSepLine();
            }
        }
    }
}