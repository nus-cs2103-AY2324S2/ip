import java.util.Scanner;

public class Ui {
    private Scanner inputScanner;
    private final String haroLogo = " ___  ___  ________  ________  ________\n"
            + "|\\  \\|\\  \\|\\   __  \\|\\   __  \\|\\   __  \\\n"
            + "\\ \\  \\\\\\  \\ \\  \\|\\  \\ \\  \\|\\  \\ \\  \\|\\  \\\n"
            + " \\ \\   __  \\ \\   __  \\ \\   _  _\\ \\  \\\\\\  \\\n"
            + "  \\ \\  \\ \\  \\ \\  \\ \\  \\ \\  \\\\  \\\\ \\  \\\\\\  \\\n"
            + "   \\ \\__\\ \\__\\ \\__\\ \\__\\ \\__\\\\ _\\\\ \\_______\\\n"
            + "    \\|__|\\|__|\\|__|\\|__|\\|__|\\|__|\\|_______|\n";
    private final String horizontalLine = "______________________________________________";
    private String openingMsg = "Heya! I'm Haro!\n" + "What can I do for you today?";
    private String closingMSg = "Bye. Hope to see you some time soon!";
    public Ui() {
        this.inputScanner = new Scanner(System.in);
    }

    public void greet() {
        System.out.println(
                "Greetings from\n" + haroLogo + "\n" +
                        openingMsg + "\n" +
                        horizontalLine
        );
    }

    public void bye() {
        System.out.println("List has been saved!");
        System.out.println(closingMSg + "\n" +
                horizontalLine);
    }

    public String readCommand() {
        return inputScanner.nextLine();
    }

    public void showError(String errorMsg) {
        System.out.println(errorMsg);
    }

    public void printList(TaskList tasks) {
        String taskString = tasks.tasksToString();
        if (taskString == "") {
            System.out.println("The task list is currently empty! Add tasks!\n");
        } else {
            System.out.println("Here are the tasks in your list:");
            System.out.println(taskString);
            System.out.println("");
        }
    }

    public void printMarkTask(Task task) {
        System.out.println("Nice! I've marked this task as done");
        System.out.println(task.printTask() + "\n");
    }

    public void printUnmarkTask(Task task) {
        System.out.println("Alright, I've marked this task as not done yet");
        System.out.println(task.printTask() + "\n");
    }

    public void printAddTask(Task task, int taskListSize) {
        System.out.println("Got it I've added this task:\n" +
                task.printTask() + "\n" +
                "You now have " + taskListSize + " tasks in the list\n");
    }

    public void printDeleteTask(Task task, int taskListSize) {
        System.out.println("Noted. I've removed this task");
        System.out.println(task.printTask() + "\n");
        System.out.println("You now have " + taskListSize + " tasks in the list\n");
    }
}
