import java.util.*;
public class Lia {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<Task> tasks = new ArrayList<>();

        System.out.println("Hello! I'm Lia :)");
        System.out.println("What can I do for you?");

        while (true) {
            String input = sc.nextLine();

            if (input.equals("bye")) {
                break;
            } else if (input.equals("list")) {
                for (int j = 1; j <= tasks.size(); j++) {
                    Task task = tasks.get(j - 1);
                    if (task instanceof Todo) {
                        System.out.println(j + ". [" + task.getStatusIcon1() + "]" +
                                "[" + task.getStatusIcon2() + "] " + task.description);
                    } else if (task instanceof Deadline) {
                        System.out.println(j + ". [" + task.getStatusIcon1() + "]" +
                                "[" + task.getStatusIcon2() + "] " + task.description +
                                " (by: " + ((Deadline) task).date + ")");
                    } else if (task instanceof Event) {
                        System.out.println(j + ". [" + task.getStatusIcon1() + "]" +
                                "[" + task.getStatusIcon2() + "] " + task.description +
                                " (from: " + ((Event) task).start + " to: " + ((Event) task).end + ")");
                    }
                }
            } else if (input.startsWith("mark")) {
                String[] tokens = input.split(" ");
                int pos = Integer.parseInt(tokens[1]);
                Task task = tasks.get(pos - 1);
                task.markAsDone();
                System.out.println("I've marked this task as done:");
                if (task instanceof Todo) {
                    System.out.println("[" + task.getStatusIcon1() + "]" +
                            "[" + task.getStatusIcon2() + "] " + task.description);
                } else if (task instanceof Deadline) {
                    System.out.println("[" + task.getStatusIcon1() + "]" +
                            "[" + task.getStatusIcon2() + "] " + task.description +
                            " (by: " + ((Deadline) task).date + ")");
                } else if (task instanceof Event) {
                    System.out.println("[" + task.getStatusIcon1() + "]" +
                            "[" + task.getStatusIcon2() + "] " + task.description +
                            " (from: " + ((Event) task).start + " to: " + ((Event) task).end + ")");
                }
            } else if (input.startsWith("unmark")) {
                String[] tokens = input.split(" ");
                int pos = Integer.parseInt(tokens[1]);
                Task task = tasks.get(pos - 1);
                task.markAsNotDone();
                System.out.println("I've marked this task as not done:");
                if (task instanceof Todo) {
                    System.out.println("[" + task.getStatusIcon1() + "]" +
                            "[" + task.getStatusIcon2() + "] " + task.description);
                } else if (task instanceof Deadline) {
                    System.out.println("[" + task.getStatusIcon1() + "]" +
                            "[" + task.getStatusIcon2() + "] " + task.description +
                            " (by: " + ((Deadline) task).date + ")");
                } else if (task instanceof Event) {
                    System.out.println("[" + task.getStatusIcon1() + "]" +
                            "[" + task.getStatusIcon2() + "] " + task.description +
                            " (from: " + ((Event) task).start + " to: " + ((Event) task).end + ")");
                }
            } else if (input.startsWith("todo")) {
                String todo = input.replaceFirst("todo", "").trim();
                tasks.add(new Todo(todo));
                System.out.println("I've added this task:");
                Task todoTask = tasks.get(tasks.size() - 1);
                System.out.println("[" + todoTask.getStatusIcon1() + "]" +
                        "[" + todoTask.getStatusIcon2() + "] " + todoTask.description);
                System.out.println("You have " + tasks.size() + " task(s) in the list.");
            } else if (input.startsWith("deadline")) {
                String deadline = input.replaceFirst("deadline", "").split("/by")[0].trim();
                String date = input.split("/by")[1].trim();
                tasks.add(new Deadline(deadline, date));
                System.out.println("I've added this task:");
                Task deadlineTask = tasks.get(tasks.size() - 1);
                System.out.println("[" + deadlineTask.getStatusIcon1() + "]" +
                        "[" + deadlineTask.getStatusIcon2() + "] " + deadlineTask.description +
                        " (by: " + ((Deadline) deadlineTask).date + ")");
                System.out.println("You have " + tasks.size() + " task(s) in the list.");
            } else if (input.startsWith("event")) {
                String event = input.replaceFirst("event", "").split("/from")[0].trim();
                String range = input.split("/from")[1].trim();
                String start = range.split("/to")[0].trim();
                String end = range.split("/to")[1].trim();
                tasks.add(new Event(event, start, end));
                System.out.println("I've added this task:");
                Task eventTask = tasks.get(tasks.size() - 1);
                System.out.println("[" + eventTask.getStatusIcon1() + "]" +
                        "[" + eventTask.getStatusIcon2() + "] " + eventTask.description +
                        " (from: " + ((Event) eventTask).start + " to: " + ((Event) eventTask).end + ")");
                System.out.println("You have " + tasks.size() + " task(s) in the list.");
            } else {
                System.out.println("invalid input");
            }
        }
        System.out.println("Goodbye!");
    }
}