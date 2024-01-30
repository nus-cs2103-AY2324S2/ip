import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class Reacher {
    public static void main(String[] args) {
            Scanner scanner = new Scanner(System.in);
        ArrayList<Task> memory = new ArrayList<>();
        System.out.println("Hello!\n" +
                "I'm Reacher.\n" +
                "Give me tasks.\n" +
                "Functions are edit, list, delete and bye");
        while (true) {
            try {
                String input = scanner.nextLine();
                if (input.isEmpty()) {
                    throw new ReacherException("pls type a task name.");
                }
                if (input.equalsIgnoreCase("bye")) { //check for end request
                    System.out.println("bye");
                    break;
                } else if (input.equalsIgnoreCase("list")) {// check for list request
                    System.out.println("Tasks:");
                    int c = 1;
                    for (Task task : memory) {
                        System.out.println(c + task.toString());
                        c++;
                    }
                } else if (input.equalsIgnoreCase("edit")) {
                    System.out.println("Which task number would u like to edit?");
                    try {
                        int num = scanner.nextInt();
                        scanner.nextLine();
                        if (num > memory.size() || num < 1) {
                            throw new ReacherException("No such task number");
                        }
                        Task task = memory.get(num - 1);
                        System.out.println("Mark Done or Undone or Delete?");
                        String change = scanner.nextLine();
                        if (change.equalsIgnoreCase("done")) {
                            task.markDone();
                            System.out.println("Task " + num + " marked done");
                        } else if (change.equalsIgnoreCase("undone")) {
                            task.markNotDone();
                            System.out.println("Task " + num + " marked Undone");
                        } else if (change.equalsIgnoreCase("delete")) {
                            memory.remove(num);
                            System.out.println("Task " + num + " deleted");
                        }else {
                            throw new ReacherException("u did not write done, undone or delete.");
                        }
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                } else {
                    System.out.println("What type of task is this?(Deadline, Event, Todo)");
                    try {
                        String type = scanner.nextLine();
                        if (type.equalsIgnoreCase("todo")) {
                            Todos t = new Todos(input);
                            memory.add(t);
                            System.out.println("I've added " + t.toString());
                        } else if (type.equalsIgnoreCase("deadline")) {
                            System.out.println("When is the deadline?");
                            String deadline = scanner.nextLine();
                            Deadline t = new Deadline(input, deadline);
                            memory.add(t);
                            System.out.println("I've added " + t.toString());
                        } else if (type.equalsIgnoreCase("event")) {
                            System.out.println("When is the start?");
                            String start = scanner.nextLine();
                            System.out.println("When is the end?");
                            String end = scanner.nextLine();
                            Events t = new Events(input, start, end);
                            memory.add(t);
                            System.out.println("I've added " + t.toString());
                        } else {
                            throw new ReacherException("That is not a type of task.");
                        }
                    } catch (ReacherException e) {
                        System.out.println(e.getMessage());
                    }
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        scanner.close();
    }
}
