import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        //        String logo = " ____        _        \n"
        //                + "|  _ \\ _   _| | _____ \n"
        //                + "| | | | | | | |/ / _ \\\n"
        //                + "| |_| | |_| |   <  __/\n"
        //                + "|____/ \\__,_|_|\\_\\___|\n";
        //        System.out.println("Hello from\n" + logo);
        String botName = "Wind";
        System.out.println("Hello I'm " + botName + "\n"
                + "What can I do for you?");
        Scanner scanner = new Scanner(System.in);
        List<Task> tasks = new ArrayList<>(100);
        while (true) {
            String input = scanner.nextLine();
            if (input.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                break;
            }
            if (input.equals("list")) {
                for (int i = 0; i < tasks.size(); i++) {
                    int number = i + 1;
                    System.out.println(number + ". " + tasks.get(i));
                }
                continue;
            }
            String[] splitedInput = input.split(" ");
            if (splitedInput[0].equals("mark")) {
                int itemNumber = Integer.parseInt(splitedInput[1]);
                tasks.get(itemNumber - 1).setIsDone(true);
                System.out.println("Nice! I've marked this task as done:\n"
                        + tasks.get(itemNumber - 1));
                continue;
            }
            if (splitedInput[0].equals("unmark")) {
                int itemNumber = Integer.parseInt(splitedInput[1]);
                tasks.get(itemNumber - 1).setIsDone(false);
                System.out.println("Ok, I've marked this task as not done yet\n"
                        + tasks.get(itemNumber - 1));
                continue;
            }
            tasks.add(new Task(input));
            System.out.println("added: " + input);

        }
    }
}

class Task {
    private final String description;
    private boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public void setIsDone(boolean isDone) {
        this.isDone = isDone;
    }

    @Override
    public String toString() {
        if (this.isDone) {
            return "[X] " + this.description;
        } else {
            return "[] " + this.description;
        }
    }

}
