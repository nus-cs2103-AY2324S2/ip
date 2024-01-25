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
        System.out.println("You can type your commands below...\n(Try typing \"help\")");

        while (true) {
            command = scanner.nextLine();
            if (command.equals("bye")) {
                System.out.println(TerminalUI.wrapWithSepLine("See you later, by the time you're back I would've taken over the world...most likely."));
                break;
            } else if (command.equals("list")) {
                System.out.println("So, here's everything you added but probably aren't gonna do: ");
                System.out.println(TerminalUI.wrapWithSepLine(taskList.listTasks()));
            } else if(command.equals("help")){
                System.out.println("Wasn't I clear earlier? I'm an extremely intelligent AI. But anyways...");
                System.out.println("You're probably looking for this:");
                System.out.println("Commands: \nlist, \nbye, \nand if you type anything else, then I add it to list");
            } else {
                TerminalUI.printSepLine();
                taskList.addTask(Task.createTask(command));
                System.out.println("Added " + command + " Don't worry, I'll get to it... eventually.");
                TerminalUI.printSepLine();
            }
        }
    }
}