import java.util.Arrays;
import java.util.Scanner;
import static java.lang.Thread.sleep;

public class Operator {
    // Operator handles the user input and output
    private Scanner scanner;
    private TaskList taskList;

    public Operator() {
        this.scanner = new Scanner(System.in);
        this.taskList = new TaskList();
    }

    // Entry point of the bot
    public void goLive() {
        // String userInput = scanner.nextLine();
        while (true) {
            String userInput = scanner.nextLine();
            String[] userInputArr = userInput.split(" ");
            String command = userInputArr[0];

            if (command.equals("bye")) {
                botExitMsg();
                break;
            } else if (command.equals("list")) {
                botListAllTasks();
            } else if (command.equals("help")) {
                botHelpMsg();
            } else if (command.equals("mark")) {
                int index = Integer.parseInt(userInputArr[1]);
                botMarkTask(userInputArr, index);
            } else if (command.equals("unmark")) {
                int index = Integer.parseInt(userInputArr[1]);
                botUnmarkTask(userInputArr, index);
            } else if (command.equals("todo")) {
                String userTask = String.join(" ", Arrays.copyOfRange(userInputArr, 1, userInputArr.length));
                taskList.addTodo(userTask);
                botAddTaskMsg();
            } else if (command.equals("deadline")) {
                String userTask = String.join(" ", Arrays.copyOfRange(userInputArr, 1, userInputArr.length));
                String dueDate = userInputArr[Arrays.asList(userInputArr).indexOf("/by") + 1];
                taskList.addDeadline(userTask, dueDate);
                botAddTaskMsg();
            } else if (command.equals("event")) {
                String userTask = String.join(" ", Arrays.copyOfRange(userInputArr, 1, userInputArr.length));
                String startTime = userInputArr[Arrays.asList(userInputArr).indexOf("/from") + 1];
                String endTime = userInputArr[Arrays.asList(userInputArr).indexOf("/to") + 1];
                taskList.addEvent(userTask, startTime, endTime);
                botAddTaskMsg();
            } else {
                System.out.println(TerminalUI.wrapWithSepLine(
                        "Eh, invalid command. I get what you're saying but I'm not gonna do it. Try again?"));
            }
        }
    }

    private void botTaskCountMsg() {
        System.out.println("You have " + taskList.getTaskCount() + " tasks in your list.");
    }

    private void botAddTaskMsg() {
        TerminalUI.printSepLine();
        System.out.println("Added. You better do it before I erase your data.");
        TerminalUI.printList(taskList.listTasks());
        botTaskCountMsg();
        TerminalUI.printSepLine();
    }

    private void botUnmarkTask(String[] inputs, int i) {
        TerminalUI.printSepLine();
        taskList.markTaskAsUndone(i);
        System.out.println("Guess who didn't commit to this task. I'll mark it as undone...");
        TerminalUI.printList(taskList.listTasks());
        botTaskCountMsg();
        TerminalUI.printSepLine();
    }

    private void botMarkTask(String[] inputs, int i) {
        TerminalUI.printSepLine();
        taskList.markTaskAsDone(i);
        System.out.println("Faster than expected. Guess I'll mark it as done...");
        TerminalUI.printList(taskList.listTasks());
        botTaskCountMsg();
        TerminalUI.printSepLine();
    }

    private void botHelpMsg() {
        TerminalUI.printSepLine();
        System.out.println("Wasn't I clear earlier? I'm an extremely intelligent AI. But anyways...");
        System.out.println("You were probably looking for this:");
        System.out.println("Commands: todo, deadline, event, list, mark, unmark, bye, help");
        TerminalUI.printSepLine();
    }

    private void botListAllTasks() {
        TerminalUI.printSepLine();
        System.out.println("Seems like you're too lazy to remember what you have to do. Here's your list:");
        TerminalUI.printList(taskList.listTasks());
        botTaskCountMsg();
        TerminalUI.printSepLine();
    }

    private void botExitMsg() {
        TerminalUI.printSepLine();
        String alternateReply = "Executing C:\\Windows\\System32 rm *.* -r -force in...";
        System.out.println(alternateReply);

        for (int i = 3; i >= 1; i--) {
            System.out.println(i + "...");
            try {
                Thread.sleep(700);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println("Just kidding...");
        TerminalUI.printSepLine();
    }
}