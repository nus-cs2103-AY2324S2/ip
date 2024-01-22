import java.util.ArrayList;
import java.util.Scanner;

public class Wei {
    private static void greet() {
        System.out.println("Hello! I'm Wei.\n" + "What can I do for you?");
    }

    private static void exit() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    private static void list(ArrayList<Task> tasks) {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            int order = i + 1;
            Task task = tasks.get(i);
            String text = task.stringify();
            System.out.println(order + ". " + text);
        }
    }

    private static void mark(ArrayList<Task> tasks, String input) throws WeiException {
        try {
            int order = Integer.parseInt(input.substring(5)) - 1;
            Task task = tasks.get(order);
            task.setStatus(true);
            System.out.println("Nice! I've marked this task as done:");
            System.out.println(task.stringify());
        }
        catch (NumberFormatException e) {
            throw new WeiException("which task do you want to mark?");
        }
    }

    private static void unmark(ArrayList<Task> tasks, String input) throws WeiException {
        try {
            int order = Integer.parseInt(input.substring(7)) - 1;
            Task task = tasks.get(order);
            task.setStatus(false);
            System.out.println("OK, I've marked this task as not done yet:");
            System.out.println(task.stringify());
        }
        catch (NumberFormatException e) {
            throw new WeiException("which task do you want to unmark?");
        }
    }

    private static void delete(ArrayList<Task> tasks, String input) throws WeiException {
        try {
            int order = Integer.parseInt(input.substring(7)) - 1;
            Task task = tasks.get(order);
            String text = task.stringify();
            tasks.remove(order);
            System.out.println("Noted. I've removed this task:");
            System.out.println(text);
            System.out.println("Now you have " + tasks.size() + " tasks in the list.");
        }
        catch (NumberFormatException e) {
            throw new WeiException("which task do you want to delete?");
        }
    }

        private static void addTask(ArrayList<Task> tasks, String input) throws WeiException {
        if (input.startsWith("todo")) {
            addToDo(tasks, input);
        }
        else if (input.startsWith("deadline")) {
            addDeadline(tasks, input);
        }
        else if (input.startsWith("event")) {
            addEvent(tasks, input);
        }
        else {
            throw new WeiException("OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
        int size = tasks.size();
        System.out.println("Got it. I've added this task:");
        System.out.println(tasks.get(size - 1).stringify());
        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
    }

    private static void addToDo(ArrayList<Task> tasks, String input) throws WeiException{
        if (input.length() < 5) {
            throw new WeiException("please tell me what is your task about");
        }
        ToDo todo = new ToDo(input.substring(5));
        tasks.add(todo);
    }

    private static void addDeadline(ArrayList<Task> tasks, String input) throws WeiException {
        try {
            int index = input.indexOf("/");
            String task = input.substring(9, index);
            String date = input.substring(index + 4);
            Deadline deadline = new Deadline(task, date);
            tasks.add(deadline);
        }
        catch (IndexOutOfBoundsException e) {
            throw new WeiException("please tell me when is the deadline");
        }
    }

    private static void addEvent(ArrayList<Task> tasks, String input) throws WeiException {
        try {
            int firstIndex = input.indexOf("/");
            int secondIndex = input.lastIndexOf("/");
            String start = input.substring(firstIndex + 6, secondIndex);
            String end = input.substring(secondIndex + 4);
            String task = input.substring(6, firstIndex);
            Event event = new Event(task, start, end);
            tasks.add(event);
        }
        catch (IndexOutOfBoundsException e) {
            throw new WeiException("please tell me when is the event occurring");
        }
    }

    public static void main(String[] args) {
        String split = "______________________________";
        greet();
        System.out.println(split);

        ArrayList<Task> tasks = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        while (true) {
            try {
                String input = scanner.nextLine();
                if (input.equals("bye")) {
                    break;
                }

                // list
                else if (input.equals("list")) {
                    if (tasks.size() == 0) {
                        System.out.println(split);
                        continue;
                    }
                    list(tasks);
                }

                // mark
                else if (input.matches("mark \\d+")) {
                    mark(tasks, input);
                }

                // unmark
                else if (input.matches("unmark \\d+")) {
                    unmark(tasks, input);
                }

                // delete
                else if (input.matches("delete \\d+")) {
                    delete(tasks, input);
                }

                // add
                else {
                    addTask(tasks, input);
                }
            }
            catch (WeiException e) {
                System.out.println(e.getMessage());
            }
            System.out.println(split);
        }

        // exit
        exit();
        System.out.println(split);
    }
}
