import java.util.Scanner;

class tasks {
    private String taskDescription;
    private boolean isDone;

    public tasks(String taskDescription) {
        this.taskDescription = taskDescription;
        this.isDone = false;
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public void markAsUndone() {
        this.isDone = false;
    }

    public String toString() {
        return "[" + (isDone ? "X" : " ") + "] " + taskDescription;
    }
}

public class Duke {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        tasks[] task = new tasks[100];
        int Count = 0;

        System.out.println("Hello! I'm Bentley\n" + "What can I do for you?\n");

        while (Count < 100) {
            String userInput = scanner.nextLine();

            if (userInput.equals("Bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                break;
            } else if (userInput.equals("List")) {

                for (int i = 0; i < Count; i++) {
                    System.out.println((i + 1) + ". " + task[i]);
                }
            } else if (userInput.startsWith("mark")) {
                System.out.println(" Nice! I've marked this task as done:");
                int taskNumber = Integer.parseInt(userInput.split(" ")[1]);
                task[taskNumber - 1].markAsDone();

                for (int i = 0; i < Count; i++) {
                    System.out.println((i + 1) + ". " + task[i]);
                }

            } else if (userInput.startsWith("unmark")) {
                System.out.println(" OK, I've marked this task as not done yet:");
                int taskNumber = Integer.parseInt(userInput.split(" ")[1]);
                task[taskNumber - 1].markAsUndone();

                for (int i = 0; i < Count; i++) {
                    System.out.println((i + 1) + ". " + task[i]);
                }

            } else {
                task[Count] = new tasks(userInput);
                System.out.println("added: " + task[Count]);
                Count++;
            }
        }

        scanner.close();
    }
}