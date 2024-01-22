import java.util.Scanner;
import java.util.ArrayList;
public class Duke {
    public static void main(String[] args) {
        String input;
        String line = "_________________________________\n";
        Storage storage = new Storage();
        System.out.print(line);
        System.out.println("Hello! I'm ChatterPal!");
        System.out.println("What can I do for you?");
        System.out.print(line);
        Scanner sc = new Scanner(System.in);
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        input = sc.nextLine();
        String[] inputParts = input.split(" ");
        while (!input.equals("bye")) {
            if (input.equals("list")) {
                System.out.println(line);
                System.out.println(storage);
                System.out.println(line);

            } else if (inputParts[0].equals("mark")) {
                Task t = storage.get(Integer.parseInt(inputParts[1]) - 1);
                t.markAsDone();
                System.out.println(line);
                System.out.println("Great job on completing the task!");
                System.out.println(t.toString());
                System.out.println(line);
            } else if (inputParts[0].equals("unmark")) {
                Task t = storage.get(Integer.parseInt(inputParts[1]) - 1);
                t.markAsUndone();
                String output = line ;
                output += "OK, I've marked this task as not done yet: \n" +
                        t.toString() + line;
                System.out.println(output);
            } else {
                Task newTask = new Task(input);
                storage.add(newTask);
                System.out.println(line);
                System.out.printf("added: %s\n", input);
                System.out.println(line);
            }
            input = sc.nextLine();
            inputParts = input.split(" ");
        }
        System.out.println(line);
        System.out.println("Farewell! Can't wait to catch up with you again. Until next time, " +
                "take care and stay awesome! ");
        System.out.println(line);
    }
}
