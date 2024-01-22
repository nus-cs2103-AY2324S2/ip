import java.util.Scanner;
import java.util.ArrayList;
public class KaiYap {

    ArrayList<Task> taskList;

    public KaiYap() {
        taskList = new ArrayList<>();
    }
    public void sayHello() {
        System.out.println("\t____________________________________________________________\n" +
                "\tHello! I'm KaiYap.\n" +
                "\tWhat can I do for you?\n" +
                "\t____________________________________________________________\n"
        );
    }

    public void sayBye() {
        System.out.println("\t____________________________________________________________\n" +
                "\tBye. Hope to see you again soon!\n" +
                "\t____________________________________________________________");
    }

    public void startChat() {
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        input = input.strip();
        while (!input.equals("bye")) {
            if (input.equals("list")) {
                this.listInputs();
            } else if ((input.length() > 5 && input.startsWith("mark ")) || input.equals("mark")) {
                this.markAsDone(input.substring(4).strip());
            } else if (input.length() > 7 && input.startsWith("unmark ") || input.equals("unmark")) {
                this.markAsUndone(input.substring(6).strip());
            } else {
                this.echo(input);
            }
            input = sc.nextLine();
        }
    }

    public void listInputs() {
        System.out.println("\t____________________________________________________________");
        for (int i = 0; i < this.taskList.size(); i++) {
            System.out.println("\t" +
                    (i + 1) +
                    ". " +
                    (taskList.get(i).isTaskDone() ? "[X] " : "[ ] ") +
                    this.taskList.get(i).getListItem()
            );
        }
        System.out.println("\t____________________________________________________________");
    }
    public void echo(String input) {
        Task task = new Task(input);
        System.out.println("\t____________________________________________________________\n" +
                "\tadded: " + task.getListItem() + "\n" +
                "\t____________________________________________________________\n");
        this.taskList.add(task);
    }

    public void markAsDone(String index) {
        System.out.println("\t____________________________________________________________");
        try {
            int numericIndex = Integer.parseInt(index) - 1;
            if (numericIndex >= this.taskList.size()) {
                System.out.println("Sorry, this task does not exist. Please try again! UwU :3");
            } else if (taskList.get(numericIndex).isTaskDone()) {
                System.out.println("This task has already been marked as done. Great job!");
            } else {
                taskList.get(numericIndex).setTaskDone(true);
                System.out.println("Nice! I've marked this task as done:\n" +
                        "\t\t[X] " + taskList.get(numericIndex).getListItem()
                );
            }

        } catch (Exception e) {
            System.out.println("That was an invalid input. Please try again! UwU :3");
        }
        System.out.println("\t____________________________________________________________");
    }

    public void markAsUndone(String index) {
        System.out.println("\t____________________________________________________________");
        try {
            int numericIndex = Integer.parseInt(index) - 1;
            if (numericIndex >= taskList.size()) {
                System.out.println("Sorry, this task does not exist. Please try again! UwU :3");
            } else if (!taskList.get(numericIndex).isTaskDone()) {
                System.out.println("This task has already been marked as undone. Good luck!");
            } else {
                taskList.get(numericIndex).setTaskDone(false);
                System.out.println("\tOK, I've marked this task as not done yet:\n" +
                        "\t\t[ ] " + taskList.get(numericIndex).getListItem()
                );
            }
        } catch (Exception e) {
            System.out.println("That was an invalid input. Please try again! UwU :3");
        }
        System.out.println("\t____________________________________________________________");
    }

    public static void main(String[] args) {
        KaiYap yap = new KaiYap();
        yap.sayHello();
        yap.startChat();
        yap.sayBye();
    }
}
