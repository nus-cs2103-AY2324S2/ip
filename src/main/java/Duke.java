import java.lang.reflect.Array;
import java.util.Scanner;
import java.util.ArrayList;

class Task {
    protected String userInput;
    protected boolean isDone;

    public Task(String input) {
        this.userInput = input;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public void unmarkTask() {
        this.isDone = false;
    }

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + userInput;
    }
}

public class Duke {
    private static ArrayList<Task> storage = new ArrayList<>();
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        //SAY HI, don't change
        String name = "____________________________________________________________ \n"
                + "Hello! I'm RATZCHAT \n"
                + "How can I help you today?";
        System.out.println(name);
        printLine();

        //RESPONSIVE
        while(true) {
            String input = scanner.nextLine();
            printLine();

            if("bye".equalsIgnoreCase(input)) {
                System.out.println("BYEBYE. Thank you for using RATZCHAT!");
                printLine();
                break;
            }

            if("list".equalsIgnoreCase(input)) {
                System.out.println("These are your to-dos: ");
                printList(storage);
                printLine();
                continue;
            }

            if(input.startsWith("mark") || input.startsWith("unmark")) {
                markingHandler(input);
                printLine();
                continue;
            }

            Task task = new Task(input);
            storage.add(task);
            System.out.println("added: " + input);
            printLine();
        }
    }

    private static void printLine() {
        System.out.println("____________________________________________________________");
    }

    private static void printList(ArrayList<Task> list) {
        for(int i = 0; i < list.size(); i++) {
            System.out.println((i + 1) + "." + list.get(i));
        }
    }

    private static void markingHandler(String input) {
        String[] split = input.split(" ");
        if (split.length < 2) {
            System.out.println("Please specify the task number!");
            return;
        }

        try {
            int index = Integer.parseInt(split[1]) - 1;
            Task task = storage.get(index);

            if ("mark".equalsIgnoreCase(split[0])) {
                task.markAsDone();
                System.out.println("I've marked this task as done:\n  " + task);
            }

            else if ("unmark".equalsIgnoreCase(split[0])) {
                task.unmarkTask();
                System.out.println("I've unmarked this task! It is now not done yet:\n  " + task);
            }
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            System.out.println("Invalid task number. Please refer to your to-do list again.");
        }
    }
}

