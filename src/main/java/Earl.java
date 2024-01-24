import java.util.Scanner;

public class Earl {

    private static final String padding = " ".repeat(4);
    private static final Task[] tasks = new Task[100];
    private static int count = 0;

    private static void printDivider() {
        System.out.println(padding + "_".repeat(50));
    }

    private static void makeResponse(String... arr) {
        printDivider();
        for (String s : arr) {
            System.out.println(padding + s);
        }
        printDivider();
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        makeResponse("Hello! I'm Earl", "What can I do for you?");

        // main loop
        String input = sc.nextLine();
        String[] command = input.split("\\s", 2);
        while (!input.equals("bye")) {
            switch (command[0]) {
                case "list":
                    if (count > 0) {
                        String[] temp = new String[count];
                        for (int i = 0; i < count; ++i) {
                            temp[i] = i+1 + "." + tasks[i];
                        }
                        makeResponse(temp);
                    } else {
                        makeResponse("There is nothing to list.");
                    }
                    break;
                case "mark": {
                    int idx = Integer.parseInt(command[1]) - 1;
                    if (tasks[idx].markAsDone()) {
                        makeResponse(
                                "Item marked as done.",
                                padding + tasks[idx]
                        );
                    } else {
                        makeResponse("Item already marked as done.");
                    }
                    break;
                }
                case "unmark": {
                    int idx = Integer.parseInt(command[1]) - 1;
                    if (tasks[idx].markUndone()) {
                        makeResponse(
                                "Item marked as not done.",
                                padding + tasks[idx]
                        );
                    } else {
                        makeResponse("Item already marked as not done.");
                    }
                    break;
                }
                case "todo":
                    tasks[count++] = new Todo(command[1]);
                    makeResponse(
                            "Added new todo.",
                            padding + tasks[count-1],
                            "There are " + count + " tasks tracked."
                    );
                    break;
                case "deadline":
                    command = command[1].split("\\s/by\\s");
                    tasks[count++] = new Deadline(command[0], command[1]);
                    makeResponse(
                            "Added new deadline.",
                            padding + tasks[count-1],
                            "There are " + count + " tasks tracked."
                    );
                    break;
                case "event":
                    command = command[1].split("\\s/(from|to)\\s");
                    tasks[count++] = new Event(
                            command[0],
                            command[1],
                            command[2]
                    );
                    makeResponse(
                            "Added new event.",
                            padding + tasks[count-1],
                            "There are " + count + " tasks tracked."
                    );
                    break;
                default:
                    makeResponse("Unknown command.");
            }
            input = sc.nextLine();
            command = input.split("\\s", 2);
        }
        makeResponse("Goodbye! See you soon.");
        sc.close();
    }
}