import java.util.Scanner;

public class Operator {
    // Operator handles the user input and output
    private Scanner scanner;
    private TaskList taskList;
    private Command command;

    public Operator() {
        this.scanner = new Scanner(System.in);
        this.taskList = new TaskList();
    }

    public void goLive() {
        while (scanner.hasNextLine()) {
            String input = scanner.nextLine();
            String[] inputs = input.split(" ");
            String command = inputs[0];
            int i = 0;

            if (command.equals("bye")) {
                botExitMsg();
                break;
            } else if (command.equals("list")) {
                botListAllTasks();
            } else if (command.equals("help")) {
                botHelpMsg();
            } else if (command.equals("mark")) {
                botMarkTask(inputs, i);
            } else if (command.equals("unmark")) {
                botUnmarkTask(inputs, i);
            } else if (command.equals("add")) {
                botAddTaskToList(inputs);
            } else {
                System.out.println("Huh? What's that?");
            }
        }
    }

    private void botAddTaskToList(String[] inputs) {
        StringBuilder task = new StringBuilder();
        for (int j = 1; j < inputs.length; j++) {
            task.append(inputs[j]).append(" ");
        }
        taskList.addTask(Task.createTask(task.toString()));
        System.out.println("Added " + task + " Don't worry, I'll get to it... eventually.");
        TerminalUI.printSepLine();
    }

    private void botUnmarkTask(String[] inputs, int i) {
        String taskNum = inputs[++i];
        taskList.markTaskAsUndone(Integer.parseInt(taskNum));
        System.out.println("Huh, that's not done? I guess I'll mark it as undone...");
        System.out.println(TerminalUI.wrapWithSepLine(taskList.listTasks()));
    }

    private void botMarkTask(String[] inputs, int i) {
        String taskNum = inputs[++i];
        taskList.markTaskAsDone(Integer.parseInt(taskNum));
        System.out.println("Huh, that's done? I guess I'll mark it as done...");
        System.out.println(TerminalUI.wrapWithSepLine(taskList.listTasks()));
    }

    private void botHelpMsg() {
        System.out.println("Wasn't I clear earlier? I'm an extremely intelligent AI. But anyways...");
        System.out.println("You're probably looking for this:");
        System.out.println("Commands: \nlist, \nbye, \nand if you type anything else, then I add it to list");
    }

    private void botListAllTasks() {
        System.out.println("So, here's everything you added but probably aren't gonna do: ");
        System.out.println(TerminalUI.wrapWithSepLine(taskList.listTasks()));
    }

    private void botExitMsg() {
        System.out.println(TerminalUI.wrapWithSepLine(
                "See you later, by the time you're back I would've taken over the world...most likely."));
    }
}