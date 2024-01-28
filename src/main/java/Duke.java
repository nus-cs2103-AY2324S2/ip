import java.util.Scanner;
import java.util.ArrayList;
public class Duke {
    private static Scanner sc = new Scanner(System.in);
    private static ArrayList<Task> tasks = new ArrayList<>();
    static void breakLine() {
        System.out.println("---------------------------------------");
    }

    static void greet() {
        breakLine();
        System.out.println("Hello! I'm teletubby"  + "\nWhat can I do for you?");
        breakLine();
    }

    static void exit() {
        System.out.println("Bye. Hope to see you again soon!");
        breakLine();
    }

    static void echo() {

        while (true) {
            String input = sc.nextLine();

            if (input.equals("bye")) {
                breakLine();
                exit();
                break;
            } else {
                breakLine();
                System.out.println(input);
                breakLine();
            }
        }

        sc.close();
    }

    static void add(String input) throws DukeException {
        breakLine();
        Task taskAdded;
        if (input.startsWith("todo")) {
            if (input.length() < 6) {
                throw new DukeException("do what?");
            }
            ToDo td = new ToDo(input.substring(5));
            tasks.add(td);
            taskAdded = td;
        } else if (input.startsWith("deadline")) {
            if (input.length() < 12) {
                throw new DukeException("what's the task?");
            }
            String by = input.split("/by ", 2)[1];
            String description = input.split(" ", 2)[1].split(" /by")[0];
            Deadline d = new Deadline(description, by);
            tasks.add(d);
            taskAdded = d;
        } else if (input.startsWith("event")) {
            String description = input.split(" ", 2)[1].split(" /from")[0];
            String start = input.split("/from ",2)[1].split(" /to")[0];
            String end = input.split("/to ")[1];
            Event e = new Event(description, start, end);
            tasks.add(e);
            taskAdded = e;
        } else {
            throw new DukeException("gong mat yeh");
        }
        int numItems = tasks.size();
        String sOrP = numItems == 1 ? "task" : "tasks";
        System.out.println("Got it. I've added this task:\n" + taskAdded.toString() + "Now you have " + numItems + " " + sOrP +" in the list.");
        breakLine();
    }

    static void list() {
        breakLine();
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            Task t = tasks.get(i);
            System.out.printf("%d. %s", i+1, t.toString());
        }
        breakLine();
    }

    static void delete(String input) throws DukeException {
        breakLine();
        String taskNumber = input.replaceAll("delete", "").replaceAll(" ","");
        if (taskNumber.length() < 1) {
            throw new DukeException("which task?");
        }
        try {
            int task = Integer.parseInt(taskNumber);
            Task removedTask = tasks.get(task-1);
            tasks.remove(task -1);
            int numItems = tasks.size();
            String sOrP = numItems == 1 ? "task" : "tasks";
            System.out.println("Noted. I've removed this task:\n" + removedTask.toString() + "Now you have " + numItems + " " + sOrP + " in the list.");
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("invalid task number... count properly xx");
        }
        breakLine();
    }

    public static void main(String[] args) {
        greet();
        while (true) {
            String input = sc.nextLine();
            if (input.equals("bye")) {
                breakLine();
                exit();
                break;
            } else if (input.equals("list")) {
                list();
            } else if (input.startsWith("mark")) {
                try {
                    String[] parts = input.split("mark ");
                    if (parts.length < 2) {
                        throw new DukeException("Missing task number...");
                    }
                    int taskNumber = Integer.parseInt(parts[1]);
                    tasks.get(taskNumber - 1).markAsDone();
                } catch (DukeException a) {
                    breakLine();
                    System.out.println(a.getMessage());
                    breakLine();
                } catch (IndexOutOfBoundsException b) {
                    breakLine();
                    System.out.println("Invalid task number... count properly xx");
                    breakLine();
                }
            } else if (input.startsWith("unmark")) {
                try {
                    String[] parts = input.split("unmark ");
                    if (parts.length < 2) {
                        throw new DukeException("Missing task number...");
                    }
                    int taskNumber = Integer.parseInt(parts[1]);
                    tasks.get(taskNumber - 1).unmark();
                } catch (DukeException a) {
                    breakLine();
                    System.out.println(a.getMessage());
                    breakLine();
                } catch (IndexOutOfBoundsException b) {
                    breakLine();
                    System.out.println("Invalid task number... count properly xx");
                    breakLine();
                }
            } else if (input.startsWith("delete")) {
                try {
                    delete(input);
                } catch (DukeException e) {
                    System.out.println(e.getMessage());
                    breakLine();
                }
            } else {
                try {
                    add(input);
                } catch (DukeException e) {
                    System.out.println(e.getMessage());
                    breakLine();
                } catch (IndexOutOfBoundsException a) {
                    System.out.println("please follow input format...");
                    breakLine();
                }
            }
        }
    }
}
