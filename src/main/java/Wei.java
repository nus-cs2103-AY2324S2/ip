import java.util.Scanner;

public class Wei {
    private static void greet() {
        System.out.println("Hello! I'm Wei.\n" + "What can I do for you?");
    }

    private static void exit() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    private static void list(Task[] tasks, int numOfTasks) {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < numOfTasks; i++) {
            int order = i + 1;
            String text = tasks[i].stringify();
            System.out.println(order + ". " + text);
        }
    }

    private static void mark(Task[] tasks, int order) {
        tasks[order].setStatus(true);
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(tasks[order].stringify());
    }

    private static void unmark(Task[] tasks, int order) {
        tasks[order].setStatus(false);
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println(tasks[order].stringify());
    }

    private static void addToDo(Task[] tasks, int numOfTasks, String input) {
        ToDo todo = new ToDo(input.substring(5));
        tasks[numOfTasks] = todo;
    }

    private static void addDeadline(Task[] tasks, int numOfTasks, String input) {
        int index = input.indexOf("/");
        String task = input.substring(9, index);
        String date = input.substring(index + 4);
        Deadline deadline = new Deadline(task, date);
        tasks[numOfTasks] = deadline;
    }

    private static void addEvent(Task[] tasks, int numOfTasks, String input) {
        int firstIndex = input.indexOf("/");
        int secondIndex = input.lastIndexOf("/");
        String start = input.substring(firstIndex + 6, secondIndex);
        String end = input.substring(secondIndex + 4);
        String task = input.substring(6, firstIndex);
        Event event = new Event(task, start, end);
        tasks[numOfTasks] = event;
    }

        public static void main(String[] args) {
        String split = "______________________________";
        greet();
        System.out.println(split);

        Task[] tasks = new Task[100];
        int numOfTasks = 0;
        Scanner scanner = new Scanner(System.in);
        while (true) {
            String input = scanner.nextLine();
            if(input.equals("bye")) {
                break;
            }

            // list
            else if (input.equals("list")) {
                if(tasks[0] == null) {
                    System.out.println(split);
                    continue;
                }
                list(tasks, numOfTasks);
            }

            // mark
            else if (input.matches("mark \\d+")) {
                int order = Integer.parseInt(input.substring(5)) - 1;
                mark(tasks, order);
            }

            // unmark
            else if (input.matches("unmark \\d+")) {
                int order = Integer.parseInt(input.substring(7)) - 1;
                unmark(tasks, order);
            }

            // add
            else {
                if (input.startsWith("todo")) {
                    addToDo(tasks, numOfTasks, input);
                }
                else if (input.startsWith("deadline")) {
                    addDeadline(tasks, numOfTasks, input);
                }
                else if (input.startsWith("event")) {
                    addEvent(tasks, numOfTasks, input);
                }

                System.out.println("Got it. I've added this task:");
                System.out.println(tasks[numOfTasks].stringify());
                numOfTasks++;
                System.out.println("Now you have " + numOfTasks + " tasks in the list.");
            }
            System.out.println(split);
        }

        // exit
        exit();
        System.out.println(split);
    }
}
