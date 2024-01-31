import java.util.Scanner;

public class Ui {
    public void run(TaskList list, Storage storage) throws BotBotException {
        greet();
        Scanner scanner = new Scanner(System.in);
        scanner.useDelimiter("\n");

        while (scanner.hasNext()) {
            String nextTask = scanner.next();
            divider();
            // Logic sequence
            if (nextTask.startsWith("bye")) {
                exit();
                break;
            } else if (nextTask.startsWith("mark")) {
                System.out.println("Good job on completing the task:");
                System.out.println(Integer.parseInt(nextTask.split(" ", 2)[1]));
                list.mark(Integer.parseInt(nextTask.split(" ", 2)[1]));
            } else if (nextTask.startsWith("unmark")) {
                System.out.println("I have unmarked a task:");
                list.unmark(Integer.parseInt(nextTask.split(" ", 2)[1]));
            } else if (nextTask.startsWith("list")) {
                System.out.println("These are the tasks in your list:");
                list.printList();
            } else if (nextTask.startsWith("delete")) {
                list.deleteTask(Integer.parseInt(nextTask.split(" ", 2)[1]));
            } else {
                list.addTask(nextTask);
            }
            divider();
        }
        scanner.close();
    }
    // Print functionalities
    private void print(String string) {
        System.out.println(string);
    }
    private void divider() {
        print("########################################");
    }
    public void greet() {
        divider();
        print("I am BotBot!\nWhat can I do for you?");
        divider();
    }
    public void exit() {
        print("Goodbye! See you soon!");
        divider();
    }
}
